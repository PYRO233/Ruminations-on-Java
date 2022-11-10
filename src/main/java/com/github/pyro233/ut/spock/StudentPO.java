package com.github.pyro233.ut.spock;

/**
 * @Author: tao.zhou
 * @Date: 2022/11/10 16:30
 */
public class StudentPO {
    private String id;
    private String name;
    private String code;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public static StudentPO create(String name, String code) {
        final StudentPO studentPO = new StudentPO();
        studentPO.setId(IDGenerator.nextId());
        studentPO.setName(name);
        studentPO.setCode(code);
        return studentPO;
    }

}
