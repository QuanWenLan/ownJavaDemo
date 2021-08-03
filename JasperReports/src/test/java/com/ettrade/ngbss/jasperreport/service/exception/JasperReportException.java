package com.ettrade.ngbss.jasperreport.service.exception;

import com.ettrade.ngts.commons.bean.ReturnCode;
import org.springframework.validation.Errors;

public class JasperReportException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ReturnCode code;

    public JasperReportException(ReturnCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public JasperReportException(Errors errors) {
        this(ReturnCode.getReturnCode(errors.getFieldError().getCode()));
    }

    public ReturnCode getCode() {
        return code;
    }
}
