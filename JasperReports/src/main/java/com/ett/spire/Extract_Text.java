package com.ett.spire;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.io.*;

public class Extract_Text {

    public static void main(String[] args) {

        //创建PdfDocument实例
        PdfDocument doc = new PdfDocument();
        //加载PDF文件
        doc.loadFromFile("D:\\7-11_HK_TC.pdf");

        //创建StringBuilder实例
        StringBuilder sb = new StringBuilder();

        PdfPageBase page;
        //遍历PDF页面，获取每个页面的文本并添加到StringBuilder对象
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            page = doc.getPages().get(i);
            sb.append(page.extractText(true));
        }
        FileWriter writer;
        try {
            //将StringBuilder对象中的文本写入到文本文件
            writer = new FileWriter("ExtractText.txt");
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.close();

        System.out.println("\\");
    }
}
