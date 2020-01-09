# spring mvc上传文件的几种方式

1. 使用HttpServletRequest对象获取

```xml
<!-- 所需jar -->
<dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.6</version>
 </dependency>
<dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.4</version>
</dependency>
```

1. 基础的方式

    ```java
    //传统方式提交
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
    ```

2. 使用spring mvc中的解析器

    ```xml
    <!-- springmvc.xml配置解析器 -->
    <!-- id必须叫 multipartResolver-->
     <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    ```

    ```java
    @RequestMapping(path = "/uploadfile2",method = {RequestMethod.POST})
    //MultipartFile 形参名必须和前端form表单input空间name一致
    public String uploadFile2(HttpServletRequest request , MultipartFile upload) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        System.out.println(realPath);
        File uploadFile = new File(realPath);
        //判断上传文件夹是否存在,不存在就创建一个
        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }
        String originalFilename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        upload.transferTo(new File(realPath,uuid+originalFilename));
        return "success";
    }
    ```

3. 将上传的文件中转至文件服务器

* 可能出现的问题：
<https://blog.csdn.net/weixin_43705313/article/details/103126093>

```xml
    <dependency>
    <groupId>com.sun.jersey</groupId>
    <artifactId>jersey-core</artifactId>
    <version>1.18.1</version>
</dependency>
<dependency>
    <groupId>com.sun.jersey</groupId>
    <artifactId>jersey-client</artifactId>
    <version>1.18.1</version>
</dependency>
```

```java
@RequestMapping(path = "/uploadfile3",method = {RequestMethod.POST})
    public String uploadFile3(MultipartFile upload) throws IOException {
        //该服务器是我们本地的，需要在webapp文件夹下创建一个叫uploads的文件夹
        String server="http://localhost:9090/uploads/";
        String originalFilename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Client client = Client.create();
        WebResource resource = client.resource(server + uuid + originalFilename);
        resource.put(upload.getBytes());
        return "success";
    }
```