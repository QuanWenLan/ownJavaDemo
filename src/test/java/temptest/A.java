package temptest;

public class A {
 
    String a;
    String b;
 
    public A() {
    }
 
    public A(String a, String b) {
        this.a = a;
        this.b = b;
    }
 
    public String getA() {
        return a;
    }
 
    public void setA(String a) {
        this.a = a;
    }
 
    public String getB() {
        return b;
    }
 
    public void setB(String b) {
        this.b = b;
    }
 
    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}