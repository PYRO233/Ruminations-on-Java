package com.github.pyro233.mini.spring.test;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 19:21
 */
public class ServiceL1 {

    private Service service;

    private ServiceL2 serviceL2;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceL2 getServiceL2() {
        return serviceL2;
    }

    public void setServiceL2(ServiceL2 serviceL2) {
        this.serviceL2 = serviceL2;
    }

    public String getResult() {
        return serviceL2.getResult();
    }
}
