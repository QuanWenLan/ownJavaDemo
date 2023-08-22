package org.quange.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Lan
 * @createTime 2023-08-22  12:28
 **/
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
