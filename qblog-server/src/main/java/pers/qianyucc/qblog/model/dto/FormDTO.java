package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.*;
import io.swagger.annotations.*;
import lombok.*;
import pers.qianyucc.qblog.model.entity.*;

import javax.validation.constraints.*;

@Data
@ApiModel(value = "表格类", description = "前端传来的表格信息")
public class FormDTO {
    @NotEmpty(message = "doctorid不能为空")
    @ApiModelProperty(notes = "doctorid", example = "zhangsan123")
    private String doctorid;

    @NotEmpty(message = "orderid不能为空")
    @ApiModelProperty(notes = "orderid", example = "orderid")
    private String orderid;

    @NotEmpty(message = "uid不能为空")
    @ApiModelProperty(notes = "uid", example = "123")
    private String uid;

    @NotEmpty(message = "type不能为空")
    @ApiModelProperty(notes = "type", example = "线上")
    private String type;

    @NotEmpty(message = "name不能为空")
    @ApiModelProperty(notes = "name", example = "张三")
    private String name;

    @NotEmpty(message = "ordertime不能为空")
    @ApiModelProperty(notes = "ordertime", example = "09:00:00")
    private String ordertime;

    @NotEmpty(message = "gender不能为空")
    @ApiModelProperty(notes = "gender", example = "男")
    private String gender;

    @NotEmpty(message = "birth不能为空")
    @ApiModelProperty(notes = "birth", example = "1998-5-13")
    private String birth;

    @NotEmpty(message = "occupation不能为空")
    @ApiModelProperty(notes = "occupation", example = "学生")
    private String occupation;

    @NotEmpty(message = "phone不能为空")
    @ApiModelProperty(notes = "phone", example = "19934271418")
    private String phone;

    @ApiModelProperty(notes = "address", example = "电子科技大学")
    private String address;

    @NotEmpty(message = "emergency不能为空")
    @ApiModelProperty(notes = "emergency", example = "emergency")
    private String emergency;

    @NotEmpty(message = "emergencyphone不能为空")
    @ApiModelProperty(notes = "emergencyphone", example = "19934271418")
    private String emergencyphone;

    @ApiModelProperty(notes = "question", example = "问题？")
    private String question;

    @ApiModelProperty(notes = "orderid", example = "家庭")
    private String family;

    @ApiModelProperty(notes = "expectation", example = "期望")
    private String expectation;

    @ApiModelProperty(notes = "history", example = "历史")
    private String history;

    @ApiModelProperty(notes = "test", example = "测试")
    private String test;

    @ApiModelProperty(notes = "sleep", example = "睡觉")
    private String sleep;

    @ApiModelProperty(notes = "relationship", example = "休息")
    private String relationship;

    @ApiModelProperty(notes = "stress", example = "压力")
    private String stress;

    @ApiModelProperty(notes = "mood", example = "脾气")
    private String mood;

    @ApiModelProperty(notes = "hurt", example = "伤害")
    private String hurt;

    @ApiModelProperty(notes = "suicide", example = "自杀")
    private String suicide;

    @ApiModelProperty(notes = "roomid", example = "1号")
    private String roomid;

    @NotEmpty(message = "start不能为空")
    @ApiModelProperty(notes = "start", example = "09:00:00")
    private String start;

    @NotEmpty(message = "end不能为空")
    @ApiModelProperty(notes = "end", example = "10:00:00")
    private String end;

    @ApiModelProperty(notes = "status", example = "1")
    private String status;

    public FormPO toFormPO() {
        return new FormDTO.Converter().convertToPO(this);
    }


    private static class Converter implements IConverter<FormDTO, FormPO> {
        @Override
        public FormPO convertToPO(FormDTO formDTO) {
            FormPO po = new FormPO();
            BeanUtil.copyProperties(formDTO, po);
            return po;
        }
    }

}
