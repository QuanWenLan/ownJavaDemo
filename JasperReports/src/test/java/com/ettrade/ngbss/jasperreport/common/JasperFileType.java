package com.ettrade.ngbss.jasperreport.common;

public enum JasperFileType {
    PDF("pdf"),
    EXCEL("xls"),
    Undefined("");

    private final String extension;

    JasperFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static JasperFileType getFileType(String fileTypeStr) {
        for (JasperFileType fileType : JasperFileType.values()) {
            if (fileType.name().toLowerCase().equals(fileTypeStr)) {
                return fileType;
            }
        }
        return Undefined;
    }
}
