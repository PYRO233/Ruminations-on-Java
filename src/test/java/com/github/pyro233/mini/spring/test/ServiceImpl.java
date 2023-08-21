package com.github.pyro233.mini.spring.test;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 9:46
 */
public class ServiceImpl implements Service {

    private String name;
    private int level;
    private String property;
    private ServiceL1 serviceL1;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public ServiceImpl() {
    }

    public ServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void setServiceL1(ServiceL1 serviceL1) {
        this.serviceL1 = serviceL1;
    }

    @Override
    public String getData() {
        return serviceL1.getResult();
    }
}
