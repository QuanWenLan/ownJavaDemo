#### 第十五章节：泛型
1. 泛型实现了*参数化类型*的概念，时代吗应用于多种类型。“泛型”这个术语的意思是：“适用于许多许多的类型”。  
2. 泛型的主要目的之一就是**用来指定容器要持有什么类型的对象，而且由编译器来保证类型的正确性“。  
3.  
方法使用案例：
```java
/*
* <A extends AbstractAction<R, S>, R extends BaseRequest, S extends BaseResponse>
* 声明此方法持有几个泛型，也可以只写一个如：
  pubic <T> T getObject(Class<T> c) {
    ...
}
Class<A> actionClass：作用是指明泛型A的具体类型
actionClass：用来创建泛型T代表的类的对象
S invoker： S 用来指明方法的返回值类型
*/
public static <A extends AbstractAction<R, S>, R extends BaseRequest, S extends BaseResponse> S invoker(
        Class<A> actionClass,
        R request) {
        logger.debug("invoker action<{}> - start", actionClass.getName());
        @SuppressWarnings("unchecked")
        A action = (A) actionCache.getOrCreateCachedAction(actionClass);
        S returnS = action.doAction(request);
        logger.debug("invoker action<{}> - end", actionClass.getName());
        return returnS;

    }
```
上限和下限  
上限 <T extends Number>  
```java
class Info<T extends Number>{    // 此处泛型只能是数字类型
    private T var ;        // 定义泛型变量
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }
    public String toString(){    // 直接打印
        return this.var.toString() ;
    }
}
public class demo1{
    public static void main(String args[]){
        Info<Integer> i1 = new Info<Integer>() ;        // 声明Integer的泛型对象
    }
}
```
下限：<? super String>  
```java
class Info<T>{
    private T var ;        // 定义泛型变量
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }
    public String toString(){    // 直接打印
        return this.var.toString() ;
    }
}
public class GenericsDemo21{
    public static void main(String args[]){
        Info<String> i1 = new Info<String>() ;        // 声明String的泛型对象
        Info<Object> i2 = new Info<Object>() ;        // 声明Object的泛型对象
        i1.setVar("hello") ;
        i2.setVar(new Object()) ;
        fun(i1) ;
        fun(i2) ;
    }
    public static void fun(Info<? super String> temp){    // 只能接收String或Object类型的泛型，String类的父类只有Object类
        System.out.print(temp + ", ") ;
    }
}
```
