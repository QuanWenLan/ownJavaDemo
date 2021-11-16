package com.ettrade.ngbss.jasperreport.common;

import com.ettrade.ngbss.jasperreport.data.request.validator.JasperReportRequestValidator;
import com.ettrade.ngbss.jasperreport.data.request.validator.userreport.*;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JasperReportId {
    DailyClientTradeStatisticsReport("7006", DailyClientTradeStatisticsDataRequestValidator.class),
    Undefined("-1", JasperReportRequestValidator.class);

    private final String name; // request report name
    private final Class<? extends Validator> validatorClass;

    JasperReportId(String name, Class<? extends Validator> validatorClass) {
        this.name = name;
        this.validatorClass = validatorClass;
    }

    public static JasperReportId getJasperReportId(String reportName) {
        for (JasperReportId jasperReportId : JasperReportId.values()) {
            if (jasperReportId.getName().equals(reportName)) {
                return jasperReportId;
            }
        }
        return Undefined;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Validator> getValidatorClass() {
        return validatorClass;
    }

    public Validator getValidator() {
        try {
            return validatorClass.getConstructor().newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static List<Integer> getReportIds() {
        return Arrays.stream(JasperReportId.values()).map(reportId -> Integer.parseInt(reportId.getName())).collect(
            Collectors.toList());
    }
}
