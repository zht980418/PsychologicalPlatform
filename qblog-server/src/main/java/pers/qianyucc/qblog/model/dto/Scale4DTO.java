package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale4PO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "量表0", description = "量表0")
public class Scale4DTO {
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
    @ApiModelProperty(notes = "question", example = "1")
    private String question11;
    @ApiModelProperty(notes = "question", example = "1")
    private String question12;
    @ApiModelProperty(notes = "question", example = "1")
    private String question13;
    @ApiModelProperty(notes = "question", example = "1")
    private String question14;
    @ApiModelProperty(notes = "question", example = "1")
    private String question15;
    @ApiModelProperty(notes = "question", example = "1")
    private String question16;
    @ApiModelProperty(notes = "question", example = "1")
    private String question17;
    @ApiModelProperty(notes = "question", example = "1")
    private String question18;
    @ApiModelProperty(notes = "question", example = "1")
    private String question19;
    @ApiModelProperty(notes = "question", example = "1")
    private String question20;
    private String question21;
    private String question22;
    private String question23;
    private String question24;
    private String question25;
    private String question26;
    private String question27;
    private String question28;
    private String question29;
    private String question30;
    private String question31;
    private String question32;
    private String question33;
    private String question34;
    private String question35;
    private String question36;
    private String question37;
    private String question38;
    private String question39;
    private String question40;
    private String question41;
    private String question42;
    private String question43;
    private String question44;
    private String question45;
    private String question46;
    private String question47;
    private String question48;
    private String question49;
    private String question50;
    private String question51;
    private String question52;
    private String question53;
    private String question54;
    private String question55;
    private String question56;
    private String question57;
    private String question58;
    private String question59;
    private String question60;
    private String question61;
    private String question62;
    private String question63;
    private String question64;
    private String question65;
    private String question66;
    private String question67;
    private String question68;
    private String question69;
    private String question70;
    private String question71;
    private String question72;
    private String question73;
    private String question74;
    private String question75;
    private String question76;
    private String question77;
    private String question78;
    private String question79;
    private String question80;
    private String question81;
    private String question82;
    private String question83;
    private String question84;
    private String question85;
    private String question86;
    private String question87;
    private String question88;
    private String question89;
    private String question90;

    public Scale4PO toScale4PO(boolean isUpdate) {
        Scale4PO po = new Scale4DTO.Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        return po;
    }

    private static class Converter implements IConverter<Scale4DTO, Scale4PO> {
        @Override
        public Scale4PO convertToPO(Scale4DTO scale4DTO) {
            Scale4PO po = new Scale4PO();
            po.setGmtUpdate(System.currentTimeMillis());
            BeanUtil.copyProperties(scale4DTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
