package pers.qianyucc.qblog.service;

import cn.hutool.crypto.digest.*;
import lombok.extern.slf4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import pers.qianyucc.qblog.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogApplication.class})
public class UserServiceTest {
    @Test
    public void testMD5() {
        String hex = MD5.create().digestHex("12345");
        System.out.println(hex);
    }
}