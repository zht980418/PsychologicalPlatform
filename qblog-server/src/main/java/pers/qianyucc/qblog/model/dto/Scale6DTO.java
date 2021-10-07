package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale6PO;
import pers.qianyucc.qblog.model.entity.Scale6PO;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@ApiModel(value = "量表0", description = "量表0")
public class Scale6DTO {
    @ApiModelProperty(notes = "id", example = "1")
    private String id;
    @ApiModelProperty(notes = "uid", example = "1")
    private String uid;
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

    public Scale6PO toScale6PO(boolean isUpdate) {
        Scale6PO po = new Scale6DTO.Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        po.setQuizname("scale6");
        int res = 0;
//        计分
        for(int i=1;i<10;i++){
            if(po.toString().charAt(po.toString().indexOf("question" + i) + 10)=='Y')
                res+=1;
        }
        for(int i=10;i<29;i++){
            if(po.toString().charAt(po.toString().indexOf("question" + i) + 11)=='Y')
                res+=1;
        }
        po.setResult(res);
        return po;
    }

    private static class Converter implements IConverter<Scale6DTO, Scale6PO> {
        @Override
        public Scale6PO convertToPO(Scale6DTO scale6DTO) {
            Scale6PO po = new Scale6PO();
            po.setGmtUpdate(System.currentTimeMillis());

            BeanUtil.copyProperties(scale6DTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
