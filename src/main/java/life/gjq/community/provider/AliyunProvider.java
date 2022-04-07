package life.gjq.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class AliyunProvider {
    @Value("${aliyun.file.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.file.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun.file.endpoint}")
    private String endpoint;
    private String bucketName="gjiaqi";
//    private String objectName="img" ;
//    private String fileName;


    public  String  uploadFile(MultipartFile file, String fileName){
        //外面获取文件输入流，最后方便关闭
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //2 创建OssClient对象
            OSS ossClient =new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            //3 获取文件信息，为了上传
            // meta设置请求头
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            //4 设置知道文件夹
            ossClient.putObject(bucketName,fileName,in, meta);
            //5 关闭ossClient
            ossClient.shutdown();
            //6 返回上传之后地址，拼接地址
            String uploadUrl = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return uploadUrl;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
