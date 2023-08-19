package com.github.pyro233.mini.spring.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 10:16
 */
public class ClassPathXmlResource implements Resource<Element> {

    private Document document;
    private Element rootElement;
    private Iterator<Element> elements;

    public ClassPathXmlResource(String fileName) {
        final SAXReader saxReader = new SAXReader();
        final URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement = document.getRootElement();
            this.elements = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return elements.hasNext();
    }

    @Override
    public Element next() {
        return elements.next();
    }
}
