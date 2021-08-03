package com.ettrade.ngbss.jasperreport.exporter.pdf;

import net.sf.jasperreports.export.SimplePdfReportConfiguration;

public class DefaultJasperPDFFileExporter extends JasperPDFFileExporter {
    public DefaultJasperPDFFileExporter() {
        SimplePdfReportConfiguration defaultConfiguration = new SimplePdfReportConfiguration();
        setReportConfiguration(defaultConfiguration);
    }
}
