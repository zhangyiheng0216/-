package com.zyh.sale.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/3 16:24
 */
@Component
public class FileUtils {
    //上传文件
    public Boolean saveFile(MultipartFile file){
        if (file.isEmpty()){
            return false;
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = "E:/java_line/项目/sale/src/main/resources/static/images/user/";
        File temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
