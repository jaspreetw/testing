package com.rjil.snw.mobileAutomation;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import javax.xml.bind.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

public class Demo {

    public static void main(String[] args) throws Exception {
        String msg = "<root><string>switchNwalk</string><key>UIBackgroundModes</key> </root>";
        //String msg = "<message>HELLO!</message>";
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(msg.getBytes()));
        System.out.println(parse.getNodeValue());
    }

}


