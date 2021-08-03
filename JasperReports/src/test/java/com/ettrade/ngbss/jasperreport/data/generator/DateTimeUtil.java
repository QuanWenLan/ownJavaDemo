package com.ettrade.ngbss.jasperreport.data.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Vin lan
 * @className DateTimeUtil
 * @description TODO utility class for handle the date time interval
 * @createTime 2020-09-28  16:32
 **/
public class DateTimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);


    public static final DateTimeFormatter TIME_STAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * use the 'yyyy-MM-dd HH:mm:ss' time format
     *
     * @param currentDateTime target
     * @param startDateTime   start
     * @param endDateTime     end
     */
    public static boolean verifyDateTimeInTwoDateInterval(LocalDateTime currentDateTime, String startDateTime, String endDateTime, DateTimeFormatter formatter) {
        if ("".equals(startDateTime) || "".equals(endDateTime) || null == currentDateTime) {
            return false;
        }
        try {
            LocalDateTime startLocalDateTime = LocalDateTime.parse(startDateTime, formatter);
            LocalDateTime endLocalDateTime = LocalDateTime.parse(endDateTime, formatter);
            if (currentDateTime.isAfter(startLocalDateTime) || currentDateTime.equals(startLocalDateTime)) {
                if (currentDateTime.isBefore(endLocalDateTime) || currentDateTime.equals(endLocalDateTime)) {
                    return true;
                }
            }
            return false;
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            LOGGER.error("DateTimeParseException:{}", ex.getMessage());
        }
        return false;
    }

    public static int selectWhichTable(LocalDateTime currentDateTime, String startDateTime, String endDateTime, DateTimeFormatter formatter) {
        if ("".equals(startDateTime) || "".equals(endDateTime) || null == currentDateTime) {
            return 0;
        }
        try {
            LocalDate startLocalDate = LocalDate.parse(startDateTime, formatter);
            LocalDate endLocalDate = LocalDate.parse(endDateTime, formatter);
            LocalDate currentDate = currentDateTime.toLocalDate();
            if (currentDate.isAfter(startLocalDate) && currentDate.isAfter(endLocalDate)) { // only select history
                return -1;
            } else if (currentDate.isAfter(startLocalDate) && (currentDate.isEqual(endLocalDate) || currentDate.isBefore(endLocalDate))) { // select now and history
                return 2;
            } else if ((currentDate.equals(startLocalDate) && (currentDate.equals(endLocalDate) || currentDate.isBefore(endLocalDate))) ||
                    (currentDate.isBefore(startLocalDate) && currentDate.isBefore(endLocalDate))) { // only select now
                return 1;
            }
            return 0;
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            LOGGER.error("DateTimeParseException:{}", ex.getMessage());
        }
        return 0;
    }
}
