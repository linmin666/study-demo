package com.linfafa.strategy;

public class Client {
    public static void main(String[] args) {
        //策略模式
        Context context;
        context=new Context(new ConcreteStrategyA());
        context.contextInterface();

        context=new Context(new ConcreteStrategyB());
        context.contextInterface();

        context=new Context(new ConcreteStrategyC());
        context.contextInterface();

        //简单工厂实现
        CashSuper cashAccept = CashFactory.createCashAccept(1);
        double money=300;
        double cash = cashAccept.acceptCash(money);
        System.out.println("应收"+money+",实收"+cash);

        //策略模式实现
        CashContext ct=null;
        int type=2;
        switch (type) {
            case 1:
                ct = new CashContext(new CashNormal());
                break;
            case 2:
                ct=new CashContext(new CashReturn(300,100));break;
            case 3:
                ct=new CashContext(new CashRebate(0.8));break;
        }
        double cash1=ct.getResult(money);
        System.out.println("应收"+money+",实收"+cash1);


        //策略者结合工厂模式
        CashContext ct1=new CashContext(3);
        double cash2=ct1.getResult(money);
        System.out.println("应收"+money+",实收"+cash2);
    }

}
