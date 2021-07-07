package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale3PO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "量表0", description = "量表0")
public class Scale3DTO {
    @ApiModelProperty(notes = "id", example = "1")
    private int id;
    @ApiModelProperty(notes = "name", example = "1")
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @ApiModelProperty(notes = "remark", example = "1")
    private String remark;
    @ApiModelProperty(notes = "sex", example = "1")
    @NotEmpty(message = "性别不能为空")
    private String sex;
    @ApiModelProperty(notes = "question", example = "1")
    private String question1;
    @ApiModelProperty(notes = "question", example = "1")
    private String question2;
    @ApiModelProperty(notes = "question", example = "1")
    private String question3;
    @ApiModelProperty(notes = "question", example = "1")
    private String question4;
    @ApiModelProperty(notes = "question", example = "1")
    private String question5;
    @ApiModelProperty(notes = "question", example = "1")
    private String question6;
    @ApiModelProperty(notes = "question", example = "1")
    private String question7;
    @ApiModelProperty(notes = "question", example = "1")
    private String question8;
    @ApiModelProperty(notes = "question", example = "1")
    private String question9;
    @ApiModelProperty(notes = "question", example = "1")
    private String question10;

    public Scale3PO toScale3PO(boolean isUpdate) {
        Scale3PO po = new Scale3DTO.Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        return po;
    }

    private static class Converter implements IConverter<Scale3DTO, Scale3PO> {
        @Override
        public Scale3PO convertToPO(Scale3DTO scale3DTO) {
            Scale3PO po = new Scale3PO();
            po.setGmtUpdate(System.currentTimeMillis());
            BeanUtil.copyProperties(scale3DTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
