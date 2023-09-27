package springtest.tx;

/**
 * @author Vin lan
 * @className User
 * @description
 * @createTime 2021-11-24  09:46
 **/
public class User {
    private String name;
    private Integer userId;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
