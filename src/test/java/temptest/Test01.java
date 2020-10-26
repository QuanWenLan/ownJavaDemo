package temptest;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


public class Test01 {
    public static void main(String[] args) throws ParseException {
        //新建9个A对象
        A a = new A("11", "23");
        A a1 = new A("15", "21");
        A a2 = new A("15", "22");
        A a3 = new A("13", "24");
        A a4 = new A("16", "22");
        A a5 = new A("18", "28");
        A a6 = new A("12", "29");
        A a7 = new A("19", "27");
        A a8 = new A("11", "21");

        //将对象放入aList集合中
        List<A> aList = new ArrayList<>();
        aList.add(a);
        aList.add(a1);
        aList.add(a2);
        aList.add(a3);
        aList.add(a4);
        aList.add(a5);
        aList.add(a6);
        aList.add(a7);
        aList.add(a8);

        System.out.println(aList);
        //输出结果:[A{a='11', b='23'}, A{a='15', b='21'}, A{a='15', b='22'}, A{a='13', b='24'}, A{a='16', b='22'}, A{a='18', b='28'}, A{a='12', b='29'}, A{a='19', b='27'}, A{a='11', b='21'}]

        Comparator<A> bya = Comparator.comparing(A::getA);//按照a升序
        Comparator<A> byb = Comparator.comparing(A::getB);//按照b升序

        Collections.sort(aList, bya.thenComparing(byb));//将aList按照a字段先升序再按照B字段进行升序排列

        System.out.println(aList);
        //输入结果:[A{a='11', b='21'}, A{a='11', b='23'}, A{a='12', b='29'}, A{a='13', b='24'}, A{a='15', b='21'}, A{a='15', b='22'}, A{a='16', b='22'}, A{a='18', b='28'}, A{a='19', b='27'}]

        aList.sort(new AComparator("a"));
        System.out.println(aList);
        aList.sort(new AComparator("b"));
        System.out.println(aList);
        List<A> collect = aList.stream().sorted(new AComparator("a")).sorted(new AComparator("b")).collect(Collectors.toList());
        System.out.println(collect);
    }
}

class AComparator implements Comparator<A> {

    private String field;

    public AComparator(String field) {
        this.field = field;
    }

    @Override
    public int compare(A o1, A o2) {
        if ("a".equals(field)) {
            return o1.a.compareTo(o2.a);
        } else {
            return o1.b.compareTo(o2.b);
        }
    }
}