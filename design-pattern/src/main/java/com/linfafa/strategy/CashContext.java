package com.linfafa.strategy;

public class CashContext {
    private CashSuper cs;

    public CashContext(CashSuper csuper){
        this.cs=csuper;
    }
    public CashContext(int type) {
        switch (type) {
            //正常收费
            case 1:
                cs = new CashNormal();
                break;
            //满300减100
            case 2:
                cs = new CashReturn(300, 100);
                break;
            //打8折
            case 3:
                cs = new CashRebate(0.8);
                break;
        }

    }

    public double getResult(double money) {
        return cs.acceptCash(money);
    }
}
