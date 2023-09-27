package com.lanwq.networkprogramming.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author Lan
 * @createTime 2023-08-14  21:46
 **/
@RestController("/file")
public class FileUploadDownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse res) throws Exception {
        File excelFile = new File("E:\\BaiduNetdiskDownload\\test.xlsx");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String realFileName = excelFile.getName();
        res.setHeader("Content-type", "application/octet-stream;charset=UTF-8");
        res.setContentType("application/octet-stream;charset=UTF-8");
        // 加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        res.addHeader("Content-Length", String.valueOf(excelFile.length()));
        try {
            res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realFileName.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(Files.newInputStream(excelFile.toPath()));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    @GetMapping(value = "/download2")
    public ResponseEntity<StreamingResponseBody> download2(HttpServletRequest request, HttpServletResponse res) throws Exception {
        File excelFile = new File("E:\\BaiduNetdiskDownload\\test.xlsx");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String realFileName = excelFile.getName();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + URLEncoder.encode(realFileName, "UTF-8") + "\"")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(excelFile.length()))
                .header(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
                .body(outputStream -> {
                    try (FileInputStream fileInputStream = new FileInputStream(excelFile)) {
                        StreamUtils.copy(fileInputStream, outputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
