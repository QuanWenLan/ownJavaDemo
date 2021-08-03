package com.ettrade.ngbss.jasperreport.common;

import java.util.*;

public class JasperReportContext {
    private Locale locale;
    private JasperFileType fileType; // pdf / excel
    private Map<String, Object> filter = new HashMap<>();
    private List<SortKey> sorting = new ArrayList<>();
    private String localeBasePath;

    // newly added attribute not from Request
    private String epId;
    // todo add userId for requesting the JasperReport as Actor, used in Report
    //    private Actor actor; // user who creates current report

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public JasperFileType getFileType() {
        return fileType;
    }

    public void setFileType(JasperFileType fileType) {
        this.fileType = fileType;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public List<SortKey> getSorting() {
        return sorting;
    }

    public void setSorting(List<SortKey> sorting) {
        this.sorting = sorting;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getLocaleBasePath() {
        return localeBasePath;
    }

    public void setLocaleBasePath(String localeBasePath) {
        this.localeBasePath = localeBasePath;
    }
}
