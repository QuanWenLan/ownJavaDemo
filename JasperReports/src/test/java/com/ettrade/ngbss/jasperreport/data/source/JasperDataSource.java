package com.ettrade.ngbss.jasperreport.data.source;

import net.sf.jasperreports.engine.JRParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class JasperDataSource<T> {
    private T source;
    private Map<String, Object> parameterMap = new HashMap<>();

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void addParameter(String key, Object value) {
        this.parameterMap.put(key, value);
    }

    public Object getParameter(String key) {
        return this.parameterMap.get(key);
    }

    // utilities method
    public ResourceBundle getResourceBundle() {
        return (ResourceBundle) getParameter(JRParameter.REPORT_RESOURCE_BUNDLE);
    }
}
