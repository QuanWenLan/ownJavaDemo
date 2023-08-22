package org.quange.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lan
 * @createTime 2023-08-22  12:19
 **/
public interface Resource {
    InputStream getInputStream() throws IOException;
}
