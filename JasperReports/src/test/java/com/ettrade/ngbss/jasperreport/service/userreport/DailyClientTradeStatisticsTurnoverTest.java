package com.ettrade.ngbss.jasperreport.service.userreport;

import com.ettrade.ngbss.jasperreport.common.JasperReportId;
import com.ettrade.ngbss.jasperreport.service.ReportServiceTest;
import com.ettrade.ngts.trading.api.bean.JasperReportRequest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Vin lan
 * @className DailyClientTradeStatisticsTurnoverTest
 * @description TODO
 * @createTime 2020-09-23  16:57
 **/
public class DailyClientTradeStatisticsTurnoverTest extends ReportServiceTest {
    private String report = JasperReportId.DailyClientTradeStatisticsReport.getName();

    // verify the request success
    @Test
    public void validatorRequestSuccess() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("fromTradeDate", "2020-09-01"); // must
        filters.put("toTradeDate", "2020-10-10"); // must

        JasperReportRequest request = new JasperReportRequest();
        request.setReport(report);
        request.setFileType("pdf");
//        request.setEpId("IFT10");  // 用来连接数据库的一个属性
        request.setFilter(filters);

        assertDoesNotThrow(() -> {
            reportService.validateRequest(request);
        });
    }

    @Test
    public void generateReportSuccess() {
        JasperReportRequest request = getSuccessfulRequest();
        assertAndGenerateReport(request, "report_test_A");
    }

    // verify the right time format
    private JasperReportRequest getSuccessfulRequest() {
//        String locale = "en_US";
        String locale = "zh_CN";
        HashMap<String, Object> filters = new HashMap<>();
        // use this time format
        filters.put("fromTradeDate", "2020-11-11"); // must
        filters.put("toTradeDate", "2020-11-14"); // must
        /*filters.put("userId", "LT16"); // optional
        filters.put("exchangeId", "HKG"); // optional
        filters.put("channel", "ET"); // optional
        filters.put("AEGroup", "1"); // optional,  user_group_id==AEGroup*/

        JasperReportRequest request = new JasperReportRequest();
        request.setReport(report);
        request.setFileType("pdf");
//        request.setEpId("IFT10");
        request.setFilter(filters);
        request.setLocale(locale);
        return request;
    }

    // verify the wrong time format
    @Test
    public void validatorErrorDateFormatRequestSuccess() {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("fromTradeDate", "20200923"); // must
        filters.put("toTradeDate", "20200925"); // must

        JasperReportRequest request = new JasperReportRequest();
        request.setReport(report);
        request.setFileType("pdf");
//        request.setEpId("IFT10");
        request.setFilter(filters);

        assertDoesNotThrow(() -> {
            reportService.validateRequest(request);
        });
    }

    // verify the missing time
    @Test
    public void validatorMissingDateRequestSuccess() {
        // 目前还没设置参数验证
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("tradeDate", "");

        JasperReportRequest request = new JasperReportRequest();
        request.setReport(report);
        request.setFileType("pdf");
//        request.setEpId("IFT10");
        request.setFilter(filters);

        assertDoesNotThrow(() -> {
            reportService.validateRequest(request);
        });
    }

    @Test
    public void fullTestSuccess() {
        JasperReportRequest request = getSuccessfulRequest();

        assertDoesNotThrow(() -> {
            reportService.validateRequest(request);
        });
        assertAndGenerateReport(request, "report_test_B");
    }

}
