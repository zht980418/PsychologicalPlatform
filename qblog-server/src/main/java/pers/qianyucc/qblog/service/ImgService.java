package pers.qianyucc.qblog.service;

import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;


import java.io.*;
import java.util.UUID;

@Slf4j
@Service
public class ImgService {

    public static String saveImg(MultipartFile multipartFile, String filePath1, String filePath2) throws IOException {
       File file = new File(filePath1);
       if(!file.exists()) file.mkdirs();
//        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = UUID.randomUUID().toString().replace("-", "")+ ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath1 + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = inputStream.read(bs))!=-1){
            bos.write(bs,0,len);
        }
        bos.flush();
        bos.close();

        File file2 = new File(filePath2);
        if(!file2.exists()) file2.mkdirs();
//        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        InputStream inputStream2 = multipartFile.getInputStream();
//        String fileName2 = UUID.randomUUID().toString().replace("-", "")+ ".png";
        BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(filePath2 + File.separator + fileName));
        byte[] bs2 = new byte[1024];
        int len2;
        while ((len2 = inputStream2.read(bs2))!=-1){
            bos2.write(bs2,0,len2);
        }
        bos2.flush();
        bos2.close();

        return  fileName;
    }

    public static String saveBaseImg(String img_data, String path) throws Exception {
        if(StringUtils.isNotBlank(img_data)){
            img_data = img_data.substring(img_data.indexOf(",")+1);
            // 读取保存文件
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(img_data);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //保存图片
            String fileName = UUID.randomUUID().toString().replace("-", "") + ".png";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path+File.separator+fileName));
            bos.write(b);
            bos.flush();
            bos.close();
            Thread.sleep(1000);
            return fileName;
        }
        return null;
    }
}
