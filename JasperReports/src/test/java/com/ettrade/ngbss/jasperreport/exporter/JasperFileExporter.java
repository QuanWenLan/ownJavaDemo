package com.ettrade.ngbss.jasperreport.exporter;

import com.ettrade.ngbss.jasperreport.common.JasperReportResult;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperFileExporter {
    JasperReportResult export(JasperPrint print) throws JRException;
}
