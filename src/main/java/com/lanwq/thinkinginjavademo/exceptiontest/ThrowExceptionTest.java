package com.lanwq.thinkinginjavademo.exceptiontest;

/**
 * @author Vin lan
 * @className ThrowExceptionTest
 * @description 抛出异常测试
 * @createTime 2022-04-20  16:28
 **/
public class ThrowExceptionTest {
    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance e) {
                System.out.println("Caught Annoyance!");
                throw e;
            }
        } catch (Sneeze e2) {
            System.out.println("Caught Sneeze!");
        } finally {
            System.out.println("Caught complete!");
        }
        /**
         * 输出：
         * Caught Annoyance!
         * Caught Sneeze!
         * Caught complete!
         * catch (Annoyance e) 是 父类的引用，但实际上是子类的对象，这是多态。在catch (Sneeze e2)
         * 的时候当然可以捕获到自己抛出来的异常了。
         */
        // 子类从本质上是无法捕获父类的异常的。
        System.out.println("测试子类异常无法捕获父类异常，也就是无法捕获比自己小的异常");
        try {
            throw new Annoyance();
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch (Annoyance s) {
            System.out.println("Caught Annoyance");
        } finally {
            System.out.println("Caught complete!");
        }
        /*System.out.println("使用更大范围的异常捕获");
        try {
            throw new Annoyance();
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch (Exception e) {
            System.out.println("Caught Exception");
        } finally {
            System.out.println("Hello World!");
        }*/
    }
}

/**
 * 烦恼
  */
class Annoyance extends Exception {

}
/**
 * 喷嚏
  */
class Sneeze extends Annoyance {

}
