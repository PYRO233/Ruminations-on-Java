package com.github.pyro233.mini.spring.web;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/23 13:00
 */
class HelloWorldBean {

    @RequestMapping("/test1")
    public String doTest1() {
        return "test 1, hello world!";
    }

    @RequestMapping("/test2")
    public String doTest2() {
        return "test 2, hello world!";
    }

}