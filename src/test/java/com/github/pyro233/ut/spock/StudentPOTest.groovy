package com.github.pyro233.ut.spock

import org.mockito.Mockito
import spock.lang.Specification

/**
 * @Author: tao.zhou
 * @Date: 2022/11/10 16:40
 */
class StudentPOTest extends Specification {

    def "should success when create student"() {
        given:
        var name = "tester"
        var code = "123456"
        Mockito.mockStatic(IDGenerator.class)
        Mockito.when(IDGenerator.nextId()).thenReturn("123456789")

        when:
        var student = StudentPO.create(name, code)

        then:
        student.getId() == "123456789"
    }


}
