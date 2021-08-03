package com.ettrade.ngbss.jasperreport.common;

import com.ettrade.ngts.commons.bean.ReturnCode;

import java.io.InputStream;

public class JasperReportResult {
    private ReturnCode returnCode;
    private String fileName;
    private InputStream inputStream;
    private String contentType;

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public JasperReportResult setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public JasperReportResult setFileName(String fileName, JasperFileType fileType) {
        this.fileName = String.format("%s.%s", fileName, fileType.getExtension());
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public JasperReportResult setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public JasperReportResult setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
}
