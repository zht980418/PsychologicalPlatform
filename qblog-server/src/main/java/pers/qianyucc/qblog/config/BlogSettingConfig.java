package pers.qianyucc.qblog.config;

import cn.hutool.core.io.*;
import cn.hutool.core.util.*;
import cn.hutool.setting.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import java.io.*;

@Slf4j
@Configuration
public class BlogSettingConfig {
    @Value("${setting.path}")
    private String settingFilePath;

    @Bean
    public Setting setting() {
        File file = new File(settingFilePath);
        Setting setting;
        if (!file.exists()) {
            FileUtil.touch(file);
            setting = new Setting(file, CharsetUtil.CHARSET_UTF_8, false);
            setting.set("title", "心理健康中心");
            setting.set("desc", "一切都是暂时的，一切都会消逝，让失去的变得可爱。");
            setting.set("covers", "https://tvax1.sinaimg.cn/mw1024/bfe05ea9ly1fxgu8jys3fj21hc0u0k0j.jpg,https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgunx09dtj21hc0u0q81.jpg,https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgv2t92yyj21hc0u0qb9.jpg");
            setting.set("avatar", "https://portrait.gitee.com/uploads/avatars/user/772/2317865_qianyucc_1589023575.png");
            setting.set("nickname", "张浩天");
            setting.set("username", "admin");
            setting.set("password", "827ccb0eea8a706c4c34a16891f84e7b");
            setting.store(file.getAbsolutePath());
        }
        setting = new Setting(file, CharsetUtil.CHARSET_UTF_8, false);
        setting.autoLoad(true);
        //log.info(BlogSetting.fromSetting(setting).toString());
        return setting;
    }
}
