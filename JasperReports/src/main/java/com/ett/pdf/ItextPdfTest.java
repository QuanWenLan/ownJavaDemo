package com.ett.pdf;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vin lan
 * @className ItextPdfTest  https://blog.csdn.net/nnlzb66/article/details/102873626
 * @description TODO 获取pdf每行内容
 * @createTime 2021-01-08  10:19
 **/
public class ItextPdfTest {

    public static void main(String[] args) {

        String filePath = "D:\\7-11_HK_TC.pdf";

        Map<Integer, List<TextRow>> pages = readPDF(new File(filePath));
        List<TextRow> page1 = pages.get(1);
        if (page1 != null) {
            for (TextRow row : page1) {
//                log.info("第{}行 有列数:{} y:{} 数据：{}", row.getRowIndex(), row.getColumnsNumber(), row.getY(), row.getRowContent());
                System.out.println("第" + row.getRowIndex() + "行，有列数: " + row.getColumnsNumber() + " y:" + row.getY() + " 数据: " + row.getRowContent());
            }
        }

        System.out.println("=============");
        Map<Integer, Map<Integer, Map<Integer, String>>> pages2 = readPDF2(new File(filePath));
        Map<Integer, Map<Integer, String>> pages21 = pages2.get(1);
        pages21.forEach((key, value) -> {
//            log.info("第{}行 有列数:{} 数据:{}", key, value.size(), JSON.toJSONString(value));
            System.out.println("第" + key + "行，有列数: " + value.size() + " 数据: " + JSON.toJSONString(value));
        });
    }


    /**
     * PDF文件解析，返回行数据列表
     *
     * @param file 文件
     */
    private static Map<Integer, List<TextRow>> readPDF(File file) {
        PdfReader reader;
        Map<Integer, List<TextRow>> pageMap = new HashMap<>();
        try {
            reader = new PdfReader(file.getAbsolutePath());
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                PDFTextListener renderListener = new PDFTextListener();
                renderListener.setPageNumber(page);
                PdfReaderContentParser parse = new PdfReaderContentParser(reader);
                parse.processContent(page, renderListener);
                Rectangle rectangle = reader.getPageSize(page);
//                log.info("第{}页：宽：{}，高:{}，左：{}，右：{}", page, rectangle.getWidth(), rectangle.getHeight(), rectangle.getLeft(), rectangle.getRight());
                System.out.println("第{}页：" + page + "宽：{}" + rectangle.getWidth() + "，高:{}" + rectangle.getHeight() + "，左：{}" + rectangle.getLeft() + "，右：{}" + rectangle.getRight());
                renderListener.sortAndAddToAllRows();
                pageMap.put(page, renderListener.getAllRows());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageMap;
    }

    /**
     * PDF文件解析，返回(行，列，内容字典)
     *
     * @param file 文件
     */
    private static Map<Integer, Map<Integer, Map<Integer, String>>> readPDF2(File file) {
        PdfReader reader;
        Map<Integer, Map<Integer, Map<Integer, String>>> pageMap = new HashMap<>();
        try {
            reader = new PdfReader(file.getAbsolutePath());
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                PDFTextListener renderListener = new PDFTextListener();
                renderListener.setPageNumber(page);
                PdfReaderContentParser parse = new PdfReaderContentParser(reader);
                parse.processContent(page, renderListener);
                Rectangle rectangle = reader.getPageSize(page);
//                log.info("第{}页：宽：{}，高:{}，左：{}，右：{}", page, rectangle.getWidth(), rectangle.getHeight(), rectangle.getLeft(), rectangle.getRight());
                System.out.println("第{}页：" + page + "宽：{}" + rectangle.getWidth() + "，高:{}" + rectangle.getHeight() + "，左：{}" + rectangle.getLeft() + "，右：{}" + rectangle.getRight());
                renderListener.sortAndAddToAllRows();
                pageMap.put(page, renderListener.getPageContentMap());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageMap;
    }

}
