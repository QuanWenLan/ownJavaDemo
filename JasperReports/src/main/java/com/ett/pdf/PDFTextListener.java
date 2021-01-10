package com.ett.pdf;

/**
 * @author Vin lan
 * @className PDFTextListener
 * @description TODO
 * @createTime 2021-01-08  10:28
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class PDFTextListener implements RenderListener {

    Logger log = LoggerFactory.getLogger(PDFTextListener.class);

    //页码
    int pageNumber;

    //保存(y,数据行) 的 map
    private Map<Integer, TextRow> rowMap = new TreeMap<Integer, TextRow>();

    //由于字体大小不一定相等，因此设置在上下 1pix 认定为同一行数据，该参数可调
    private final int MAX_ROW_ERROR_PIX = 1;

    //解析结果行
    List<TextRow> allRows = new ArrayList<>();

    /**
     * 读取一页pdf文件并且将这一页pdf文件转换为一行行的文字，最终放在rowMap中
     *
     * @param renderInfo 文字信息
     */
    @Override
    public void renderText(TextRenderInfo renderInfo) {
        //读取PDF时，有些肉眼看上去是一行的字，可能会被解析为多个，导致找不到满足条件的关键字，这里做了简单的处理
        //即如果一些词是连续的，前后没有空白字符串，即认为是一个词
        String content = renderInfo.getText().trim();

        //获取文字的边框
        Rectangle2D.Float textRectangle = renderInfo.getDescentLine().getBoundingRectange();
        log.info("content:{},(x,y,w,h)=({}, {}, {}, {})", content, textRectangle.getX(), textRectangle.getY(), textRectangle.getWidth(), textRectangle.getHeight());

        double y = textRectangle.getY();
        double x = textRectangle.getX();
        TextRow row = null;

        int keyY = (int) y;
        boolean containsKey = false;

        //允许行的高度误差,从小往大找
        for (int findY = keyY - MAX_ROW_ERROR_PIX; findY <= keyY + MAX_ROW_ERROR_PIX; findY++) {
            if (rowMap.containsKey(findY)) {
                keyY = findY;
                containsKey = true;
                break;
            }
        }

        if (!containsKey) {
            //创建一行新的记录
            row = new TextRow();
            row.setY(y);
            rowMap.put(keyY, row);
        }
        row = rowMap.get(keyY);
        if (content != null && content.length() > 0) {
            //处理一行数据左右拼接问题（字体大小不一致）
            TextColumn column;
            if (row.getColumnsMap().containsKey(x)) {
                column = row.getColumnsMap().get(x);
                content = column.getContent() + content;
                column.setContent(content);
            } else {
                column = new TextColumn();
                column.setContent(content);
                column.setX(x);
                column.setY(y);
                row.getColumnsMap().put(x, column);
            }
        }
    }


    /**
     * 获取一个 (行号，列号，字符串)的字典，方便查找使用
     */
    public Map<Integer, Map<Integer, String>> getPageContentMap() {
        Map<Integer, Map<Integer, String>> result = new HashMap<>();
        for (Integer i = 0; i < allRows.size(); i++) {
            Map<Integer, String> tempRow = new HashMap<Integer, String>();
            List<TextColumn> list = allRows.get(i).getColumns();
            for (Integer j = 0; j < list.size(); j++) {
                tempRow.put(j, list.get(j).getContent());
            }
            result.put(i, tempRow);
        }
        return result;
    }

    /**
     * 重要，由于解析的时候，rowMap中的y值是乱序的(因为itext解析也是有点乱序的)，因此需要对rowMap进行逆序排序之后，
     * 才能得到正确的行，才能将排序好的数据放进allRows中。
     */
    public void sortAndAddToAllRows() {
        allRows.clear();
        rowMap.entrySet().stream().
                sorted(Map.Entry.<Integer, TextRow>comparingByKey().reversed()).forEachOrdered(entry -> {
            allRows.add(entry.getValue());
            TextRow row = entry.getValue();
            row.setRowIndex(allRows.size() - 1);
            //同理，每行的列也是乱序的，因此需要按照x从小到大排序。
            row.sortAndAddColumns();
            log.info("key:{} , value:{},size:{}", entry.getKey(), entry.getValue().getRowContent(), row.getColumnsMap().size());
        });
    }


    @Override
    public void endTextBlock() {
        // TODO Auto-generated method stub

    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beginTextBlock() {
        // TODO Auto-generated method stub
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<TextRow> getAllRows() {
        return allRows;
    }

    public void setAllRows(List<TextRow> allRows) {
        this.allRows = allRows;
    }


}


