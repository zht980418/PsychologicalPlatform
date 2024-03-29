package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale9PO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "量表二", description = "量表2")
public class Scale9DTO {
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

    public Scale9PO toScale9PO(boolean isUpdate) {
        Scale9PO po = new Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        po.setQuizname("scale9");
        int res = 0;
//        计分
        for(int i=1;i<10;i++){
            int startTime =  po.toString().charAt(po.toString().indexOf("question" + i) + 10) - 'A';
            int status = po.toString().charAt(po.toString().indexOf("question" + i) + 12) - 'A';
            int level = po.toString().charAt(po.toString().indexOf("question" + i) + 14) - 'A';
            int lastTime = po.toString().charAt(po.toString().indexOf("question" + i) + 16) - 'A';
            res+=startTime*level*lastTime;


        }
        for(int i=10;i<49;i++){
            int startTime =  po.toString().charAt(po.toString().indexOf("question" + i) + 11) - 'A';
            int status = po.toString().charAt(po.toString().indexOf("question" + i) + 13) - 'A';
            int level = po.toString().charAt(po.toString().indexOf("question" + i) + 15) - 'A';
            int lastTime = po.toString().charAt(po.toString().indexOf("question" + i) + 17) - 'A';
            res+=startTime*level*lastTime;
        }
        if(!po.getQuestion49().equals("")){
            int startTime =  po.toString().charAt(po.toString().indexOf("question" + 49) + 11) - 'A';
            int status = po.toString().charAt(po.toString().indexOf("question" + 49) + 13) - 'A';
            int level = po.toString().charAt(po.toString().indexOf("question" + 49) + 15) - 'A';
            int lastTime = po.toString().charAt(po.toString().indexOf("question" + 49) + 17) - 'A';
            res+=startTime*level*lastTime;
        }
        if(!po.getQuestion50().equals("")){
            int startTime =  po.toString().charAt(po.toString().indexOf("question" + 50) + 11) - 'A';
            int status = po.toString().charAt(po.toString().indexOf("question" + 50) + 13) - 'A';
            int level = po.toString().charAt(po.toString().indexOf("question" + 50) + 15) - 'A';
            int lastTime = po.toString().charAt(po.toString().indexOf("question" + 50) + 17) - 'A';
            res+=startTime*level*lastTime;
        }
        po.setResult(res);
        return po;
    }

    private static class Converter implements IConverter<Scale9DTO, Scale9PO> {
        @Override
        public Scale9PO convertToPO(Scale9DTO scale9DTO) {
            Scale9PO po = new Scale9PO();
            po.setGmtUpdate(System.currentTimeMillis());
            BeanUtil.copyProperties(scale9DTO, po, CopyOptions.create().ignoreNullValue());
            return po;
        }
    }
}
