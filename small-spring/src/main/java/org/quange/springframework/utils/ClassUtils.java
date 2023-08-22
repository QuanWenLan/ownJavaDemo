package org.quange.springframework.utils;

/**
 * @author Lan
 * @createTime 2023-08-22  13:59
 **/
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
