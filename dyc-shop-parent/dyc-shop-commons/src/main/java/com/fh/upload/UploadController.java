package com.fh.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${file.user.photo}")
    private String userPhotoPath;
    @PostMapping
    public String  uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String oldName=file.getOriginalFilename();
        Long fileTimeName=System.currentTimeMillis();
        String newName=fileTimeName+oldName.substring(oldName.lastIndexOf("."));
        String filePath=userPhotoPath+newName;
        File dest=new File(filePath);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        String realPath="/userPhoto/"+newName;
        return realPath;

    }
}
