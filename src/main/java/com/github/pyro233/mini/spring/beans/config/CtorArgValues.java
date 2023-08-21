package com.github.pyro233.mini.spring.beans.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 15:44
 */
public class CtorArgValues {

    private final List<CtorArg> argumentValueList = new ArrayList<>();

    public void addArgumentValue(CtorArg argumentValue) {
        argumentValueList.add(argumentValue);
    }

    public CtorArg getIndexedArgumentValue(int index) {
        return argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return argumentValueList.size();
    }

}
