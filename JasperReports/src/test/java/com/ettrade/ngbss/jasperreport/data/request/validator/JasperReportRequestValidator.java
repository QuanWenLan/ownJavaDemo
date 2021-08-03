package com.ettrade.ngbss.jasperreport.data.request.validator;

import com.ettrade.ngbss.jasperreport.common.JasperFileType;
import com.ettrade.ngbss.jasperreport.common.JasperReportId;
import com.ettrade.ngbss.jasperreport.common.SortKey;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.bean.JasperReportRequest;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class JasperReportRequestValidator implements Validator {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public final boolean supports(Class<?> clazz) {
        return JasperReportRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "report", ReturnCode.NoReportSupplied.getValue());
        ValidationUtils.rejectIfEmpty(errors, "fileType", ReturnCode.NoFileTypeSupplied.getValue());
        ValidationUtils.rejectIfEmpty(errors, "epId", ReturnCode.NoEpIdSupplied.getValue());

        JasperReportRequest request = (JasperReportRequest) target;

        if (request.getReport() == JasperReportId.Undefined) {
            errors.rejectValue("report", ReturnCode.InvalidReportSupplied.getValue());
        }
        if (request.getFileType() == JasperFileType.Undefined) {
            errors.rejectValue("fileType", ReturnCode.InvalidFileTypeSupplied.getValue());
        }

        doValidate(request, errors);
    }

    protected void doValidate(JasperReportRequest request, Errors errors) {

    }

    /* ************************************** utilties method ***************************************/

    public void validateFilterOnExpectedType(
        Map<String, Object> filters,
        Errors errors,
        String filterName,
        Class clazz,
        boolean allowEmpty) {
        validateFilterOnExpectedType(filters, errors, filterName, clazz, allowEmpty, null);
    }

    public void validateFilterOnExpectedType(
        Map<String, Object> filters,
        Errors errors,
        String filterName,
        Class clazz,
        boolean allowEmpty,
        BiConsumer<Object, Errors> consumer) {
        Object filterValue = filters.get(filterName);
        if (filterValue == null) {
            if (allowEmpty) {
                return;
            }
            errors.rejectValue("filter", ReturnCode.NoFilterSupplied.getValue());
            return;
        }

        boolean isSameClass = clazz.isAssignableFrom(filterValue.getClass());
        if (!isSameClass) {
            log.error("class not matched; filterName: {}, expected: {}, actual: {}", filterName, clazz.getName(), filterValue.getClass().getName());
            errors.rejectValue("filter", ReturnCode.InvalidFormatSupplied.getValue());
        }

        if (consumer != null) {
            consumer.accept(filterValue, errors);
        }
    }

    public void validateSortKeys(
        JasperReportRequest request,
        Errors errors,
        Collection<String> permittedAttributes,
        boolean allowEmpty) {
        List<SortKey> sortKeys = request.getSorting();
        if (sortKeys == null || sortKeys.isEmpty()) {
            if (allowEmpty) {
                return;
            }
            errors.rejectValue("sorting", ReturnCode.NoSortingSupplied.getValue());
        }

        List<String> sortStrs = sortKeys.stream().map(sortKey -> sortKey.getAttribute()).collect(Collectors.toList());
        boolean isNotPermitted = Iterables.any(sortStrs, (sortKey) -> !permittedAttributes.contains(sortKey));
        if (isNotPermitted) {
            errors.rejectValue("sorting", ReturnCode.InvalidSortingField.getValue());
        }
    }
}
