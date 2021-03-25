package com.linfafa.strategy;

/**
 * 抽象算法类
 */
abstract class Strategy {
    public abstract void AlgorithmInterface();
}

/**
 * 具体算法A
 */
class ConcreteStrategyA extends Strategy{

    public void AlgorithmInterface() {
        System.out.println("算法A实现");
    }
}

/**
 * 具体算法B
 */
class ConcreteStrategyB extends Strategy{

    public void AlgorithmInterface() {
        System.out.println("算法B实现");
    }
}

/**
 * 具体算法C
 */
class ConcreteStrategyC extends Strategy{

    public void AlgorithmInterface() {
        System.out.println("算法C实现");
    }
}

