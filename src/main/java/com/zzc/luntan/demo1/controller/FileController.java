package com.zzc.luntan.demo1.controller;

import com.zzc.luntan.demo1.auth.MyUserDetails;
import com.zzc.luntan.demo1.pojo.Result;
import com.zzc.luntan.demo1.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.Security;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/04")
public class FileController {


    //废弃接口
    @RequestMapping("/upload.do")
    public void upload(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //判断上传的文件是普通表单还是带文件的表单
        if(!ServletFileUpload.isMultipartContent(req)){
            System.out.println("推出");
            return;
        }

        //获得项目路径D:\workspace\demo1\target\classes\
        String path= ResourceUtils.getURL("classpath:").getPath();

        //创建文件夹
        File upload = new File(path,"static/upload/");
        if(!upload.exists())upload.mkdir();

        String uploadPath=path+"static/upload/";

        //缓存文件夹
        File temp = new File(path,"static/temp/");
        if(!temp.exists())temp.mkdir();

        //1.创建DiskFileItemFactory对象,处理文件上传路径或者大小限制的;
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件中;
        factory.setSizeThreshold(1024*1024);
        factory.setRepository(temp);

        //2.获取ServletFileUpload
        ServletFileUpload servletFileUpload=new ServletFileUpload(factory);


        //3.处理上传的文件
        //把前端请求解析,封装成一个FileItem对象,需要从ServletFileUpload对象中获取
        RequestContext context=new ServletRequestContext(req);
        List<FileItem> fileItems=servletFileUpload.parseRequest(context);

        for (FileItem fileItem : fileItems) {
            System.out.println("进入循环======");
            //判断上传的文件是普通表单还是带文件的表单
            if(fileItem.isFormField()){
                //getFieldName指的是前端表单控件的name
                String name = fileItem.getFieldName();
                String value=fileItem.getString("UTF-8");//处理乱码
                System.out.println(name + ":" + value);
            }else{//文件

                //==========处理文件==========//
                System.out.println("进入处理文件======");
                 String uploadFileName=fileItem.getName();
                 //可能存在文件名不合法的情况
                if(uploadFileName.trim().equals("")||uploadFileName==null)continue;
                //获得上传的文件名
                String fileName=uploadFileName.substring(uploadFileName.lastIndexOf("/"+1));
                //获得文件的后缀名
                String fileExtName=uploadFileName.substring(uploadFileName.lastIndexOf("."+1));

                //可以使用UUID(唯一识别的通用码),保证文件名唯一;
                //UUID.randomUUID(),随机生一个唯一识别通用码;

                //JNI= Java Native Interface
                //Serializable  :标记接口 ,JVM--->Java 栈  本地方法栈 native--->c++

                String uuidPath= UUID.randomUUID().toString();

                //=============存放地址=========//

                //存到哪?uploadPath
                //文件真实存在的路径 realPath
                String realPath=uploadPath+uuidPath;
                //给每个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if(!realPathFile.exists())realPathFile.mkdir();

                //========文件传输==========//

                //获得文件上传的流
                InputStream inputStream=fileItem.getInputStream();

                //创建一个文件输出流
                //realPath = 真实的文件夹;
                FileOutputStream fos=new FileOutputStream(realPath+"/"+fileName);

                //创建一个缓冲区
                byte[] buffer=new byte[1204*1024];

                //判断是否读取完毕
                int len=0;
                //如果大于0说明还存在数据;
                while ((len=inputStream.read(buffer))>0){
                    fos.write(buffer,0,len);
                }

                fos.close();
                inputStream.close();
            }
        }
        System.out.println("结束");
    }

    @PostMapping("/upload02")
    @ResponseBody
    public  String upload( @RequestParam("file")MultipartFile file) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails=(MyUserDetails)principal;
        String username=myUserDetails.getUsername();
        //获得项目路径D:\workspace\demo1\target\classes\
        String path= ResourceUtils.getURL("classpath:").getPath();

        //创建文件夹
       File upload02 = new File(path,"static/upload/");
    if(!upload02.exists())upload02.mkdir();
        File upload = new File(path,"static/upload/"+username);
        if(!upload.exists())upload.mkdir();
        String uploadPath=path+"static/upload/"+username+"/";
        String fileName=file.getOriginalFilename();
        File saveFile=new File(uploadPath+fileName);

        file.transferTo(saveFile);
        return "success";
    }

    @GetMapping("/download")
    public void download(HttpServletRequest req,HttpServletResponse res,@RequestParam("data")String s,@RequestParam("status")int s1) throws IOException, InterruptedException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails=(MyUserDetails)principal;

        HttpSession session = req.getSession();
        String path="D:\\workspace\\demo1\\target\\classes\\static\\upload\\"+myUserDetails.getUsername()+"\\"+s;
        res.setHeader("content-disposition", "attachment;filename="+new String(s.getBytes(), "ISO8859-1")); //乱码解决
        FileInputStream in=new FileInputStream(path);
        ServletOutputStream out = res.getOutputStream();
        int len=0;
//        int count=0;
        byte[] buf=new byte[1024];
        while ((len=in.read(buf))!=-1){
            //每次判断是否停止下载
//            if(session.getAttribute(myUserDetails.toString())!=null&&session.getAttribute(myUserDetails.toString()).equals("下载暂停")){
//                session.setAttribute("position",count*1024);
//                break;   //直接就断开下载了,,,,,,
//            }
            if(s1!=200)                //通过传入的参数决定是否限制流量,参数由数据库中的角色决定
            Thread.sleep(100);  //10000B/s
            out.write(buf,0,len);
//            count++;
        }
            in.close();
            out.close();
    }
    @ResponseBody
    @GetMapping("/fileList")
    public List<String> fileList(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails=(MyUserDetails)principal;
        List<String> list = FileUtil.getFiles("D:\\workspace\\demo1\\target\\classes\\static\\upload\\" + myUserDetails.getUsername());
        list.remove(myUserDetails.getUsername());
        return list;
    }


//    //让程序停止下载或上传
//    @ResponseBody
//    @RequestMapping("/sign")
//    public Result sign(HttpServletRequest req) throws InterruptedException {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        MyUserDetails myUserDetails=(MyUserDetails)principal;
//
//        HttpSession session = req.getSession();
//        session.setAttribute(myUserDetails.toString(),"下载暂停");
//
//        while (session.getAttribute("position")==null){
//        Thread.sleep(200);
//        }
//        System.out.println("session.getAttribute(\"position\")==>"+session.getAttribute("position"));
//        return new Result((Integer) session.getAttribute("position"),"");
//    }
}
