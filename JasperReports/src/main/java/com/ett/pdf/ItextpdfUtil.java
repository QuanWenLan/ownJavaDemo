package com.ett.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;

/**
 * @author Vin lan
 * @className ItextpdfUtil https://blog.csdn.net/charliechen1989/article/details/42425617
 * @description TODO  使用itextpdf 读取pdf文件内容为字符串
 * @createTime 2021-01-08  09:25
 **/
public class ItextpdfUtil {

    public static void main(String[] args) {
        String pdfPath = "D:\\7-11_HK_TC.pdf";
        String txtfilePath = "D:\\7-11_HK_TC-pdf2.txt";
        readPdfToTxt(pdfPath,txtfilePath); //调用读取方法
        System.out.println("==============");
        System.out.println(readPdfToTxt(pdfPath));
        System.out.println("Finished !");
    }

    /**
     * 读取PDF文件内容到txt文件
     *
     * @param pdfPath
     * @param txtfilePath
     */
    private static void readPdfToTxt(String pdfPath, String txtfilePath) {
        // 读取pdf所使用的输出流
        PrintWriter writer = null;
        PdfReader reader = null;
        try {
            writer = new PrintWriter(new FileOutputStream(txtfilePath));
            reader = new PdfReader(pdfPath);
            int num = reader.getNumberOfPages();// 获得页数
            System.out.println("Total Page: " + num);
            String content = ""; // 存放读取出的文档内容
            for (int i = 1; i <= num; i++) {
                // 读取第i页的文档内容
                content += PdfTextExtractor.getTextFromPage(reader, i);
            }

            String[] arr = content.split("/n");
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            /*String[] childArr = arr[i].split(" ");
            for(int j=0;j<childArr.length;j++){
                System.out.println(childArr[j]);
            }*/
            }

            //System.out.println(content);
            writer.write(content);// 写入文件内容
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取pdf内容
     *
     * @param pdfPath
     */

    private static String readPdfToTxt(String pdfPath) {
        PdfReader reader = null;
        StringBuffer buff = new StringBuffer();
        try {
            reader = new PdfReader(pdfPath);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            int num = reader.getNumberOfPages();// 获得页数
            TextExtractionStrategy strategy;
            for (int i = 1; i <= num; i++) {
//                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                strategy = parser.processContent(i, new LocationTextExtractionStrategy());
                buff.append(strategy.getResultantText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }
    // 读取pdf中表格 参考：https://zhidao.baidu.com/question/1951868378565173068.html
}
