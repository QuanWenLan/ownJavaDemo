package com.ett.report;

import net.sf.jasperreports.engine.JRDataSource;

public interface OFTPJRDataSource extends JRDataSource {

    public abstract int initDataSource(JRContext context);

}
