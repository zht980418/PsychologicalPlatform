package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale0PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale0VO {
    private int id;
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

    public static Scale0VO fromScale0PO(Scale0PO scale0PO){return new Scale0VO.Converter().convertToVO(scale0PO);}
    private static class Converter implements IConverter<Scale0PO, Scale0VO>{
        @Override
        public Scale0VO convertToVO(Scale0PO scale0PO){
            final Scale0VO scale0VO = new Scale0VO();
            BeanUtil.copyProperties(scale0PO, scale0VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale0VO.setGmtCreate(DateTimeUtils.formatDatetime(scale0PO.getGmtCreate()));
            scale0VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale0PO.getGmtUpdate()));
            return scale0VO;
        }
    }
}
