package com.ettrade.ngbss.jasperreport.data.request.validator.userreport;

import com.ettrade.ngbss.jasperreport.data.request.validator.JasperReportRequestValidator;
import com.ettrade.ngbss.jasperreport.data.request.validator.common.SimpleDateValidator;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.bean.JasperReportRequest;
import com.google.common.base.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Map;

/**
 * @author Vin lan
 * @className DailyClientTradeStatisticsDataRequestValidator
 * @description TODO
 * @createTime 2020-09-25  12:14
 **/
public class DailyClientTradeStatisticsDataRequestValidator extends JasperReportRequestValidator {
    private SimpleDateValidator simpleDateValidator =
            new SimpleDateValidator("yyyy-MM-dd", "filter", ReturnCode.InvalidDateFormat.getValue());

    @Override
    protected void doValidate(JasperReportRequest request, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "filter", ReturnCode.NoFilterSupplied.getValue());

        Map<String, Object> filter = request.getFilter();
        String fromTradeDate = (String)filter.get("fromTradeDate");
        String toTradeDate = (String)filter.get("toTradeDate");

        // must filter
        if (Strings.isNullOrEmpty(fromTradeDate) || Strings.isNullOrEmpty(toTradeDate)) {
            errors.rejectValue("filter",ReturnCode.NoDateFilterSupplied.getValue());
        }

        simpleDateValidator.validate(fromTradeDate, errors);
        simpleDateValidator.validate(toTradeDate, errors);
    }
}
