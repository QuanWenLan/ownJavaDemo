package temptest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className temptest.InitSqlUtil
 * @description
 * @createTime 2021-12-15  17:03
 **/
public class InitSqlUtil {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    HashMap<String, String> productMap = new HashMap<>();
    Set<String> notInWebProduct = new HashSet<>();

    public void updateSql() {
        jdbcTemplate.query("select * from web_product", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String webProductId = rs.getString("web_product_id");
                String productId = rs.getString("product_id");
                productMap.put(webProductId, productId);
            }
        });
        List<Entity> queryList = jdbcTemplate.query("select * from web_article", new RowMapper<Entity>() {

            @Override
            public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
                String article_id = rs.getString("article_id");
                String sub_content1 = rs.getString("sub_content1");
                if (sub_content1 != null) {

                }
                return new Entity(article_id, sub_content1 == null ? "" : sub_content1);
            }
        });
        int num = 0;
//        File file = new File("20211221_update_web_article_prd.sql");
//        File file = new File("20211217_update_web_article.sql");
        File file = new File("link.txt");
        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);

            for (Entity entity : queryList) {
                String content = entity.getSubContent();
                if (content.contains("<a href=") || content.contains("<A HREF=")) {
                    // handle
                    String result = handle("<a href=", content, fileWriter, entity.getId());
                    result = handle("<A HREF=", result, fileWriter, entity.getId());
                    /*if (!content.equals(result)) {
                        fileWriter.write("update web_article set sub_content1 = \"" + result + "\" where article_id = \"" + entity.getId() + "\"; \n");
                        num++;
                    }*/
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(num);
        System.out.println(notInWebProduct.size());
        System.out.println(notInWebProduct.stream().map(t -> "'" + t + "'").collect(Collectors.joining(",")));
    }

    private String handle(String ahref, String content, FileWriter fileWriter, String id) throws IOException {
        String tempContent = content;
        for (int index = 0; index < tempContent.length(); index++) {
            index = tempContent.indexOf(ahref, index);
            if (index != -1) {
                int i = tempContent.indexOf(">", index + 8);
                if (i != -1) {
                    String allHref = tempContent.substring(index + 8, i);

                    if (allHref.contains(".aspx")) {
                        int endHrefQuotation = allHref.indexOf(".aspx");
                        String href = allHref.substring(0, endHrefQuotation + 5);
                        String leftHref = allHref.substring(endHrefQuotation + 5);
                        int i1 = href.indexOf(".aspx");
                        int i2;
                        for (i2 = i1; i2 >= 0; i2--) {
                            if (href.charAt(i2) == '/') {
                                break;
                            }
                        }
                        String productId = href.substring(i2 + 1, i1);
                        productId = productId.replaceAll("'", "").toUpperCase();
                        // 保存链接到文件
                        fileWriter.write(id + " : " + allHref + "\n");
                        index += allHref.length();
                        // 这个注释掉的是替换成新链接
                        /*if (href.contains("article")) {
                            String newStr = "'https://10.159.9.6/hs-home/#!/link/articleDetail?articleId=" + productId + leftHref;
                            index += newStr.length();
                            tempContent = tempContent.replaceFirst(allHref, newStr);
                        } else if (productMap.get(productId) != null) {
                            String newStr = "'https://10.159.9.6/hs-home/#!/link/productDetail/" + productId + leftHref;
                            index += newStr.length();
                            tempContent = tempContent.replaceFirst(allHref, newStr);
                        } else {
                            index += allHref.length();
                        }
                        if (productMap.get(productId) == null) {
                            notInWebProduct.add(productId);
                        }*/
                    } /*else if (allHref.contains("product_details.php")) {
//                                <a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=miraco&item=MR0800001'>
//                                <a href= https://www.healthsmart.com.hk/product_details.php?item=ZHSFD00064>
                        int endHrefQuotation = allHref.lastIndexOf("'");
                        if (endHrefQuotation == -1) {
                            endHrefQuotation = allHref.length();
                        }
                        String href = allHref.substring(0, endHrefQuotation);
                        String categoryType1 = "", categoryType2 = "", categoryType3 = "", productId = "";
                        int itemIndex = href.indexOf("item=");
                        int lv1Index = href.indexOf("lv1=");
                        int lv2Index = href.indexOf("lv2=");

                        if (lv1Index != -1) {
                            for (int j = lv1Index + 4; j < href.length(); j++) {
                                if (href.charAt(j) == '&') {
                                    categoryType1 = href.substring(lv1Index + 4, j).trim();
                                    break; // 停止遍历
                                }
                            }
                        }
                        if (lv2Index != -1) {
                            for (int j = lv2Index + 4; j < href.length(); j++) {
                                if (href.charAt(j) == '&') {
                                    categoryType2 = href.substring(lv2Index + 4, j).trim();
                                    break; // 停止遍历
                                }
                            }
                        }

                        if (itemIndex != -1) {
                            productId = href.substring(itemIndex + 5, endHrefQuotation).trim();
                            if (productId.contains("&")) {
                                productId = productId.substring(0, productId.indexOf("&"));
                            }
                        }
                        productId = productId.replaceAll("'", "").toUpperCase();
                        StringBuilder newStr = new StringBuilder("'https://10.159.9.6/hs-home/#!/link/productDetail");
                        if (!"".equals(productId)) {
                            newStr.append("/").append(productId);
                        }
                        if (!"".equals(categoryType1)) {
                            newStr.append("?categoryType1=").append(categoryType1);
                        }
                        if (!"".equals(categoryType2)) {
                            newStr.append("&categoryType1=").append(categoryType2);
                        }

                        if (productMap.get(productId) != null) {
                            index += newStr.length();
                            tempContent = tempContent.replaceFirst(allHref.replaceAll("\\?", "\\\\?"), newStr.append("'").toString());
                        } else {
                            if (productMap.get(productId) == null) {
                                notInWebProduct.add(productId);
                            }
                            index += allHref.length();
                        }
                    }  */else if (allHref.contains(".php")) {
//                                <a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=miraco&item=MR0800001'>
//                                <a href= https://www.healthsmart.com.hk/product_details.php?item=ZHSFD00064>
                        int endHrefQuotation = allHref.lastIndexOf("'");
                        if (endHrefQuotation == -1) {
                            endHrefQuotation = allHref.length();
                        }
                        String href = allHref.substring(0, endHrefQuotation);
                        fileWriter.write(id + " : " + allHref + "\n");
                        index += allHref.length();
                    }
                }
            } else {
                break;
            }
        }
        return tempContent;
    }

    static class Entity {
        private String id;
        private String subContent;

        public Entity() {
        }

        public Entity(String id, String subContent) {
            this.id = id;
            this.subContent = subContent;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubContent() {
            return subContent;
        }

        public void setSubContent(String subContent) {
            this.subContent = subContent;
        }
    }
}
