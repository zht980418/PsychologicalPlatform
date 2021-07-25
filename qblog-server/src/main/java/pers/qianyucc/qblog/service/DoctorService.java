package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.vo.ArticleVO;
import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.TimelineVO;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public PageVO<DoctorVO> getAllDoctors(int page, int limit, String search, String field){
        QueryWrapper<DoctorPO> qw = new QueryWrapper<>();
        if(search.equals("")) qw.select(DoctorPO.class, i->!"content".equals(i.getColumn()));
        else {
            if(field.equals("doctorId"))
                qw.like("doctorId",search).select(DoctorPO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("doctorName"))
                qw.like("doctorName",search).select(DoctorPO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("status"))
                qw.like("status",search).select(DoctorPO.class, i-> !"content".equals(i.getColumn()));
        }


        qw.select(DoctorPO.class, i->!"content".equals(i.getColumn()));
        Page<DoctorPO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<DoctorPO> res = doctorMapper.selectPage(page1,qw);
        List<DoctorVO> doctorVOS = res.getRecords().stream()
                .map(DoctorVO::fromDoctorPO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<doctorVOS.size();i++){
            re.add(doctorVOS.get(i));
        }
        PageVO<DoctorVO> pageVO = PageVO.<DoctorVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }

//    id查
    public DoctorVO getDoctorByid(String doctorid){
        DoctorPO dbDoctor = doctorMapper.selectById(doctorid);
        if(Objects.isNull(dbDoctor)) throw  new BlogException(INVALID_ID);
        doctorMapper.updateById(dbDoctor);
        return DoctorVO.fromDoctorPO(dbDoctor);
    }
}
