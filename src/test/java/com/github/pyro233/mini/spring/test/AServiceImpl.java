package com.github.pyro233.mini.spring.test;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 9:46
 */
public class AServiceImpl implements AService {

    private String name;
    private int level;
    private String property;

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

    public AServiceImpl() {
    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void sayHello() {
        // print "a service 1 say hello"
        System.out.println("a service 1 say hello");
    }
}
