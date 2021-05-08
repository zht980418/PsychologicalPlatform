package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.DejueMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.DejueDTO;
import pers.qianyucc.qblog.model.entity.DejuePO;
import pers.qianyucc.qblog.model.vo.DejueVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class DejueService {
    @Autowired
    private DejueMapper dejueMapper;
    //    增
    public void insDejue(DejueDTO dejueDTO){
        DejuePO dejuePO = dejueDTO.toDejuePO(false);
        dejueMapper.insert(dejuePO);
    }
    //    删
    public void deleteDejue(int id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        dejueMapper.deleteByMap(map);
    }
    //    改
    public void updateDejue(DejueDTO dejueDTO,int id){
        DejuePO dbDejue = dejueMapper.selectById(id);
        if(Objects.isNull(dbDejue)) throw new BlogException(INVALID_ID);
        DejuePO dejuePO = dejueDTO.toDejuePO(true);
        dejuePO.setId(id);
        dejueMapper.updateById(dejuePO);
    }
    //    批量查
    public List<DejueVO> getAllDejue(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<DejuePO> wrapper = new QueryWrapper<>();
        wrapper.select("id","cover","title","tabloid","author","link");
        List<Map<String, Object>> maps = dejueMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
    //    id查
    public DejueVO getDejueByid(int id){
        DejuePO dbDejue = dejueMapper.selectById(id);
        if(Objects.isNull(dbDejue)) throw new BlogException(INVALID_ID);
        dbDejue.setViews(dbDejue.getViews()+1);
        dejueMapper.updateById(dbDejue);
        return DejueVO.fromDejuePO(dbDejue);

    }

}
