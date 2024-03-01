package fileupload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 文件上传servlet
 *  @MultipartConfig // 表明当前servlet可以处理文件上传请求 enctype="multipart/form-data"
 */
@WebServlet(name = "uploadServlet", value = {"/uploadServlet"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String images = servletContext.getRealPath("/images"); // 获取文件夹路径
        // 从请求中获取文件 multipart/form-data 中的部分 Part 对象
        Part photo = request.getPart("photo");
        photo.write(images + "/xxx.txt");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
