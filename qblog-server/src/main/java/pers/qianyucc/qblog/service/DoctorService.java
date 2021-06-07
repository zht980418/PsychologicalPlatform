package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.DoctorMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.DoctorDTO;
import pers.qianyucc.qblog.model.entity.ArticlePO;
import pers.qianyucc.qblog.model.entity.DoctorPO;
import pers.qianyucc.qblog.model.vo.ArticleVO;
import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.model.vo.TimelineVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
//    增
    public void insDoctor(DoctorDTO doctorDTO){
        DoctorPO doctorPO = doctorDTO.toDoctorPO();
        doctorMapper.insert(doctorPO);
    }
//    删
    public void deleteDoctor(String doctorid){
        Map<String,Object> map = new HashMap<>();
        map.put("doctorid",doctorid);
        doctorMapper.deleteByMap(map);
    }
//    改
    public void updateDoctor(DoctorDTO doctorDTO,String doctorid){
        DoctorPO dbDoctor = doctorMapper.selectById(doctorid);
        if(Objects.isNull(dbDoctor)) throw  new BlogException(INVALID_ID);
        DoctorPO doctorPO = doctorDTO.toDoctorPO();
        doctorPO.setDoctorid(doctorid);
        doctorMapper.updateById(doctorPO);
    }
//    批量查
    public List<DoctorVO> getAllDoctors(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<DoctorPO> wrapper = new QueryWrapper<>();
        wrapper.select("doctorid","doctorname","status","description","avatar");
        List<Map<String,Object>> maps = doctorMapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++){
            res.add(maps.get(i));
        }
        return  res;
    }

//    id查
    public DoctorVO getDoctorByid(String doctorid){
        DoctorPO dbDoctor = doctorMapper.selectById(doctorid);
        if(Objects.isNull(dbDoctor)) throw  new BlogException(INVALID_ID);
        doctorMapper.updateById(dbDoctor);
        return DoctorVO.fromDoctorPO(dbDoctor);
    }
}
