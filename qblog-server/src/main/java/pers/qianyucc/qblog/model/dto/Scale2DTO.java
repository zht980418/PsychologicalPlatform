package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale2PO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "量表二", description = "量表2")
public class Scale2DTO {
    @ApiModelProperty(notes = "id", example = "1")
    private String id;
    @ApiModelProperty(notes = "uid", example = "1")
    private int uid;
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

    public Scale2PO toScale2PO(boolean isUpdate) {
        Scale2PO po = new Scale2DTO.Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        po.setQuizname("scale2");
        int res = 0;
//        计分
        for(int i=1;i<10;i++){
            if(i==5||i==9)
                res += 4 - po.toString().charAt(po.toString().indexOf("question" + i) + 10) + 'A';
            else
                res += po.toString().charAt(po.toString().indexOf("question" + i) + 10) - 'A' + 1;


        }
        for(int i=10;i<21;i++){
            if(i==13||i==17||i==19) {
                System.out.println(4 - po.toString().charAt(po.toString().indexOf("question" + i) + 11) + 'A');
                res += 4 - po.toString().charAt(po.toString().indexOf("question" + i) + 11) + 'A';
            }
            else {
                System.out.println(po.toString().charAt(po.toString().indexOf("question" + i) + 11) - 'A' + 1);
                res += po.toString().charAt(po.toString().indexOf("question" + i) + 11) - 'A' + 1;
            }
        }
        res*=1.25;
        po.setResult(res);
        return po;
    }

    private static class Converter implements IConverter<Scale2DTO, Scale2PO> {
        @Override
        public Scale2PO convertToPO(Scale2DTO scale2DTO) {
            Scale2PO po = new Scale2PO();
            po.setGmtUpdate(System.currentTimeMillis());
            BeanUtil.copyProperties(scale2DTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
