package com.linfafa.factorymethod;

import java.util.ArrayList;
import java.util.List;


public class IDCardFactory extends Factory {
    private List<String> owners = new ArrayList<String>();

    public Product createProduct(String owner) {
        return new IDCard(owner);
    }

    /**
     * 将owner保存到owners里来实现注册产品
     */
    public void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
}
