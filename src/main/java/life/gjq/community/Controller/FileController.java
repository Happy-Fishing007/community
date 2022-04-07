package life.gjq.community.Controller;

import life.gjq.community.dto.FileDTO;
import life.gjq.community.provider.AliyunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class FileController {
@Autowired
private AliyunProvider aliyunProvider;

@RequestMapping("/file/upload")
@ResponseBody
    public FileDTO upload(HttpServletRequest request){
    MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
    MultipartFile file =multipartRequest.getFile("editormd-image-file");

        FileDTO fileDTO = new FileDTO();

    if (!file.isEmpty()) {
        // 生成文件名称
        String nameSuffix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
                .replaceAll(" ", "_").replaceAll(",", "")
                +  UUID.randomUUID().toString();
        //上传原始图片到阿里云
        String uploadPath =  aliyunProvider.uploadFile(file,nameSuffix);

        //设置返回参数，设置返回url
        fileDTO.setSuccess(1);
        fileDTO.setUrl(uploadPath);
    }else{
        fileDTO.setSuccess(0);
        fileDTO.setMessage("文件不能为空哦");
    }

        return fileDTO;
    }
}
