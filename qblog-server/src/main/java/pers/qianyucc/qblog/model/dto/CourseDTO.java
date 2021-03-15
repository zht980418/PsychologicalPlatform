package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.RoomPO;
import pers.qianyucc.qblog.model.entity.CoursePO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "课程信息",description = "前端传回的课程信息")
public class CourseDTO {
    @NotEmpty(message = "咨询室id不能为空")
    @ApiModelProperty(notes = " courseid", example = "123")
    private String courseid;

    @ApiModelProperty(notes = "coursetitle", example = "数学")
    private String coursetitle;

    @ApiModelProperty(notes = "coursedescription", example = "课程描述")
    private String coursedescription;

    @ApiModelProperty(notes = "courselink", example = "链接")
    private String courselink;

    public CoursePO toCoursePO() {
        return new CourseDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<CourseDTO, CoursePO> {
        @Override
        public CoursePO convertToPO(CourseDTO courseDTO) {
            CoursePO po = new CoursePO();
            BeanUtil.copyProperties(courseDTO, po);
            return po;
        }
    }
}
