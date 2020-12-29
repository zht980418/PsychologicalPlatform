package pers.qianyucc.qblog.provider;

import cn.hutool.core.io.*;
import cn.hutool.core.util.*;
import lombok.extern.slf4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import pers.qianyucc.qblog.*;

import java.io.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogApplication.class})
public class MinioFileProviderTest {
    @Autowired
    private MinioFileProvider minioFileProvider;

    @Test
    public void testUploadFile() {
        String originalFilename = "E:\\User\\Pictures\\Screenshots\\屏幕截图(405).png";
        BufferedInputStream in = FileUtil.getInputStream(originalFilename);
        String fileName = IdUtil.fastUUID();
        String[] arr = originalFilename.split("\\.");
        if (ArrayUtil.isNotEmpty(arr)) {
            fileName += "." + arr[arr.length - 1];
        }
        log.info("fileName : {}", fileName + ".png");
        minioFileProvider.uploadFile(in, fileName, "application/octet-stream");
    }

    @Test
    public void testDownloadFile() {
        InputStream in = minioFileProvider.downloadFile("c824916f-d85e-4509-b138-f74addb4e77e.png");
        FileUtil.writeFromStream(in, "f:/demo.png");
    }
}