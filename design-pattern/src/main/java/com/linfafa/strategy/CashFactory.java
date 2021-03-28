package com.linfafa.strategy;

/**
 * 现金收费工厂，不推荐
 */
public class CashFactory {

    public static CashSuper createCashAccept(int type) {
        CashSuper cs = null;
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
        return cs;
    }
}
