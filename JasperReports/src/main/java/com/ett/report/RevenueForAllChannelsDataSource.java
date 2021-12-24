package com.ett.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className RevenueForAllChannelsDataSource
 * @description TODO
 * @createTime 2020-12-14  17:30
 **/
public class RevenueForAllChannelsDataSource extends AbstractBeanDataSource implements JRConstants {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(RevenueForAllChannelsDataSource.class);

    @Override
    public int initDataSource(JRContext context) {
        logger.debug(">>>>>>> RevenueForAllChannelsDataSource initDataSource(JRContext) - start");
        try {
            List<RevenueForAllChannelsForm> formList = new ArrayList<>();
            RevenueForAllChannelsForm form = new RevenueForAllChannelsForm();

            StringBuilder timeConditionSql = new StringBuilder();
            StringBuilder productConditionSql = new StringBuilder();
            StringBuilder storeConditionSql = new StringBuilder();

            List<String> productIds = (List<String>) context.get("productIds");
            List<String> storeIds = (List<String>) context.get("storeIds");
//            都是查询逻辑
            /*SimpleDateFormat designFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date orderDateFrom = designFormat.parse((String) context.get(REPORT_PARAMETER_ORDER_DATE_FROM_DATE));
            Date orderDateTo = designFormat.parse((String) context.get(REPORT_PARAMETER_ORDER_DATE_TO_DATE));
            if (null != orderDateFrom) {
                timeConditionSql.append(" AND ");
                timeConditionSql.append(
                        DatabaseService.getInstance().getDBAdapter().ge(
                                "so.order_date",
                                orderDateFrom,
                                DatePattern.YYYYMMDD));
            }
            if (null != orderDateTo) {
                timeConditionSql.append(" AND ");
                timeConditionSql.append(
                        DatabaseService.getInstance().getDBAdapter().le(
                                "so.order_date",
                                orderDateTo,
                                DatePattern.YYYYMMDD));
            }
            if (null != productIds && productIds.size() > 0) {
                productConditionSql.append(" AND p.product_id IN ");
                productConditionSql.append(productIds.stream().filter(Objects::nonNull).filter(StringUtils::isNotEmpty).map(id -> "'" + id + "'").collect(Collectors.joining(",", "(", ")")));
            }
            if (null != storeIds && storeIds.size() > 0) {
                storeConditionSql.append(" AND so.store_id IN ");
                storeConditionSql.append(storeIds.stream().filter(Objects::nonNull).filter(StringUtils::isNotEmpty).map(id -> "'" + id + "'").collect(Collectors.joining(",", "(", ")")));
            }

            SalesOrderKey salesOrderKey = new SalesOrderKey();
            salesOrderKey.setEpId(context.getSystemId());
            BigDecimal totalAmt = BigDecimal.ZERO;

            StringBuilder bonusHql = new StringBuilder("SELECT " +
                    " so.order_id , " +
                    " so.order_date ," +
                    " bh.action_id , " +
                    " bh.bonus_point, " +
                    " c.client_id , " +
                    " c.name_en, " +
                    " c.client_type,  " +
                    " c.client_type as product_type " +
                    " FROM " +
                    " sales_order so " +
                    " LEFT JOIN client c ON " +
                    " so.client_id = c.client_id " +
                    " LEFT JOIN bonus_hist bh ON " +
                    " so.order_id = bh.action_id " +
                    " WHERE " +
                    " so.status <> '" + SalesOrderStatus.Cancel.getValue() +"' and " +
                    " bh.`action` = 'REDM' ");
            bonusHql.append(timeConditionSql).append(storeConditionSql);
            bonusHql.append(" UNION " +
                    " SELECT" +
                    " so.order_id ," +
                    " so.order_date ," +
                    " oi.order_id ," +
                    " oi.qty," +
                    " c.client_id ," +
                    " c.name_en," +
                    " c.client_type," +
                    " oi.product_id " +
                    " FROM" +
                    " sales_order so" +
                    " LEFT JOIN client c ON" +
                    " so.client_id = c.client_id" +
                    " INNER JOIN order_item oi ON" +
                    " so.order_id = oi.order_id" +
                    " LEFT JOIN product prd ON" +
                    " oi.product_id = prd.product_id" +
                    " WHERE " +
                    " so.status <> '" + SalesOrderStatus.Cancel.getValue() +"' AND oi.product_id = 'BPADJ-M-001' ");
            bonusHql.append(timeConditionSql).append(storeConditionSql);
            bonusHql.append(" UNION " +
                    " SELECT" +
                    " so.order_id," +
                    " so.order_date," +
                    " di.order_id," +
                    " di.gift_qty," +
                    " c.client_id ," +
                    " c.name_en," +
                    " c.client_type," +
                    " di.product_id " +
                    " FROM" +
                    " sales_order so" +
                    " LEFT JOIN client c ON" +
                    " so.client_id = c.client_id" +
                    " INNER JOIN discount_item di ON" +
                    " so.order_id = di.order_id" +
                    " LEFT JOIN product prd ON" +
                    " di.product_id = prd.product_id" +
                    " WHERE " +
                    " so.status <> '" + SalesOrderStatus.Cancel.getValue() +"' AND di.product_id = 'BPADJ-M-001' ");
            bonusHql.append(timeConditionSql).append(storeConditionSql);
            logger.info("database bonusHql search sql:{}", bonusHql.toString());

            List<Object[]> bonusList = DatabaseService.getInstance().getPersistentStoreManager().
                    pagingNativeQuery(salesOrderKey, bonusHql.toString(), 0, Integer.MAX_VALUE);
            if (bonusList.size() == 0)
                form.setBonusList(Collections.emptyList());
            List<RevenueForAllChannelsItemForm> bonusItemFormList = bonusList.stream().map(data -> {
                RevenueForAllChannelsItemForm itemForm = new RevenueForAllChannelsItemForm();
                itemForm.setOrderDate((Date) data[1]);
                itemForm.setOrderId((String) data[2]);
                itemForm.setNameEN((String) data[5]);

                itemForm.setListPrice(data[3] == null ? BigDecimal.ZERO : new BigDecimal(data[3] + ""));
                itemForm.setSubTotalAmt(data[3] == null ? BigDecimal.ZERO : new BigDecimal(data[3] + ""));
                if ("BPADJ-M-001".equalsIgnoreCase((String) data[7])) {
                    itemForm.setTotalQty(new BigDecimal(data[3] + ""));
                    itemForm.setListPrice(BigDecimal.ZERO);
                    itemForm.setSubTotalAmt(BigDecimal.ZERO);
                } else {
                    itemForm.setTotalQty(BigDecimal.valueOf(1));
                }

                String clientType = (String) data[6];
                if (StringUtils.isNoneEmpty(clientType) && "S".equals(clientType)) {
                    itemForm.setExcludeTotalQty(BigDecimal.ZERO);
                    itemForm.setExcludeSubTotalAmt(BigDecimal.ZERO);
                } else {
                    itemForm.setExcludeTotalQty(BigDecimal.valueOf(1));
                    itemForm.setExcludeSubTotalAmt(data[3] == null ? BigDecimal.ZERO : new BigDecimal(data[3] + ""));
                }
                return itemForm;
            }).collect(Collectors.toList());
            form.setBonusList(bonusItemFormList);
            for (RevenueForAllChannelsItemForm f : bonusItemFormList) {
                totalAmt = totalAmt.add(f.getSubTotalAmt() == null ? BigDecimal.ZERO : f.getSubTotalAmt());
            }

            StringBuilder discountSql = new StringBuilder("SELECT " +
                    " so.order_date , " +
                    " di.order_id , " +
                    " di.discount_amt , " +
                    " c.name_en , " +
                    " c.client_type " +
                    " FROM " +
                    " sales_order so " +
                    " INNER JOIN discount_item di ON " +
                    " so.order_id = di.order_id " +
                    " LEFT JOIN product p ON " +
                    " di.product_id = p.product_id " +
                    " LEFT JOIN client c ON " +
                    " c.client_id = so.client_id " +
                    " WHERE " +
                    " di.discount_amt <> 0 " +
                    " AND so.status <> '" + SalesOrderStatus.Cancel.getValue() +"' ");
            discountSql.append(timeConditionSql).append(productConditionSql).append(storeConditionSql);
            logger.info("database discount search sql:{}", discountSql.toString());

            List<Object[]> discountList = DatabaseService.getInstance().getPersistentStoreManager().
                    pagingNativeQuery(salesOrderKey, discountSql.toString(), 0, Integer.MAX_VALUE);
            if (discountList.size() == 0)
                form.setDiscountList(Collections.emptyList());
            List<RevenueForAllChannelsItemForm> discountItemFormList = discountList.stream().map(data -> {
                RevenueForAllChannelsItemForm itemForm = new RevenueForAllChannelsItemForm();
                itemForm.setOrderDate((Date) data[0]);
                itemForm.setOrderId((String) data[1]);
                itemForm.setNameEN((String) data[3]);

                itemForm.setTotalQty(BigDecimal.valueOf(1));
                itemForm.setSubTotalAmt(data[2] == null ? BigDecimal.ZERO : (BigDecimal) data[2]);

                String clientType = (String) data[4];
                if (StringUtils.isNoneEmpty(clientType) && "S".equals(clientType)) {
                    itemForm.setExcludeTotalQty(BigDecimal.ZERO);
                    itemForm.setExcludeSubTotalAmt(BigDecimal.ZERO);
                } else {
                    itemForm.setExcludeTotalQty(BigDecimal.valueOf(1));
                    itemForm.setExcludeSubTotalAmt(data[2] == null ? BigDecimal.ZERO : (BigDecimal) data[2]);
                }
                return itemForm;
            }).collect(Collectors.toList());
            form.setDiscountList(discountItemFormList);
            for (RevenueForAllChannelsItemForm f : discountItemFormList) {
                totalAmt = totalAmt.add(f.getSubTotalAmt() == null ? BigDecimal.ZERO : f.getSubTotalAmt());
            }

            StringBuilder productSql = new StringBuilder("SELECT " +
                    " so.order_date, " +
                    " oi.order_id, " +
                    " oi.qty, " +
                    " oi.product_id, " +
                    " c.client_type, " +
                    " c.name_en, " +
                    " oi.price, " +
                    " pp.price as member_price, " +
                    " (oi.qty * oi.price) AS amt,  " +
                    " p.name_en as product_name_en, " +
                    " p.size_desc " +
                    " FROM " +
                    " ( " +
                    " SELECT " +
                    "  oi.product_id, oi.order_id , oi.item_seq, oi.item_type, oi.qty, oi.price " +
                    " FROM " +
                    "  order_item oi " +
                    " GROUP BY " +
                    "  oi.product_id, oi.order_id , oi.item_seq, oi.item_type, oi.qty, oi.price) oi " +
                    " INNER JOIN sales_order so ON " +
                    " so.order_id = oi.order_id " +
                    " AND oi.item_type IN ('S', " +
                    " 'K') " +
                    " LEFT JOIN product p ON " +
                    " oi.product_id = p.product_id " +
                    " LEFT JOIN product_price pp ON " +
                    " oi.product_id = pp.product_id  " +
                    " AND pp.price_type = 'M' " +
                    " LEFT JOIN client c ON " +
                    " so.client_id = c.client_id WHERE so.status <> '" + SalesOrderStatus.Cancel.getValue() +"'  ");
            productSql.append(" AND oi.product_id != 'BPADJ-M-001'");
            productSql.append(timeConditionSql).append(productConditionSql).append(storeConditionSql);
            logger.info("database productSql search sql:{}", productSql.toString());

            List<Object[]> productList = DatabaseService.getInstance().getPersistentStoreManager().
                    pagingNativeQuery(salesOrderKey, productSql.toString(), 0, Integer.MAX_VALUE);
            List<RevenueForAllChannelsItemForm> productItemList = getProductItemList(productList);

            StringBuilder productSqlWithNotInOrderItemSql = new StringBuilder("SELECT  " +
                    " t1.order_date, " +
                    " t1.order_id, " +
                    " iai.qty * -1 AS qty , " +
                    " p2.product_id, " +
                    " c.client_type, " +
                    " c.name_en , " +
                    " iai.price, " +
                    " pp.price AS member_price, " +
                    " (iai.qty * -1 * iai.price) AS amt, " +
                    " p2.name_en as product_name_en, " +
                    " p2.size_desc " +
                    " FROM  " +
                    " (SELECT " +
                    "  so.order_date, so.store_id, so.order_id , so.pay_amount,  so.client_id  FROM sales_order so " +
                    " WHERE order_id NOT IN " +
                    " ( SELECT t.order_id FROM sales_order t INNER JOIN order_item oi ON t.order_id = oi.order_id " +
                    "  WHERE oi.product_id !='BPADJ-M-001' AND oi.item_type IN ('S','K') AND status <> 'CA' ");
            productSqlWithNotInOrderItemSql.append(timeConditionSql)
                    .append(productConditionSql.toString().replace("p.product_id IN","oi.product_id IN"))
                    .append(storeConditionSql)
                    .append(")");
            productSqlWithNotInOrderItemSql.append(" AND status <> 'CA' AND pay_amount < 0 ")
                    .append(timeConditionSql)
//                    .append(productConditionSql)
                    .append(storeConditionSql)
                    .append(") t1 ");
            productSqlWithNotInOrderItemSql.append(
                    " LEFT JOIN inventory_adj ia ON " +
                            "  ia.remark3 = t1.order_id " +
                            " INNER JOIN inventory_adj_item iai ON " +
                            "  ia.adj_id = iai.adj_id " +
                            " LEFT JOIN product p2 ON " +
                            "  iai.product_id = p2.product_id " +
                            " LEFT JOIN product_price pp ON " +
                            "  p2.product_id = pp.product_id " +
                            "  AND pp.price_type = 'M' " +
                            " LEFT JOIN client c ON " +
                            "  t1.client_id = c.client_id " +
                            productConditionSql.toString().replace("AND p.product_id IN", " where p2.product_id IN") +
                            " ORDER BY iai.product_id ");
            logger.info("database productSqlWithNotInOrderItemSql search sql:{}", productSqlWithNotInOrderItemSql.toString());

            List<Object[]> productListWithNotInOrderItem = DatabaseService.getInstance().getPersistentStoreManager().
                    pagingNativeQuery(salesOrderKey, productSqlWithNotInOrderItemSql.toString(), 0, Integer.MAX_VALUE);
            List<RevenueForAllChannelsItemForm> productItemListWithNegative = getProductItemList(productListWithNotInOrderItem);
            productItemList.addAll(productItemListWithNegative);
            if (productItemList.size() == 0){
                form.setProductList(Collections.emptyList());
                return JasperReportEngine.NO_DATA;
            }
//            order
            productItemList.sort(Comparator.comparing(RevenueForAllChannelsItemForm::getProductId));
            form.setProductList(productItemList);
            for (RevenueForAllChannelsItemForm f : productItemList) {
                totalAmt = totalAmt.add(f.getSubTotalAmt() == null ? BigDecimal.ZERO : f.getSubTotalAmt());
            }

            context.put("totalAmt", totalAmt);

            formList.add(form);*/
            this.delegateDataSource = new JRBeanCollectionDataSource(formList);
        } catch (Exception e) {
            logger.error("initDataSource(JRContext)", e);
            return JasperReportEngine.CREATE_FAILED;
        }
        logger.debug("initDataSource(JRContext) - end");
        return JasperReportEngine.CREATE_SUCCESSFUL;
    }

    @Override
    public boolean next() throws JRException {
        return super.next();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        return super.getFieldValue(jrField);
    }

    private List<RevenueForAllChannelsItemForm> getProductItemList(List<Object[]> objList) {
        return objList.stream().map(data -> {
            RevenueForAllChannelsItemForm itemForm = new RevenueForAllChannelsItemForm();
            itemForm.setOrderDate((Date) data[0]);
            itemForm.setOrderId((String) data[1]);
            itemForm.setTotalQty((BigDecimal) data[2]);
            itemForm.setProductId((String) data[3]);
            itemForm.setNameEN((String) data[5]);
            itemForm.setListPrice(data[6] == null ? BigDecimal.ZERO : new BigDecimal(String.valueOf(data[6])).setScale(2, RoundingMode.HALF_UP));
            itemForm.setMemberPrice(data[7] == null ? itemForm.getListPrice() : new BigDecimal(data[7] + "").setScale(2, RoundingMode.HALF_UP));
            itemForm.setSubTotalAmt(data[8] == null ? BigDecimal.ZERO : (BigDecimal) data[8]);
            itemForm.setProductNameEN((String) data[9]);
            itemForm.setSize((String)data[10]);


            String clientType = (String) data[4];
            if (StringUtils.isNoneEmpty(clientType) && "S".equals(clientType)) {
                itemForm.setExcludeTotalQty(BigDecimal.ZERO);
                itemForm.setExcludeSubTotalAmt(BigDecimal.ZERO);
            } else {
                itemForm.setExcludeTotalQty((BigDecimal) data[2]);
                itemForm.setExcludeSubTotalAmt(data[8] == null ? BigDecimal.ZERO : (BigDecimal) data[8]);
            }
            itemForm.setExcludeListPrice(data[6] == null ? BigDecimal.ZERO : new BigDecimal(String.valueOf(data[6])).setScale(2, RoundingMode.HALF_UP));
            return itemForm;
        }).collect(Collectors.toList());
    }
}
// 调用
/*
 *
 *public RevenueForAllChannelsResponse downloadRevenueReportForAllChannels(RevenueForAllChannelsRequest request) {
        RevenueForAllChannelsResponse response = new RevenueForAllChannelsResponse();
        try {
            DefaultJRContext jrContext = new DefaultJRContext();
            jrContext.setActor(request.getActor());
            jrContext.setSystemId(request.getSystemId());
            if(request.getReportType() == ReportMimeType.XLS.getValue()) {
                jrContext.setReportMimeType(ReportMimeType.XLS);
                jrContext.setTemplate(this.revenueForAllChannelsTemplateForExcel);
                jrContext.setExporter(new DefaultXLSReportExporter().withResponse(response));
                response.setReportFileName("revenue report for all channels.xls");
            } else if (request.getReportType() == ReportMimeType.PDF.getValue()) {
                jrContext.setReportMimeType(ReportMimeType.PDF);
                jrContext.setTemplate(this.revenueForAllChannelsTemplate);
                jrContext.setExporter(new DefaultPDFReportExporter().withResponse(response));
                response.setReportFileName("revenue report for all channels.pdf");
            } else {
                jrContext.setReportMimeType(ReportMimeType.PDF);
                jrContext.setTemplate(this.revenueForAllChannelsTemplate);
                jrContext.setExporter(new DefaultPDFReportExporter().withResponse(response));
                response.setReportFileName("revenue report for all channels.pdf");
            }
            jrContext.setJrDataSourceClass(RevenueForAllChannelsDataSource.class);
            jrContext.setConnectionProvider(jrConnectionProvider);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
            jrContext.put(JRConstants.REPORT_PARAMETER_ORDER_DATE_FROM_DATE, dateFormat.format(request.getOrderDateFrom()));
            jrContext.put(JRConstants.REPORT_PARAMETER_ORDER_DATE_TO_DATE, dateFormat.format(request.getOrderDateTo()));
            jrContext.put("productIds", request.getProductIds());
            jrContext.put("storeIds", request.getStoreIds());
            int res = new DefaultJasperReportEngine().createReport(jrContext);
            if (res == JasperReportEngine.NO_DATA) {
                response.setReturnCode(ErrorCode.ReportNoData);
            } else if (res != JasperReportEngine.CREATE_SUCCESSFUL) {
                response.setReturnCode(ErrorCode.Failed);
            }
        } catch (Exception e) {
            log.error("downloadRevenueReportForAllChannels.", e);
            response.setReturnCode(ErrorCode.Failed);
        }
        return response;
    }
 */
