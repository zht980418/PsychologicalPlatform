package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.RecordformPO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "咨询记录表格",description = "前端的咨询记录")
public class RecordformDTO {
//    @NotEmpty(message = "appid不能为空")
    @ApiModelProperty(notes = "consultno", example="1")
    private int consultno;
    @ApiModelProperty(notes = "type", example="1")
    private String type;
    @ApiModelProperty(notes = "userid", example="1")
    private String userid;
    @ApiModelProperty(notes = "doctorname", example="1")
    private String doctorname;
    @ApiModelProperty(notes = "date1", example="1")
    private String date1;
    @ApiModelProperty(notes = "date2", example="1")
    private String date2;
    @ApiModelProperty(notes = "date3", example="1")
    private String date3;
    @ApiModelProperty(notes = "consulttype", example="1")
    private String consulttype;
    @ApiModelProperty(notes = "mainpoint", example="1")
    private String mainpoint;
    @ApiModelProperty(notes = "nextexist", example="1")
    private String nextexist;
    @ApiModelProperty(notes = "specialattention", example="1")
    private String specialattention;
    @ApiModelProperty(notes = "followplan", example="1")
    private String followplan;
    @ApiModelProperty(notes = "sign", example="1")
    private String sign;
    @ApiModelProperty(notes = "complaint", example="1")
    private String complaint;
    @ApiModelProperty(notes = "impression", example="1")
    private String impression;

    public RecordformPO toRecordformPO() {
        return new RecordformDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<RecordformDTO, RecordformPO> {
        @Override
        public RecordformPO convertToPO(RecordformDTO recordformDTO) {
            RecordformPO po = new RecordformPO();
            BeanUtil.copyProperties(recordformDTO, po);
            return po;
        }
    }
}
