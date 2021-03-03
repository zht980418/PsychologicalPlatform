package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.SchedulePO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "日程表" , description = "前端传来的日程表信息")
public class ScheduleDTO {
    @NotEmpty(message = "appid不能为空")
    @ApiModelProperty(notes = "appid", example="1")
    private String appid;

    @ApiModelProperty(notes = "doctorid", example = "zhangsan123")
    private String doctorid;

    @ApiModelProperty(notes = "docotorname", example = "zhangsan")
    private String doctorname;

    @ApiModelProperty(notes = "开始时间", example = "9:00:00")
    private String start;

    @ApiModelProperty(notes = "结束时间", example = "19:00:00")
    private String end;

    @ApiModelProperty(notes = "工作日", example = "[1,3,5]")
    private String daysofweek;

    public SchedulePO toSchedulePO() {
        return new ScheduleDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<ScheduleDTO, SchedulePO> {
        @Override
        public SchedulePO convertToPO(ScheduleDTO scheduleDTO) {
            SchedulePO po = new SchedulePO();
            BeanUtil.copyProperties(scheduleDTO, po);
            return po;
        }
    }
}
