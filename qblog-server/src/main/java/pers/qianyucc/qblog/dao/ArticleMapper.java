package pers.qianyucc.qblog.dao;

import com.baomidou.mybatisplus.core.mapper.*;
import org.apache.ibatis.annotations.Mapper;
import pers.qianyucc.qblog.model.entity.*;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePO> {
}
