package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale4PO;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@ApiModel(value = "量表0", description = "量表0")
public class Scale4DTO {
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
        po.setQuizname("scale4");
//        躯体化
        BigDecimal a = new BigDecimal((po.getQuestion1().charAt(0)-'A' )+(po.getQuestion4().charAt(0)-'A' )
                +(po.getQuestion12().charAt(0)-'A' )+(po.getQuestion27().charAt(0)-'A' )
                +(po.getQuestion40().charAt(0)-'A' )+(po.getQuestion42().charAt(0)-'A' )
                +(po.getQuestion48().charAt(0)-'A' )+(po.getQuestion49().charAt(0)-'A' )
                +(po.getQuestion52().charAt(0)-'A' )+(po.getQuestion53().charAt(0)-'A' )
                +(po.getQuestion56().charAt(0)-'A' )+(po.getQuestion58().charAt(0)-'A' ));
        BigDecimal b = new BigDecimal(12);
        po.setSomatization(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//        总分
        a = new BigDecimal((po.getQuestion1().charAt(0)-'A' )+(po.getQuestion4().charAt(0)-'A' )
                +(po.getQuestion2().charAt(0)-'A' )
                +(po.getQuestion3().charAt(0)-'A' )
                +(po.getQuestion4().charAt(0)-'A' )
                +(po.getQuestion5().charAt(0)-'A' )
                +(po.getQuestion6().charAt(0)-'A' )
                +(po.getQuestion7().charAt(0)-'A' )
                +(po.getQuestion8().charAt(0)-'A' )
                +(po.getQuestion9().charAt(0)-'A' )
                +(po.getQuestion10().charAt(0)-'A' )
                +(po.getQuestion11().charAt(0)-'A' )
                +(po.getQuestion12().charAt(0)-'A' )
                +(po.getQuestion13().charAt(0)-'A' )
                +(po.getQuestion14().charAt(0)-'A' )
                +(po.getQuestion15().charAt(0)-'A' )
                +(po.getQuestion16().charAt(0)-'A' )
                +(po.getQuestion17().charAt(0)-'A' )
                +(po.getQuestion18().charAt(0)-'A' )
                +(po.getQuestion19().charAt(0)-'A' )
                +(po.getQuestion20().charAt(0)-'A' )
                +(po.getQuestion21().charAt(0)-'A' )
                +(po.getQuestion22().charAt(0)-'A' )
                +(po.getQuestion23().charAt(0)-'A' )
                +(po.getQuestion24().charAt(0)-'A' )
                +(po.getQuestion25().charAt(0)-'A' )
                +(po.getQuestion26().charAt(0)-'A' )
                +(po.getQuestion12().charAt(0)-'A' )
                +(po.getQuestion27().charAt(0)-'A' )
                +(po.getQuestion28().charAt(0)-'A' )
                +(po.getQuestion29().charAt(0)-'A' )
                +(po.getQuestion30().charAt(0)-'A' )
                +(po.getQuestion31().charAt(0)-'A' )
                +(po.getQuestion32().charAt(0)-'A' )
                +(po.getQuestion33().charAt(0)-'A' )
                +(po.getQuestion34().charAt(0)-'A' )
                +(po.getQuestion35().charAt(0)-'A' )
                +(po.getQuestion36().charAt(0)-'A' )
                +(po.getQuestion37().charAt(0)-'A' )
                +(po.getQuestion38().charAt(0)-'A' )
                +(po.getQuestion39().charAt(0)-'A' )
                +(po.getQuestion40().charAt(0)-'A' )
                +(po.getQuestion41().charAt(0)-'A' )
                +(po.getQuestion42().charAt(0)-'A' )
                +(po.getQuestion43().charAt(0)-'A' )
                +(po.getQuestion44().charAt(0)-'A' )
                +(po.getQuestion45().charAt(0)-'A' )
                +(po.getQuestion46().charAt(0)-'A' )
                +(po.getQuestion47().charAt(0)-'A' )
                +(po.getQuestion48().charAt(0)-'A' )
                +(po.getQuestion49().charAt(0)-'A' )
                +(po.getQuestion50().charAt(0)-'A' )
                +(po.getQuestion51().charAt(0)-'A' )
                +(po.getQuestion52().charAt(0)-'A' )
                +(po.getQuestion53().charAt(0)-'A' )
                +(po.getQuestion54().charAt(0)-'A' )
                +(po.getQuestion55().charAt(0)-'A' )
                +(po.getQuestion56().charAt(0)-'A' )
                +(po.getQuestion57().charAt(0)-'A' )
                +(po.getQuestion58().charAt(0)-'A' )
                +(po.getQuestion59().charAt(0)-'A' )
                +(po.getQuestion60().charAt(0)-'A' )
                +(po.getQuestion61().charAt(0)-'A' )
                +(po.getQuestion62().charAt(0)-'A' )
                +(po.getQuestion63().charAt(0)-'A' )
                +(po.getQuestion64().charAt(0)-'A' )
                +(po.getQuestion65().charAt(0)-'A' )
                +(po.getQuestion66().charAt(0)-'A' )
                +(po.getQuestion67().charAt(0)-'A' )
                +(po.getQuestion68().charAt(0)-'A' )
                +(po.getQuestion69().charAt(0)-'A' )
                +(po.getQuestion70().charAt(0)-'A' )
                +(po.getQuestion71().charAt(0)-'A' )
                +(po.getQuestion72().charAt(0)-'A' )
                +(po.getQuestion73().charAt(0)-'A' )
                +(po.getQuestion74().charAt(0)-'A' )
                +(po.getQuestion75().charAt(0)-'A' )
                +(po.getQuestion76().charAt(0)-'A' )
                +(po.getQuestion77().charAt(0)-'A' )
                +(po.getQuestion78().charAt(0)-'A' )
                +(po.getQuestion79().charAt(0)-'A' )
                +(po.getQuestion80().charAt(0)-'A' )
                +(po.getQuestion81().charAt(0)-'A' )
                +(po.getQuestion82().charAt(0)-'A' )
                +(po.getQuestion83().charAt(0)-'A' )
                +(po.getQuestion84().charAt(0)-'A' )
                +(po.getQuestion85().charAt(0)-'A' )
                +(po.getQuestion86().charAt(0)-'A' )
                +(po.getQuestion87().charAt(0)-'A' )
                +(po.getQuestion88().charAt(0)-'A' )
                +(po.getQuestion89().charAt(0)-'A' )
                +(po.getQuestion90().charAt(0)-'A' )
        );
        b = new BigDecimal(90);
        po.setSumaverage(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//      阳性症状
        int yang = 0;
        int yin = 0;
        for(int i=1;i<10;i++){
            if(po.toString().charAt(po.toString().indexOf("question"+i)+10)-'A'<1) yin++;
            else yang++;
        }
        for(int i=10;i<91;i++){
            if(po.toString().charAt(po.toString().indexOf("question"+i)+11)-'A'<1) yin++;
            else yang++;
        }
//        a延续为总分
        b = new BigDecimal(yang);
        System.out.println(a);
        System.out.println(b);
        po.setPositive(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//        强迫症状
        a = new BigDecimal((po.getQuestion3().charAt(0)-'A' )+(po.getQuestion9().charAt(0)-'A' )
                +(po.getQuestion10().charAt(0)-'A' )+(po.getQuestion28().charAt(0)-'A' )
                +(po.getQuestion38().charAt(0)-'A' )+(po.getQuestion45().charAt(0)-'A' )
                +(po.getQuestion46().charAt(0)-'A' )+(po.getQuestion51().charAt(0)-'A' )
                +(po.getQuestion55().charAt(0)-'A' )+(po.getQuestion65().charAt(0)-'A' ));
        b = new BigDecimal(10);
        po.setObsessiveCompulsive(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//      人际关系敏感度
        a = new BigDecimal((po.getQuestion6().charAt(0)-'A' )+(po.getQuestion21().charAt(0)-'A' )
                +(po.getQuestion34().charAt(0)-'A' )+(po.getQuestion36().charAt(0)-'A' )
                +(po.getQuestion37().charAt(0)-'A' )+(po.getQuestion41().charAt(0)-'A' )
                +(po.getQuestion61().charAt(0)-'A' )+(po.getQuestion69().charAt(0)-'A' )
                +(po.getQuestion73().charAt(0)-'A' ));
        b = new BigDecimal(9);
        po.setInterpersonalSensitivity(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//       抑郁
        a = new BigDecimal((po.getQuestion5().charAt(0)-'A' )+(po.getQuestion14().charAt(0)-'A' )
                +(po.getQuestion15().charAt(0)-'A' )+(po.getQuestion20().charAt(0)-'A' )
                +(po.getQuestion22().charAt(0)-'A' )+(po.getQuestion26().charAt(0)-'A' )
                +(po.getQuestion29().charAt(0)-'A' )+(po.getQuestion30().charAt(0)-'A' )
                +(po.getQuestion31().charAt(0)-'A' )+(po.getQuestion32().charAt(0)-'A' )
                +(po.getQuestion54().charAt(0)-'A' )+(po.getQuestion71().charAt(0)-'A' )
                +(po.getQuestion79().charAt(0)-'A' ));
        b = new BigDecimal(13);
        po.setDepression(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//      焦虑
        a = new BigDecimal((po.getQuestion2().charAt(0)-'A' )+(po.getQuestion17().charAt(0)-'A' )
                +(po.getQuestion23().charAt(0)-'A' )+(po.getQuestion33().charAt(0)-'A' )
                +(po.getQuestion39().charAt(0)-'A' )+(po.getQuestion57().charAt(0)-'A' )
                +(po.getQuestion72().charAt(0)-'A' )+(po.getQuestion78().charAt(0)-'A' )
                +(po.getQuestion80().charAt(0)-'A' )+(po.getQuestion86().charAt(0)-'A' ));
        b = new BigDecimal(10);
        po.setAnxiety(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//        敌对
        a = new BigDecimal((po.getQuestion11().charAt(0)-'A' )+(po.getQuestion24().charAt(0)-'A' )
                +(po.getQuestion63().charAt(0)-'A' )+(po.getQuestion67().charAt(0)-'A' )
                +(po.getQuestion74().charAt(0)-'A' )+(po.getQuestion81().charAt(0)-'A' ));
        b = new BigDecimal(6);
        po.setHostility(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
//      恐怖
        a = new BigDecimal((po.getQuestion13().charAt(0)-'A' )+(po.getQuestion25().charAt(0)-'A' )
                +(po.getQuestion47().charAt(0)-'A' )+(po.getQuestion50().charAt(0)-'A' )
                +(po.getQuestion70().charAt(0)-'A' )+(po.getQuestion75().charAt(0)-'A' )
                +(po.getQuestion82().charAt(0)-'A' ));
        b = new BigDecimal(7);
        po.setPhoticAnxiety(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
        //      偏执
        a = new BigDecimal((po.getQuestion8().charAt(0)-'A' )+(po.getQuestion18().charAt(0)-'A' )
                +(po.getQuestion43().charAt(0)-'A' )+(po.getQuestion68().charAt(0)-'A' )
                +(po.getQuestion76().charAt(0)-'A' )+(po.getQuestion83().charAt(0)-'A' ));
        b = new BigDecimal(6);
        po.setParanoidIdeation(a.divide(b,2,BigDecimal.ROUND_HALF_UP));
        //      精神病性
        a = new BigDecimal((po.getQuestion7().charAt(0)-'A' )+(po.getQuestion16().charAt(0)-'A' )
                +(po.getQuestion35().charAt(0)-'A' )+(po.getQuestion62().charAt(0)-'A' )
                +(po.getQuestion77().charAt(0)-'A' )+(po.getQuestion84().charAt(0)-'A' )
                +(po.getQuestion85().charAt(0)-'A' )+(po.getQuestion87().charAt(0)-'A' )
                +(po.getQuestion88().charAt(0)-'A' )+(po.getQuestion90().charAt(0)-'A' ));
        b = new BigDecimal(10);
        po.setPsychoticism(a.divide(b,2,BigDecimal.ROUND_HALF_UP));

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
