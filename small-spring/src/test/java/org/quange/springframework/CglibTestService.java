package org.quange.springframework;

/**
 * @author Lan
 * @createTime 2023-12-18  14:45
 **/
public class CglibTestService {
    private String name;

    public CglibTestService(String name) {
        this.name = name;
    }

    public CglibTestService() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CglibTestService{" +
                "name='" + name + '\'' +
                '}';
    }
}
