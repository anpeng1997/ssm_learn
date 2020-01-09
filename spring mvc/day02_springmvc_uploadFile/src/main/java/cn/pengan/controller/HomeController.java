package cn.pengan.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/home")
@Controller
public class HomeController {

    @RequestMapping(path = "/uploadfile1", method = {RequestMethod.POST})
    public String uploadFile1(HttpServletRequest request) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        System.out.println(realPath);
        File uploadFile = new File(realPath);
        //判断上传文件夹是否存在,不存在就创建一个
        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            if (!fileItem.isFormField()){
                String fileName = fileItem.getName();
                String filePrefix = UUID.randomUUID().toString().replace("-", "");
                String fileNewName=filePrefix+fileName;
                fileItem.write(new File(realPath,fileNewName));
                //当上传的文件大于10k时，就会有缓存文件，保存完之后就要删除它
                fileItem.delete();
            }
        }
        return "success";
    }
}
