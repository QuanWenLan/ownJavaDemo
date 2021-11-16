package com.ettrade.ngbss.jasperreport.data.generator.clientacreport;

import com.ettrade.ngbss.jasperreport.common.JasperReportContext;
import com.ettrade.ngbss.jasperreport.data.datastruct.userreport.dailyclientstatisticsreport.DailyClientStatisticsData;
import com.ettrade.ngbss.jasperreport.data.datastruct.userreport.dailyclientstatisticsreport.DailyClientStatisticsDataModel;
import com.ettrade.ngbss.jasperreport.data.generator.DateTimeUtil;
import com.ettrade.ngbss.jasperreport.data.generator.DefaultJasperDataGenerator;
import com.ettrade.ngbss.jasperreport.data.source.JasperDataSource;
import com.ettrade.ngbss.jasperreport.data.store.StoreUtil;
import com.google.common.base.Strings;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author Vin lan
 * @className DailyClientTradeStatisticsGenerator
 * @description TODO
 * @createTime 2020-09-25  11:58
 **/
public class DailyClientTradeStatisticsReportGenerator extends DefaultJasperDataGenerator<JRDataSource> {
    @Override
    protected CompletableFuture<JasperDataSource<JRDataSource>> doGenerate(JasperReportContext context, JasperDataSource<JRDataSource> dataSource) {
        return CompletableFuture.supplyAsync(() -> {
            // get all the requirement
            Map<String, Object> filter = context.getFilter();
            String fromTradeDate = (String) filter.get("fromTradeDate");
            String toTradeDate = (String) filter.get("toTradeDate");
            String userId = (String) filter.get("userId");
            String exchangeId = (String) filter.get("exchangeId");
            String channel = (String) filter.get("channel");
            String AEGroup = (String) filter.get("AEGroup");

            List<DailyClientStatisticsData> allDailyClientStatisticsDataList = new ArrayList<>();
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValues(filter);
            DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd");
            DateTime fDate = formatter.parseDateTime(fromTradeDate);
            DateTime tDate = formatter.parseDateTime(toTradeDate);
            sqlParameterSource.addValue("fromTradeDate", new Timestamp(fDate.toDate().getTime()),
                    Types.TIMESTAMP);
            sqlParameterSource.addValue("toTradeDate", new Timestamp(tDate.plusDays(1).minus(1).toDate().getTime()),
                    Types.TIMESTAMP);
            HashMap<String, long[]> map = new HashMap<>();

            String tradeTable = "trade"; // as a default
            String securityOrderTable = "security_order";// as a default
            int tableNums = DateTimeUtil.selectWhichTable(LocalDateTime.now(), fromTradeDate, toTradeDate,
                    DateTimeUtil.DATE_FORMATTER);

            if (tableNums == 1 || tableNums == 2) { // now
                tradeTable = "trade";
                securityOrderTable = "security_order";
                StringBuilder orderSb = new StringBuilder("SELECT DATE_FORMAT(so.fill_time, '%Y-%m-%d') AS format_fill_time, so.* \n" +
                        " FROM " + securityOrderTable + " so, \n" +
                        " (SELECT\n" +
                        "   s.exchange_id, s.orig_order_id, ug.user_group_id, ug.user_group_name\n" +
                        "  FROM\n" +
                        "   " + securityOrderTable + " s LEFT JOIN (SELECT ug.*, ugm.user_id FROM user_group ug, user_group_map ugm WHERE ug.user_group_id = ugm.user_group_id) ug \n" +
                        "   ON s.user_id = ug.user_id\n" +
                        "   WHERE s.user_id IS NOT NULL\n" +
                        "   GROUP BY\n" +
                        "   ug.user_group_id, ug.user_group_name, s.exchange_id, s.orig_order_id) tmp\n" +
                        "   WHERE so.exchange_id  = tmp.exchange_id AND so.orig_order_id = tmp.orig_order_id\n" +
                        "  AND so.fill_time >=  :fromTradeDate\n" +
                        "  AND so.fill_time <=  :toTradeDate\n");

                StringBuilder allTradeSb = new StringBuilder("SELECT\n" +
                        " allTrade.*,\n" +
                        " so.channel,\n" +
                        " so.order_status ,\n" +
                        " so.cumulative_fill_qty\n" +
                        " FROM\n" +
                        " (SELECT\n" +
                        "  tr.exchange_id, tr.execution_id, tr.match_type, tr.orig_order_id,\n" +
                        "  tr.fill_time, DATE_FORMAT(tr.fill_time, '%Y-%m-%d') AS format_fill_time, \n" +
                        "  tr.currency, tr.trade_cancel_flag, tr.book_in_flag,  tr.fill_price,\n" +
                        "  tr.fill_qty, (tr.fill_price * tr.fill_qty) AS current_amount\n" +
                        " FROM\n" +
                        "  " + tradeTable + " tr\n" +
                        " INNER JOIN (\n" +
                        "  SELECT\n" +
                        "   t.exchange_id, t.orig_order_id, ug.user_group_id, ug.user_group_name\n" +
                        "  FROM\n" +
                        "   " + tradeTable + " t LEFT JOIN (SELECT ug.*, ugm.user_id FROM user_group ug, user_group_map ugm WHERE ug.user_group_id = ugm.user_group_id) ug\n" +
                        " ON t.user_id = ug.user_id\n" +
                        " WHERE t.user_id IS NOT NULL \n" +
                        "  GROUP BY\n" +
                        "   ug.user_group_id, ug.user_group_name, t.exchange_id, t.orig_order_id ) tmp ON\n" +
                        "  ( tr.exchange_id = tmp.exchange_id AND tr.orig_order_id = tmp.orig_order_id )\n" +
                        "  WHERE tr.fill_time >= :fromTradeDate\n" +
                        "  AND tr.fill_time <= :toTradeDate\n");
                if (!Strings.isNullOrEmpty(AEGroup)) {
                    orderSb.append(" AND tmp.user_group_id = :AEGroup \n");
                    allTradeSb.append(" AND tmp.user_group_id = :AEGroup \n");
                }
                if (!Strings.isNullOrEmpty(userId)) {
                    orderSb.append(" AND so.user_id = :userId \n");
                    allTradeSb.append(" AND tr.user_id = :userId \n");
                }
                if (!Strings.isNullOrEmpty(exchangeId)) {
                    orderSb.append(" AND so.exchange_id = :exchangeId \n");
                    allTradeSb.append(" AND tr.exchange_id = :exchangeId \n");
                }

                allTradeSb.append(" ) allTrade\n" +
                        " LEFT JOIN " + securityOrderTable + " so ON\n" +
                        " (allTrade.exchange_id = so.exchange_id AND allTrade.orig_order_id = so.orig_order_id)\n");
                if (!Strings.isNullOrEmpty(channel)) {
                    orderSb.append(" AND so.channel = :channel \n");
                    allTradeSb.append("WHERE so.channel = :channel \n");
                }

                orderSb.append(" ORDER BY so.fill_time;");
                allTradeSb.append(" ORDER BY allTrade.fill_time;");

                StoreUtil.getTSStore(context.getEpId()).query(orderSb.toString(),
                        sqlParameterSource, (ResultSet rs, int rowNum) -> {
                            String orderKey = rs.getString("format_fill_time") + "|" + rs.getString("currency");
                            if (!map.containsKey(orderKey)) {
                                map.put(orderKey, new long[]{0, 0});
                            }
                            long[] arr = map.get(orderKey);
                            if ("F".equals(rs.getString("order_status"))) {
                                arr[0]++;
                            }
                            arr[1] += rs.getLong("cumulative_fill_qty");
                            return null;
                        });
                allDailyClientStatisticsDataList.addAll(StoreUtil.getTSStore(context.getEpId()).
                        simpleQuery(allTradeSb.toString(), sqlParameterSource, DailyClientStatisticsData.class));
            }

            if (tableNums == -1 || tableNums == 2) { // history
                tradeTable = "trade_hist";
                securityOrderTable = "security_order_hist";
                StringBuilder orderSbHist = new StringBuilder("SELECT DATE_FORMAT(so.fill_time, '%Y-%m-%d') AS format_fill_time, so.* \n" +
                        " FROM " + securityOrderTable + " so, \n" +
                        " (SELECT\n" +
                        "   s.exchange_id, s.orig_order_id, ug.user_group_id, ug.user_group_name\n" +
                        "  FROM\n" +
                        "   " + securityOrderTable + " s LEFT JOIN (SELECT ug.*, ugm.user_id FROM user_group ug, user_group_map ugm WHERE ug.user_group_id = ugm.user_group_id) ug \n" +
                        "   ON s.user_id = ug.user_id\n" +
                        "   WHERE s.user_id IS NOT NULL\n" +
                        "  GROUP BY\n" +
                        "   ug.user_group_id, ug.user_group_name, s.exchange_id, s.orig_order_id) tmp\n" +
                        "   WHERE so.exchange_id  = tmp.exchange_id AND so.orig_order_id = tmp.orig_order_id\n" +
                        "  AND so.fill_time >=  :fromTradeDate\n" +
                        "  AND so.fill_time <=  :toTradeDate\n");

                StringBuilder allTradeSbHist = new StringBuilder("SELECT\n" +
                        " allTrade.*,\n" +
                        " so.channel,\n" +
                        " so.order_status ,\n" +
                        " so.cumulative_fill_qty\n" +
                        " FROM\n" +
                        " (SELECT\n" +
                        "  tr.exchange_id, tr.execution_id, tr.match_type, tr.orig_order_id,\n" +
                        "  tr.fill_time, DATE_FORMAT(tr.fill_time, '%Y-%m-%d') AS format_fill_time, \n" +
                        "  tr.currency, tr.trade_cancel_flag, tr.book_in_flag,  tr.fill_price,\n" +
                        "  tr.fill_qty, (tr.fill_price * tr.fill_qty) AS current_amount\n" +
                        " FROM\n" +
                        "  " + tradeTable + " tr\n" +
                        " INNER JOIN (\n" +
                        "  SELECT\n" +
                        "   t.exchange_id, t.orig_order_id, ug.user_group_id, ug.user_group_name\n" +
                        "  FROM\n" +
                        "   " + tradeTable + " t LEFT JOIN (SELECT ug.*, ugm.user_id FROM user_group ug, user_group_map ugm WHERE ug.user_group_id = ugm.user_group_id) ug\n" +
                        "  ON t.user_id = ug.user_id\n" +
                        "  WHERE t.user_id IS NOT NULL \n" +
                        "  GROUP BY\n" +
                        "   ug.user_group_id, ug.user_group_name, t.exchange_id, t.orig_order_id ) tmp ON\n" +
                        "  ( tr.exchange_id = tmp.exchange_id AND tr.orig_order_id = tmp.orig_order_id )\n" +
                        "  WHERE tr.trade_cancel_flag = 'N'" +
                        "  AND tr.fill_time >= :fromTradeDate\n" +
                        "  AND tr.fill_time <= :toTradeDate\n");
                if (!Strings.isNullOrEmpty(AEGroup)) {
                    orderSbHist.append(" AND tmp.user_group_id = :AEGroup \n");
                    allTradeSbHist.append(" AND tmp.user_group_id = :AEGroup \n");
                }
                if (!Strings.isNullOrEmpty(userId)) {
                    orderSbHist.append(" AND so.user_id = :userId \n");
                    allTradeSbHist.append(" AND tr.user_id = :userId \n");
                }
                if (!Strings.isNullOrEmpty(exchangeId)) {
                    orderSbHist.append(" AND so.exchange_id = :exchangeId \n");
                    allTradeSbHist.append(" AND tr.exchange_id = :exchangeId \n");
                }

                allTradeSbHist.append(" ) allTrade\n" +
                        " LEFT JOIN " + securityOrderTable + " so ON\n" +
                        " (allTrade.exchange_id = so.exchange_id AND allTrade.orig_order_id = so.orig_order_id)\n");
                if (!Strings.isNullOrEmpty(channel)) {
                    orderSbHist.append(" AND so.channel = :channel \n");
                    allTradeSbHist.append("WHERE so.channel = :channel \n");
                }

                orderSbHist.append(" ORDER BY so.fill_time;");
                allTradeSbHist.append(" ORDER BY allTrade.fill_time;");

                StoreUtil.getTSStore(context.getEpId()).query(orderSbHist.toString(),
                        sqlParameterSource, (ResultSet rs, int rowNum) -> {
                            String orderKey = rs.getString("format_fill_time") + "|" + rs.getString("currency");
                            if (!map.containsKey(orderKey)) {
                                map.put(orderKey, new long[]{0, 0});
                            }
                            long[] arr = map.get(orderKey);
                            if ("F".equals(rs.getString("order_status"))) {
                                arr[0]++;
                            }
                            arr[1] += rs.getLong("cumulative_fill_qty");
                            return null;
                        });
                allDailyClientStatisticsDataList.addAll(StoreUtil.getTSStore(context.getEpId()).
                        simpleQuery(allTradeSbHist.toString(), sqlParameterSource, DailyClientStatisticsData.class));
            }
            if (tableNums == 0) {
                log.info("time range is not appropriate:fromTradeDate:【{}】 -- toTradeDate:【{}】", fromTradeDate, toTradeDate);
            }
            log.debug("data size:{}", allDailyClientStatisticsDataList.size());

            HashMap<String, DailyClientStatisticsDataModel> dataModelHashMap = new HashMap<>();
            for (DailyClientStatisticsData statisticsData : allDailyClientStatisticsDataList) {
                if (statisticsData == null) continue;

                String matchType = statisticsData.getMatchType(), tradeCancelFlag = statisticsData.getTradeCancelFlag(),
                        bookInFlag = statisticsData.getBookInFlag();
                Timestamp formatFillTime = statisticsData.getFormatFillTime();
                // 2020-09-23|HKD
                String tradeDateKey = formatFillTime.toLocalDateTime().toLocalDate().toString() + "|" + statisticsData.getCurrency();
                DailyClientStatisticsDataModel model;
                if (!dataModelHashMap.containsKey(tradeDateKey)) {
                    model = new DailyClientStatisticsDataModel();
                    // set some constant
                    model.setCurrency(statisticsData.getCurrency());
                    model.setTradeDate(formatFillTime);
                    model.setGtsLiteFilledOrderCnt(0);
                    dataModelHashMap.put(tradeDateKey, model);
                }
                model = dataModelHashMap.get(tradeDateKey);

                if (!"".equals(tradeCancelFlag) && "N".equals(tradeCancelFlag)) {
                    // cumulative the manual trade cnt and qty
                    if ("M".equals(matchType) && "N".equals(bookInFlag)) {
                        model.addManualTradeCnt();
                        model.addManualTradeQty(statisticsData.getFillQty());
                    }
                    // cumulative the amount
                    model.addTotalAmount(statisticsData.getCurrentAmount());
                    // cumulative the bookInTrade cnt and qty
                    if (!"".equals(bookInFlag) && "Y".equals(bookInFlag)) {
                        model.addBookInTradeCnt();
                        model.addBookInTradeQty(statisticsData.getFillQty());
                    }
                }
            }
            // filledOrderCnt and totalFilledQty
            for (Map.Entry<String, DailyClientStatisticsDataModel> entry : dataModelHashMap.entrySet()) {
                String key = entry.getKey();
                DailyClientStatisticsDataModel value = entry.getValue();
                if (null != map.get(key)) {
                    long[] arr = map.get(key);
                    value.setFilledOrderCnt((int) arr[0]);
                    value.setTotalFilledQty(arr[1]);
                }
            }
            log.debug("success generating report data");
            ArrayList<DailyClientStatisticsDataModel> resultList = new ArrayList<>(dataModelHashMap.values());
            log.debug("finally data:{}, size:{}", resultList, resultList.size());

            ResourceBundle resourceBundle = dataSource.getResourceBundle();
            String companyName = resourceBundle.getString("companyName");
            String filterFromDate = resourceBundle.getString("filter.fromDate");
            String filterToDate = resourceBundle.getString("filter.toDate");
            fromTradeDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(fDate.toDate());
            toTradeDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(tDate.toDate());
            dataSource.addParameter("filter",
                    filterFromDate + " : " + fromTradeDate + " To " + filterToDate + " : " + toTradeDate);
            dataSource.addParameter("companyName", companyName);
            dataSource.addParameter("author", resourceBundle.getString("author"));
            dataSource.setSource(new JRBeanCollectionDataSource(resultList));
            return dataSource;
        }, executorService);
    }
}
