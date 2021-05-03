package com.example.house.service.impl;

import com.example.house.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    /**
     * 通过图片文件获取文件存储的路径
     */
    /*public List<String> getImgPaths(List<MultipartFile> files) {
        if (filePath!=null&&!filePath.endsWith("")) {
            filePath = getResourcePath();
        }
        List<String> paths = new ArrayList<>();
        files.forEach(file -> {
            File localFile = null;
            if (!file.isEmpty()) {
                try {
                    localFile =  saveToLocal(file);
                    String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    paths.add(path);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return paths;
    }

    public static String getResourcePath(){
        File file = new File(".");
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }*/

    /**
     * 把文件保存到本地
     * @param file
     * @return 保存的相对路径
     * @throws IOException
     */
    /*private String saveToLocal(MultipartFile file) throws IOException {
        File newFile = new File(filePath + "/" + Instant.now().getEpochSecond() +"/"+file.getOriginalFilename());
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        //Files.write(file.getBytes(), newFile);
        return newFile;
    }*/
}
