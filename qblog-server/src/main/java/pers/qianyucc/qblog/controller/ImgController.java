package pers.qianyucc.qblog.controller;
import cn.hutool.core.util.*;
import com.sun.javafx.scene.control.Logging;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.service.ImgService;
import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Api("图片上传Api接口")
@RestController
public class ImgController {
    @Autowired
    private ImgService imgService;

    @Value("${img.location}")
    private String location;

    @ApiOperation("上传图片")
    @PutMapping("/ImgUpload")
    public Results uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile)  {

        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new BlogException(IMG_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new BlogException(IMG_NAME_ERR);
        }

        String filePath = location;

        String file_name = null;
        try {
            file_name = imgService.saveImg(multipartFile, filePath);
            return Results.ok("图片上传成功", file_name);
        } catch (IOException e) {
            throw new BlogException(IMG_UPLOAD_ERR);
        }
    }

    @ApiOperation("上传图片")
    @PutMapping("/BaseImgUpload")
    public Results uploadImg(@RequestParam(value = "imgBase64") String img) throws Exception {

        if (StringUtils.isEmpty(img)) {
            throw new BlogException(IMG_EMPTY);
        }

        String filePath = location;

        String file_name;
        try {
            file_name = imgService.saveBaseImg(img, filePath);
        } catch (IOException e) {
            throw new BlogException(IMG_UPLOAD_ERR);
        }
        return Results.ok("图片上传成功", file_name);
    }
}
