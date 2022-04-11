/**
 * @author linmin
 * @date 2021/9/19
 */
public class Test {
    public int count;
    public Test(){
        count++;
        System.out.println(this.getClass().getClassLoader());
        System.out.println("count="+count);
    }
}
