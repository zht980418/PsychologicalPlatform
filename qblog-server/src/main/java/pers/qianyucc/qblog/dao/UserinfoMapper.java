package pers.qianyucc.qblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.qianyucc.qblog.model.entity.UserinfoPO;

@Mapper
public interface UserinfoMapper extends BaseMapper<UserinfoPO> {
}
