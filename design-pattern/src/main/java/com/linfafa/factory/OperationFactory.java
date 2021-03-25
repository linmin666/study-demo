package com.linfafa.factory;

/**
 * 简单工厂类，对所有的操作类进行了实例化
 */
public class OperationFactory {
    public static Operation createOperation(char operate){
        Operation oper=null;
        switch (operate){
            case '+':
                oper=new OperationAdd();break;
            case '-':
                oper=new OperationSub();break;
            case '*':
                oper=new OperationMul();break;
            case '/':
                oper=new OperationDiv();break;
        }
        return oper;
    }
}
