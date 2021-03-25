package com.linfafa.strategy;

/**
 * 现金收费抽象类
 */
abstract class CashSuper {
    public abstract double acceptCash(double money);
}

/**
 * 正常收费子类
 */
class CashNormal extends CashSuper {

    public double acceptCash(double money) {
        return money;
    }
}

/**
 * 打折收费子类
 */
class CashRebate extends CashSuper {

    private double moneyRebate = 1d;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}

/**
 * 返利收费子类
 */
class CashReturn extends CashSuper {

    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    public double acceptCash(double money) {
        double result = money;
        //若大于返利条件，则需要减去返利值
        if (money >= moneyCondition)
            result = money - money / moneyCondition * moneyReturn;
        return result;
    }
}