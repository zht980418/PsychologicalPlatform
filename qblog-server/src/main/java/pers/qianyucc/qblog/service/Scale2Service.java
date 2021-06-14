package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale2Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale2DTO;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.vo.Scale2VO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale2Service {
    @Autowired
    private Scale2Mapper scale2Mapper;

    public void insScale2(Scale2DTO scale2DTO) {
        Scale2PO scale2PO =  scale2DTO.toScale2PO();
        scale2Mapper.insert(scale2PO);
    }

    public void deleteScale2(int scale2id){
        Map<String,Object> map =new HashMap<>();
        map.put("scale2id",scale2id);
        scale2Mapper.deleteByMap(map);
    }

    public void updateScale2(Scale2DTO scale2DTO, int scale2id){
        Scale2PO dbscale2 = scale2Mapper.selectById(scale2id);
        if (Objects.isNull(dbscale2)) {
            throw new BlogException(INVALID_ID);
        }
        Scale2PO scale2PO = scale2DTO.toScale2PO();
//        System.out.println(scale2PO.getScale2id());
        scale2PO.setId(scale2id);
        scale2Mapper.updateById(scale2PO);
    }

    public List<Scale2VO> getAllScale2s(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale2PO> wrapper = new QueryWrapper<>();
        List<Map<String, Object>> maps = scale2Mapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
}
