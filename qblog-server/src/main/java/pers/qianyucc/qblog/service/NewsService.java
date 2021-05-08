package pers.qianyucc.qblog.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.NewsMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.NewsDTO;
import pers.qianyucc.qblog.model.entity.NewsPO;
import pers.qianyucc.qblog.model.entity.NewsPO;
import pers.qianyucc.qblog.model.vo.NewsVO;

import java.util.*;

import java.util.HashMap;
import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;
//    增
    public void insNews(NewsDTO newsDTO){
        NewsPO newsPO = newsDTO.toNewsPO(false);
        newsMapper.insert(newsPO);
    }
//    删
    public void deleteNews(int id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        newsMapper.deleteByMap(map);
    }
//    改
    public void updateNews(NewsDTO newsDTO,int id){
        NewsPO dbNews = newsMapper.selectById(id);
        if(Objects.isNull(dbNews)) throw new BlogException(INVALID_ID);
        NewsPO newsPO = newsDTO.toNewsPO(true);
        newsPO.setId(id);
        newsMapper.updateById(newsPO);
    }
//    批量查
    public List<NewsVO> getAllNews(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<NewsPO> wrapper = new QueryWrapper<>();
        wrapper.select("id","cover","title","content","author","tabloid");
        List<Map<String, Object>> maps = newsMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
//    id查
    public NewsVO getNewsByid(int id){
        NewsPO dbNews = newsMapper.selectById(id);
        if(Objects.isNull(dbNews)) throw new BlogException(INVALID_ID);
        dbNews.setViews(dbNews.getViews()+1);
        newsMapper.updateById(dbNews);
        return  NewsVO.fromNewsPO(dbNews);

    }
}
