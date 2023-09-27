package com.lanwq.other.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Vin lan
 * @className parse
 * @description
 * @createTime 2021-08-05  14:10
 **/
public class XmlParseTest {
    public static void main(String[] args) throws Exception {
        String str = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<records>\n" +
                "<record id=\"1\">\n" +
                "<orderStatus>Rejected<a>fafafa</a></orderStatus>\n" +
                "<ref>SO20210000000601</ref>\n" +
                "<payRef>9693788</payRef>\n" +
                "<amt>378</amt>\n" +
                "<cur>344</cur>\n" +
                "<prc>-8</prc>\n" +
                "<src>2053</src>\n" +
                "<ord>null</ord>\n" +
                "<holder>null</holder>\n" +
                "<authId>null</authId>\n" +
                "<alertCode>null</alertCode>\n" +
                "<remark></remark>\n" +
                "<eci>null</eci>\n" +
                "<payerAuth>null</payerAuth>\n" +
                "<sourceIp>219.134.131.100</sourceIp>\n" +
                "<ipCountry>CN*</ipCountry>\n" +
                "<payMethod>U</payMethod>\n" +
                "<panFirst4></panFirst4>\n" +
                "<panLast4></panLast4>\n" +
                "<cardIssuingCountry>--</cardIssuingCountry>\n" +
                "<channelType>SPC</channelType>\n" +
                "<txTime>2021-07-13 10:35:22.0</txTime>\n" +
                "<successcode>1</successcode>\n" +
                "<MerchantId>88153061</MerchantId>\n" +
                "<errMsg>Query Successfully</errMsg>\n" +
                "</record>\n" +
                "<record id=\"2\">\n" +
                "<orderStatus>Rejected</orderStatus>\n" +
                "<ref>SO20210000000601</ref>\n" +
                "<payRef>9693787</payRef>\n" +
                "<amt>378</amt>\n" +
                "<cur>344</cur>\n" +
                "<prc>-8</prc>\n" +
                "<src>2053</src>\n" +
                "<ord>null</ord>\n" +
                "<holder>null</holder>\n" +
                "<authId>null</authId>\n" +
                "<alertCode>null</alertCode>\n" +
                "<remark></remark>\n" +
                "<eci>null</eci>\n" +
                "<payerAuth>null</payerAuth>\n" +
                "<sourceIp>219.134.131.100</sourceIp>\n" +
                "<ipCountry>CN*</ipCountry>\n" +
                "<payMethod>U</payMethod>\n" +
                "<panFirst4></panFirst4>\n" +
                "<panLast4></panLast4>\n" +
                "<cardIssuingCountry>--</cardIssuingCountry>\n" +
                "<channelType>SPC</channelType>\n" +
                "<txTime>2021-07-13 10:35:18.0</txTime>\n" +
                "<successcode>1</successcode>\n" +
                "<MerchantId>88153061</MerchantId>\n" +
                "<errMsg>Query Successfully</errMsg>\n" +
                "</record>\n" +
                "</records>";
        String str2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bookstore>\n" +
                "<book id=\"1\">\n" +
                "<name>冰与火之歌</name>\n" +
                "<author>乔治马丁</author>\n" +
                "<year>2014</year>\n" +
                "<price>89</price>\n" +
                "</book>\n" +
                "<book id=\"2\">\n" +
                "<name>安徒生童话</name>\n" +
                "<year>2004</year>\n" +
                "<price>77</price>\n" +
                "<language>English</language>\n" +
                "</book>\n" +
                "</bookstore>\n";
        File file = new File("a.xml");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str2);
        writer.close();


        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            Document document = db.parse(file);
            //获取所有book节点的集合
            NodeList bookList = document.getElementsByTagName("book");
            //通过nodelist的getLength()方法可以获取bookList的长度
            System.out.println("一共有" + bookList.getLength() + "本书");
            //遍历每一个book节点
            for (int i = 0; i < bookList.getLength(); i++) {
                System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                //通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
                Node book = bookList.item(i);
                //获取book节点的所有属性集合
                NamedNodeMap attrs = book.getAttributes();
                System.out.println("第 " + (i + 1) + " 本书共有" + attrs.getLength() + " 个属性");
                //遍历book的属性
                for (int j = 0; j < attrs.getLength(); j++) {
                    //通过item(index)方法获取book节点的某一个属性
                    Node attr = attrs.item(j);
                    //获取属性名
                    System.out.print("属性名：" + attr.getNodeName());
                    //获取属性值
                    System.out.println("--属性值" + attr.getNodeValue());
                }
                //解析book节点的子节点
                NodeList childNodes = book.getChildNodes();
                //遍历childNodes获取每个节点的节点名和节点值
                System.out.println("第" + (i + 1) + "本书共有" +
                        childNodes.getLength() + "个子节点");
                for (int k = 0; k < childNodes.getLength(); k++) {
                    System.out.print("第" + (k + 1) + "个节点的节点名："
                            + childNodes.item(k).getNodeName() + " ");
                    //区分出text类型的node以及element类型的node
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        //获取了element类型节点的节点名
                        /*System.out.print("第" + (k + 1) + "个节点的节点名："
                                + childNodes.item(k).getNodeName());*/
                        //获取了element类型节点的节点值
                        System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                        //System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                    }
                }
                System.out.println("======================结束遍历第" + (i + 1) + "本书的内容=================");
            }

            Document document2 = db.parse(new ByteArrayInputStream(str.getBytes()));
            NodeList records = document2.getElementsByTagName("record");
            for (int i = 0; i < records.getLength(); i++) {
                System.out.println("开始遍历第" + (i + 1) + "record");
                Node record = records.item(i);
                NamedNodeMap attributes = record.getAttributes();
                System.out.println("第" + (i + 1) + "record,有" + attributes.getLength() + "个属性");
                for (int j = 0; j < attributes.getLength(); j++) {
                    //通过item(index)方法获取book节点的某一个属性
                    Node attr = attributes.item(j);
                    //获取属性名
                    System.out.print("属性名：" + attr.getNodeName());
                    //获取属性值
                    System.out.println("--属性值：" + attr.getNodeValue());
                }
                NodeList childNodes = record.getChildNodes();
                System.out.println("第" + (i + 1) + "record,有" + childNodes.getLength() + "个node");
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node item1 = childNodes.item(j);
                    if (item1.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("第" + (j + 1) + "个子节点");
                        System.out.println("nodeType:" + item1.getNodeType());
                        System.out.println("nodeName:" + item1.getNodeName());
                        if (item1.getChildNodes().getLength() > 0) {
                            System.out.println("nodeValue:" + item1.getFirstChild().getNodeValue());
                        } else {
                            System.out.println("nodeValue: ");
                        }
                    }
                }
                System.out.println("一个record结束");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



