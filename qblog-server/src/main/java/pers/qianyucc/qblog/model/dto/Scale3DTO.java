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

    public Scale3PO toScale3PO(boolean isUpdate) {
        Scale3PO po = new Scale3DTO.Converter().convertToPO(this);
        po.setGmtCreate(isUpdate ? null : po.getGmtUpdate());
        po.setQuizname("scale3");
        int ans5 = 0;
        for(char c:po.getQuestion5().toCharArray())
            ans5+=c-'A'+1;
        int ans6 = 0;
        for(char c:po.getQuestion6().toCharArray()){
            if(c-'A'>=0&&c-'A'<=6) ans6++;
            else if(c=='、') ans6++;
        }
        if(po.getQuestion6().contains("H")) ans6++;
        if(po.getQuestion6().charAt(1)=='1') ans6 = 0;

        int ans7 = 0;
        for(char c:po.getQuestion7().toCharArray()){
            if(c-'A'>=0&&c-'A'<=6) ans7++;
            else if(c=='、') ans7++;
        }
        if(po.getQuestion7().contains("H")) ans7++;
        if(po.getQuestion7().charAt(1)=='1') ans7 = 0;
        System.out.println(ans5);
        System.out.println(ans6);
        System.out.println(ans7);
//        客观支持分
        int ob = po.getQuestion2().charAt(0)-'A'+1+ans6+ans7;
        po.setObjective(ob);
        System.out.println(ob);
//        主观支持分
        int sb = po.getQuestion1().charAt(0)-'A'+1+po.getQuestion3().charAt(0)-'A'+1+po.getQuestion4().charAt(0)-'A'+1+ans5;
        po.setSubjective(sb);
        System.out.println(sb);
//        对支持的利用度
        int sp = po.getQuestion8().charAt(0)-'A'+1+po.getQuestion9().charAt(0)-'A'+1+po.getQuestion10().charAt(0)-'A'+1;
        po.setSupport(sp);
        System.out.println(sp);
//        总分
        int sm = ob+sb+sp;
        po.setSum(sm);
        System.out.println(sm);
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
