package com.pink.gmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtils {
    public static String uploadImage(MultipartFile multipartFile){
        String imgUrl = "http://192.168.32.129";
        //上传代码

        String tracker = PmsUploadUtils.class.getResource("/tracker.conf").getPath();//获得配置文件的路径
        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageClient storageClient = new StorageClient(trackerServer,null);


        try {
            byte[] bytes = multipartFile.getBytes();//获得上传的二进制对象
            String originalFilename = multipartFile.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");

            String extName = originalFilename.substring(index+1);

            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);

            for (String uploadInfo:uploadInfos) {
                    imgUrl += "/"+uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
