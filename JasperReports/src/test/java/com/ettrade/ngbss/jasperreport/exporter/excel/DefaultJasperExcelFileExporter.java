package com.ettrade.ngbss.jasperreport.exporter.excel;

import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class DefaultJasperExcelFileExporter extends JasperExcelFileExporter {
    public DefaultJasperExcelFileExporter() {
        SimpleXlsReportConfiguration defaultConfiguration = new SimpleXlsReportConfiguration();
        defaultConfiguration.setOnePagePerSheet(false); // if true, will have lots of Excel Sheets
        defaultConfiguration.setDetectCellType(true);
        defaultConfiguration.setCollapseRowSpan(true); // to remove empty rows in the middle
        defaultConfiguration.setWhitePageBackground(false); // to remove white page background (UI issue)
        setReportConfiguration(defaultConfiguration);
    }
}
