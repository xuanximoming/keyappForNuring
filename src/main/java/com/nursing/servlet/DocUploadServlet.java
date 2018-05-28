package com.nursing.servlet;

import com.nursing.data.DocConfig;
import net.dongliu.apk.parser.ApkParser;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Jack on 2015/12/15.
 * 移动查房版本上传
 */
public class DocUploadServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveFileByCommonsFileUpload(req,resp);
    }

    /**
     * 通过commons-fileupload来处理文件上传
     * @param req
     * @param resp
     */
    private void saveFileByCommonsFileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("utf-8");

        //设置保存上传文件的路径
        String realPath = getServletContext().getRealPath("/") + "upload";
        File fileupload = new File(realPath);
        if(!fileupload.exists()){
            fileupload.mkdir();
        }
        //消息提示
        String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(req)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            @SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(req);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    System.out.println("fileName="+filename);
                    //设置全局的文件名称
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realPath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";

                    //解析apk信息
                    message=message+"当前版本信息：" + parseApkInfo(new File(realPath+"\\"+filename));
                    System.out.println("versionInfo="+message);
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("doc_update.jsp").forward(req,resp);
    }

    /**
     * 对apk进行解析
     * @param
     */
    private String parseApkInfo(File apkFile){
        String version="";
        try {
            ApkParser apkParser=new ApkParser(apkFile);
            ApkMeta apkMeta=apkParser.getApkMeta();
            System.out.println("apkMeta="+apkMeta.toString());
            System.out.println("----------------");
            System.out.println("apkFile Name="+apkFile.getName());
            String downUrl="/download.do?filename="+apkFile.getName();
            //保存版本更新的内容
            OutputStream outputStream = new FileOutputStream(this.getClass().getResource("/doc.properties").getPath());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            DocConfig.getProperties().setProperty("version.appName",apkMeta.getName());
            DocConfig.getProperties().setProperty("version.appDescription","版本更新");
            DocConfig.getProperties().setProperty("version.packageName",apkMeta.getPackageName());
            DocConfig.getProperties().setProperty("version.versionCode",String.valueOf(apkMeta.getVersionCode()));
            DocConfig.getProperties().setProperty("version.versionName",apkMeta.getVersionName());
            DocConfig.getProperties().setProperty("version.apkUrl",downUrl);
            DocConfig.getProperties().store(bw,null);
            outputStream.close();
            version= apkMeta.toString()+"apkUrl:"+downUrl;
            apkParser.close();
        } catch (Exception e) {

        }
        return version;
    }
}
