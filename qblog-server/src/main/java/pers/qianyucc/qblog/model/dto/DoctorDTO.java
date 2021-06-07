package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.DoctorPO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "医生信息", description = "医生信息")
public class DoctorDTO {
    @NotEmpty(message = "医生id不能为空")
    @ApiModelProperty(notes = "医生id",example = "zhangsan123")
    private String doctorid;
    @NotEmpty(message = "医生姓名不能为空")
    @ApiModelProperty(notes = "医生姓名",example = "张三")
    private String doctorname;
    @NotEmpty(message = "医生状态不能为空")
    @ApiModelProperty(notes = "医生状态",example = "可预约")
    private String status;
    @ApiModelProperty(notes = "描述",example = "医生简介")
    private String description;
    @ApiModelProperty(notes = "医生头像",example = "链接")
    private String avatar;

    public DoctorPO toDoctorPO() {
        DoctorPO po = new DoctorDTO.Converter().convertToPO(this);
        return po;
    }

    private static class Converter implements IConverter<DoctorDTO, DoctorPO> {
        @Override
        public DoctorPO convertToPO(DoctorDTO doctorDTO) {
            DoctorPO po = new DoctorPO();
            BeanUtil.copyProperties(doctorDTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
