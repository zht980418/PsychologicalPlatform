package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.CourseMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.CourseDTO;
import pers.qianyucc.qblog.model.entity.CoursePO;
import pers.qianyucc.qblog.model.vo.CourseVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Transactional(rollbackFor = Exception.class)
//    插入课程
    public void insCourse(CourseDTO courseDTO){
        CoursePO coursePO=courseDTO.toCoursePO();
        courseMapper.insert(coursePO);
    }
//    删除课程
    public void deleteCourse(String courseid){
//        Map<String,Object> map=new HashMap<>();
//        map.put("courseid",courseid);
        courseMapper.deleteById(courseid);
    }
//    更新课程
    public void updateCourse(CourseDTO courseDTO,String courseid){
        CoursePO dbcoursePO = courseMapper.selectById(courseid);
        if(Objects.isNull(dbcoursePO)){
            throw new BlogException(INVALID_ID);
        }
        CoursePO coursePO = courseDTO.toCoursePO();
        coursePO.setCourseid(courseid);
        courseMapper.updateById(coursePO);
    }
//    获取所有课程
    public List<CourseVO> getAllcourse(){
        ArrayList res = new ArrayList();
        QueryWrapper<CoursePO> wrapper=new QueryWrapper<>();
        wrapper.select("courseid","coursetitle","coursedescription","courselink");
        List<Map<String,Object>> maps=courseMapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++){
            res.add(maps.get(i));
        }
        return res;
    }
//    获取单个课程

    public CourseVO getCourse(String courseid){
        CoursePO dbcourse = courseMapper.selectById(courseid);
        CourseVO res = CourseVO.fromCoursePO(dbcourse);
        return res;
    }
}
