package com.ettrade.ngbss.jasperreport.data.request.validator.common;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SimpleDateValidator implements Validator {
    private final String dateFormat;
    private final String field; // correponding to which field it is, e.g. filter
    private final String errorCode;

    public SimpleDateValidator(String dateFormat, String field, String errorCode) {
        this.dateFormat = dateFormat;
        this.field = field;
        this.errorCode = errorCode;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object date, Errors errors) {
        if (!(date instanceof String)) {
            errors.rejectValue(field, errorCode);
        }

        String dateStr = (String) date;
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);

        try {
            formatter.parseDateTime(dateStr);
        }
        catch (Exception ex) {
            errors.rejectValue(field, errorCode);
        }
    }
}
