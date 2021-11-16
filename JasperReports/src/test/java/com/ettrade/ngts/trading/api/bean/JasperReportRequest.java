package com.ettrade.ngts.trading.api.bean;

import com.ettrade.ngbss.jasperreport.common.JasperFileType;
import com.ettrade.ngbss.jasperreport.common.JasperReportId;
import com.ettrade.ngbss.jasperreport.common.SortKey;
import org.apache.commons.lang3.LocaleUtils;

import java.util.*;
import java.util.stream.Collectors;

public class JasperReportRequest {
    private JasperReportId report; // which report to have (should have a mapping in spring config)
    private JasperFileType fileType; // pdf / excel
    private Map<String, Object> filter = new HashMap<>();
    private List<SortKey> sorting = new ArrayList<>();
    private Locale locale;

    public JasperReportId getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = JasperReportId.getJasperReportId(report);
    }

    public JasperFileType getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = JasperFileType.getFileType(fileType);
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

    // todo how to do exception handling here? inside logic should be placed as static method in SortKey
    public void setSorting(List<String> sortingStrs) {
        this.sorting = sortingStrs.stream().map(SortKey::fromString).collect(Collectors.toList());
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(String localeStr) {
        this.locale = LocaleUtils.toLocale(localeStr);
    }
}
