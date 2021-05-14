package interpreter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class JmxC {

    public static void main(String[] args) {
        if(args.length>0){
            if(args[0].equals("-l")){
                JmxC c = new JmxC();
                c.m1();
            }else if(args[0].equals("-p")){
                int id = Integer.valueOf(args[1]);
                System.out.println("pid:"+id);
                JmxC c = new JmxC();
                if(args.length > 2 && args[2].equals("-n")){
                    String name = args[3];
                    System.out.println("args name:"+name);
                    if(args.length > 4 && args[4].equals("-a")){
                        String atts = args[5];
                        System.out.println("args atts:"+name);
                        c.processMbean(c.getConnectStrById(id),name,atts);
                    }else{
                        c.processMbean(c.getConnectStrById(id),name,null);
                    }
                }else{
                    c.processMbean(c.getConnectStrById(id));
                }
            }
        }

    }

    public void processMbean(String connectorAddress,String name,String att){
        if(null == connectorAddress) return;
        JMXConnector connector = null;
        try {
            System.out.println("conn:" + connectorAddress);
            JMXServiceURL url = new JMXServiceURL(connectorAddress);
            connector = JMXConnectorFactory.connect(url);

            MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
            Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
            System.out.println("beanSet num:" + beanSet.size());
            ObjectName n = new ObjectName(name);
            MBeanInfo info = mbeanConn.getMBeanInfo(n);
            if(null != info){
                if(null != att && !att.isEmpty()){
                    String[] as = att.split(",");
                    List<String> al = new LinkedList<String>();
                    System.out.print("      time   "+"\t");
                    for(String a:as){
                        if(null != a && !a.isEmpty()){
                            al.add(a);
                            System.out.print(a+"\t");
                        }
                    }
                    System.out.println();
                    SimpleDateFormat dateformat1=new SimpleDateFormat("HH:mm:ss");
                    while(true){
                        System.out.print(dateformat1.format(new Date())+"\t");
                        for(String a:al){
                            Object value = null;
                            if(!a.contains("-")){
                                value = mbeanConn.getAttribute(n, a);
                            }else{
                                String[] at= a.split("-");
                                value = (long)mbeanConn.getAttribute(n, at[0]) - (long)mbeanConn.getAttribute(n, at[1]);
                            }

                            System.out.print(value+"\t");
                        }
                        System.out.println();
                        Thread.sleep(1000);
                    }

                }else{
                    MBeanAttributeInfo[] atts= info.getAttributes();
                    for(MBeanAttributeInfo attr : atts){
                        Object value = mbeanConn.getAttribute(n, attr.getName());
                        System.out.println(attr.getName()+"->"+value);
                    }
                }
            }else{
                System.err.println("info is null");
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connector != null) connector.close();
                // break;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // ...
    }
    public void processMbean(String connectorAddress){
        if(null == connectorAddress) return;
        JMXConnector connector = null;
        try {
            System.out.println("conn:" + connectorAddress);
            JMXServiceURL url = new JMXServiceURL(connectorAddress);
            connector = JMXConnectorFactory.connect(url);

            MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
            Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
            System.out.println("beanSet num:" + beanSet.size());

            for(ObjectName name: beanSet){

                if(name.getDomain().equals("org.apache.commons.pool2")){
                    System.out.println("name:"+name);
                    //ObjectInstance instance = mbeanConn.getObjectInstance(name);
                    MBeanInfo info = mbeanConn.getMBeanInfo(name);
                    MBeanAttributeInfo[] atts= info.getAttributes();
                    for(MBeanAttributeInfo attr : atts){
                        Object value = mbeanConn.getAttribute(name, attr.getName());
                        System.out.println(attr.getName()+"->"+value);
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }

            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connector != null) connector.close();
                // break;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // ...
    }
    public String getConnectStrById(int pid){
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        int i = 0;
        for (VirtualMachineDescriptor desc : vms) {
            if(!desc.id().equals(""+pid)){
                continue;
            }
            VirtualMachine vm;
            try {
                System.out.println("desc:" + desc);
                System.out.println("process id:" + desc.id());
                vm = VirtualMachine.attach(desc);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            try {
                Properties props = vm.getAgentProperties();
                System.out.println("args:"+props.get("sun.jvm.args"));
                for (Map.Entry<Object, Object> entry : props.entrySet()) {
                    // System.out.println(entry.getKey() + "->" +
                    // entry.getValue());
                }

                String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if (connectorAddress == null) {
                    System.out.println("connectorAddress  is  null,and continue search");
                    props = vm.getSystemProperties();
                    String home = props.getProperty("java.home");

                    // Normally in ${java.home}/jre/lib/management-agent.jar but
                    // might
                    // be in ${java.home}/lib in build environments.

                    String agent = home + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";
                    File f = new File(agent);
                    if (!f.exists()) {
                        agent = home + File.separator + "lib" + File.separator + "management-agent.jar";
                        f = new File(agent);
                        if (!f.exists()) {
                            throw new IOException("Management agent not found");
                        }
                    }
                    agent = f.getCanonicalPath();
                    vm.loadAgent(agent, "com.sun.management.jmxremote");
                    props = vm.getAgentProperties();
                    connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                    if (connectorAddress == null) {
                        System.out.println("connectorAddress  is  null");
                    }

                }
                return connectorAddress;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return null");
        return null;
    }


    public void m1() {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        int i = 0;
        for (VirtualMachineDescriptor desc : vms) {
            System.out.println();
            System.out.println();
            System.out.println("------" + i++ + "----------------");
            VirtualMachine vm;
            try {
                System.out.println("desc:" + desc);
                System.out.println("process id:" + desc.id());
                vm = VirtualMachine.attach(desc);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            JMXConnector connector = null;
            try {
                Properties props = vm.getAgentProperties();
                System.out.println(props.get("sun.jvm.args"));
                for (Map.Entry<Object, Object> entry : props.entrySet()) {
                    // System.out.println(entry.getKey() + "->" +
                    // entry.getValue());
                }

                String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if (connectorAddress == null) {
                    System.out.println("connectorAddress  is  null,and continue search");
                    props = vm.getSystemProperties();
                    String home = props.getProperty("java.home");

                    // Normally in ${java.home}/jre/lib/management-agent.jar but
                    // might
                    // be in ${java.home}/lib in build environments.

                    String agent = home + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";
                    File f = new File(agent);
                    if (!f.exists()) {
                        agent = home + File.separator + "lib" + File.separator + "management-agent.jar";
                        f = new File(agent);
                        if (!f.exists()) {
                            throw new IOException("Management agent not found");
                        }
                    }
                    agent = f.getCanonicalPath();
                    vm.loadAgent(agent, "com.sun.management.jmxremote");
                    props = vm.getAgentProperties();
                    connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                    if (connectorAddress == null) {
                        System.out.println("connectorAddress  is  null");
                        continue;
                    }
                }
                System.out.println("conn:" + connectorAddress);
                JMXServiceURL url = new JMXServiceURL(connectorAddress);
                connector = JMXConnectorFactory.connect(url);

                MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
                Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
                System.out.println("beanSet num:" + beanSet.size());
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connector != null) connector.close();
                    // break;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
