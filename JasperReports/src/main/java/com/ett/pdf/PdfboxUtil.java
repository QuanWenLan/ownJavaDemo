package com.ett.pdf;

import java.io.*;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.io.RandomAccessFile;


/**
 * @author CharlieChen
 * @version 1.0 pdfbox解析pdf文件  https://blog.csdn.net/charliechen1989/article/details/42425617
 * @DateTime 2015-1-5 上午9:55:38
 */

public class PdfboxUtil {


    /**
     * @param args
     */
    public static void main(String[] args) {
        String pdfPath = "D:\\OK_HK_TC.pdf";
        String txtfilePath = "D:\\OK_HK_TC.txt";
        PdfboxUtil pdfutil = new PdfboxUtil();
        try {
            String content = pdfutil.getTextFromPdf(pdfPath);
            pdfutil.toTextFile(content, txtfilePath);
            System.out.println("Finished !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取PDF文件的文字内容
     *
     * @param pdfPath
     * @throws Exception
     */

    public String getTextFromPdf(String pdfPath) throws Exception {
        // 是否排序

        boolean sort = true;
        // 开始提取页数
        int startPage = 1;
        // 结束提取页数
        int endPage = Integer.MAX_VALUE;
        String content = null;
        InputStream input = null;
        File pdfFile = new File(pdfPath);
        PDDocument document = null;
        try {
            input = new FileInputStream(pdfFile);
            // 加载 pdf 文档
            PDFParser parser = new PDFParser(new RandomAccessFile(pdfFile,"rw"));
            parser.parse();
            document = parser.getPDDocument();
            //获取内容信息
            PDFTextStripper pts = new PDFTextStripper();
            pts.setSortByPosition(sort);
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            try {
                content = pts.getText(document);
            } catch (Exception e) {
                throw e;
            }
            System.out.println("Get PDF Content ...");
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != input)
                input.close();
            if (null != document)
                document.close();
        }


        return content;

    }


    /**
     * 把PDF文件内容写入到txt文件中
     *
     * @param pdfContent
     * @param filePath
     */

    public void toTextFile(String pdfContent, String filePath) {

        try {
            File f = new File(filePath);

            if (!f.exists()) {
                f.createNewFile();

            }

            System.out.println("Write PDF Content to txt file ...");

            BufferedWriter output = new BufferedWriter(new FileWriter(f));

            output.write(pdfContent);

            output.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
