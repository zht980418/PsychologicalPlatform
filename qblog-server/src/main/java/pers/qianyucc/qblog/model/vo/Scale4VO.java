package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale4PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

import java.math.BigDecimal;

@Data
public class Scale4VO {
    private String id;
    private String uid;
    private String quizname;
    private String name;
    private String remark;
    private String sex;
    private String gmtCreate;
    private String gmtUpdate;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
    private String question8;
    private String question9;
    private String question10;
    private String question11;
    private String question12;
    private String question13;
    private String question14;
    private String question15;
    private String question16;
    private String question17;
    private String question18;
    private String question19;
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
    private BigDecimal Somatization;
    private BigDecimal Sumaverage;
    private BigDecimal Positive;
    private BigDecimal ObsessiveCompulsive;
    private BigDecimal InterpersonalSensitivity;
    private BigDecimal Depression;
    private BigDecimal Anxiety;
    private BigDecimal Hostility;
    private BigDecimal PhoticAnxiety;
    private BigDecimal ParanoidIdeation;
    private BigDecimal Psychoticism;



    public static Scale4VO fromScale4PO(Scale4PO scale4PO){return new Scale4VO.Converter().convertToVO(scale4PO);}
    private static class Converter implements IConverter<Scale4PO, Scale4VO>{
        @Override
        public Scale4VO convertToVO(Scale4PO scale4PO){
            final Scale4VO scale4VO = new Scale4VO();
            BeanUtil.copyProperties(scale4PO, scale4VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale4VO.setGmtCreate(DateTimeUtils.formatDatetime(scale4PO.getGmtCreate()));
            scale4VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale4PO.getGmtUpdate()));
            return scale4VO;
        }
    }
}
