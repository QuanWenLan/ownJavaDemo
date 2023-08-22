package org.quange.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lan
 * @createTime 2023-08-22  12:25
 **/
public class FileSystemResource implements Resource {
    private File file;
    private String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
