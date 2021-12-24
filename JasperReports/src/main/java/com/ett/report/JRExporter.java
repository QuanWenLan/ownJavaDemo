package com.ett.report;

import net.sf.jasperreports.engine.JasperPrint;

public interface JRExporter extends JRConstants {

    public void export(JasperPrint print);

}
