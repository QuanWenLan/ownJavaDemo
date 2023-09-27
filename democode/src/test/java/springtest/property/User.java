package springtest.property;

import java.util.Date;

/**
 * @author Vin lan
 * @className User
 * @description
 * @createTime 2021-08-25  16:22
 **/
public class User {
    private Date date;
    private long num;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "User{" +
                "date=" + date +
                '}';
    }
}
