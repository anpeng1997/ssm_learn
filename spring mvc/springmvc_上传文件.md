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

```java
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