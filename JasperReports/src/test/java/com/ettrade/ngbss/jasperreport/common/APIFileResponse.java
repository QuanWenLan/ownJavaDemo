package com.ettrade.ngbss.jasperreport.common;

import com.ettrade.ngts.trading.api.action.HttpHeader;

import java.io.File;
import java.util.Map;

/**
 * @version $Id$
 */

public class APIFileResponse extends APIResponse implements HttpHeader {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }        
    
    @Override
    public String getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public void setHeader(String key, String value) {
        headers.put(key, value);
    }
   
}
