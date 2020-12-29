package pers.qianyucc.qblog.service;

import cn.hutool.core.convert.*;
import cn.hutool.setting.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pers.qianyucc.qblog.dao.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.entity.*;
import pers.qianyucc.qblog.model.vo.*;

import java.util.*;

@Service
public class CommService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private Setting setting;

    public BlogInfoVO getBlogInfo() {
        BlogSetting blogSetting = BlogSetting.fromSetting(setting);
        BlogInfoVO vo = BlogInfoVO.fromBlogSetting(blogSetting);
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("sum(views) as total_views");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        int totalViews = 0;
        if (!maps.isEmpty() && Objects.nonNull(maps.get(0))) {
            totalViews = Convert.toInt(maps.get(0).getOrDefault("total_views", 0), 0);
        }
        Integer articleCount = articleMapper.selectCount(null);
        vo.setTotalViews(totalViews);
        vo.setArticleCount(articleCount);
        return vo;
    }
}
