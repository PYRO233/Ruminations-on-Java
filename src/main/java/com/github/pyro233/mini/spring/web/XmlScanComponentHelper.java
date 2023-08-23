package com.github.pyro233.mini.spring.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/22 20:37
 */
public class XmlScanComponentHelper {

    public static final String BASE_PACKAGE = "base-package";

    public static List<String> getNodeValue(URL xmlPath) {
        List<String> packages = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        final Element root = document.getRootElement();
        final Iterator<Element> it = root.elementIterator();

        while (it.hasNext()) {
            Element element = it.next();
            packages.add(element.attributeValue(BASE_PACKAGE));
        }

        return packages;
    }

}
