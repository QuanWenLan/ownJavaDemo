package org.quange.springframework.core.io;

/**
 * @author Lan
 * @createTime 2023-08-22  12:27
 **/
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
