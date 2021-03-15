package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.CoursePO;

@Data
public class CourseVO {
    private String courseid;
    private String coursetitle;
    private String coursedescription;
    private String courselink;

    public static CourseVO fromCoursePO(CoursePO coursePO){return new CourseVO.Converter().convertToVO(coursePO);}
    private static class Converter implements IConverter<CoursePO, CourseVO>{
        @Override
        public CourseVO convertToVO(CoursePO coursePO){
            final CourseVO courseVO = new CourseVO();
            BeanUtil.copyProperties(coursePO, courseVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return courseVO;
        }
    }
}
