package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale1PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale1VO {
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

    public static Scale1VO fromScale1PO(Scale1PO scale1PO){return new Scale1VO.Converter().convertToVO(scale1PO);}
    private static class Converter implements IConverter<Scale1PO, Scale1VO>{
        @Override
        public Scale1VO convertToVO(Scale1PO scale1PO){
            final Scale1VO scale1VO = new Scale1VO();
            BeanUtil.copyProperties(scale1PO, scale1VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale1VO.setGmtCreate(DateTimeUtils.formatDatetime(scale1PO.getGmtCreate()));
            scale1VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale1PO.getGmtUpdate()));
            return scale1VO;
        }
    }
}
