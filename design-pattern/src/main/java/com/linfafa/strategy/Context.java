package com.linfafa.strategy;

/**
 * 上下文
 * 用一个ConcreteStrategy来配置，维护一个对Strategy对象的引用
 */
public class Context {
    Strategy strategy;
    public Context(Strategy strategy){
        this.strategy=strategy;
    }

    public void contextInterface(){
        strategy.AlgorithmInterface();
    }
}
