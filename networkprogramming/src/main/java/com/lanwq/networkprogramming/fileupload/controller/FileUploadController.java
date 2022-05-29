package com.lanwq.networkprogramming.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: javaDemo->FileUploadController
 * @description: 文件上传
 * @author: lanwenquan
 * @date: 2022-04-24 20:26
 */
@Controller
public class FileUploadController {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response) {

        return "hello";
    }
}
