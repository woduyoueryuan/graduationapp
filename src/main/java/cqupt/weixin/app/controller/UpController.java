package cqupt.weixin.app.controller;

import cqupt.weixin.app.entity.CourseWare;
import cqupt.weixin.app.result.ResponseResult;
import cqupt.weixin.app.service.CourseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UpController{
    @Autowired
    CourseService courseService;
    @RequestMapping("/upload")
    public ResponseResult uploadMusicFile(HttpServletRequest request, MultipartFile file ) {
        CourseWare courseWare=new CourseWare();
        //判断文件等不等于空
        String originalFilename=file.getOriginalFilename();
        if(file.isEmpty()){
           return new ResponseResult("文件为空，上传失败！");
        }
        //判断文件是否已经上传过
        CourseWare isE = courseService.findFile(originalFilename);
        if((isE!=null)&&(isE.getFileSize()==file.getSize())){
                return new ResponseResult("此文件已存在请不要重复上传");
        }
        if (file != null) {
           //保存到服务器的文件名
            String fileName= UUID.randomUUID().toString().replaceAll("-","");
            //获取文件后缀
            String originalFile = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFile);
            if(isE!=null){
                originalFilename=originalFilename+Math.random()*100;
            }
            //保存文件到服务器
            try {
                file.transferTo(new File("D:\\upload\\"+fileName+"."+extension));
            }catch (IOException e){
                return new ResponseResult("上传失败");
            }
            //保存到数据库
            courseWare.setFileName(fileName + "." + extension);
            courseWare.setOriginalFilename(originalFilename);
            courseWare.setFileSize(file.getSize());
            boolean b = courseService.saveFile(courseWare);
            if(!b){
                return new ResponseResult("上传失败！");
            }


        }
        return new ResponseResult("上传成功！");
    }
    //查找所有文件
    @RequestMapping("/findAllFile")
    public ResponseResult<List<CourseWare>> findAllFile(){
     List<CourseWare> courseWare;
        try {
           courseWare= courseService.findAllFile();
        }catch (Exception e){
            return new ResponseResult("-1","系统内部错误！");
        }
        return new ResponseResult<List<CourseWare>>("200","查询成功！",courseWare);
    }
}