package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale6PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

import java.math.BigDecimal;

@Data
public class Scale6VO {
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
    private int result;


    public static Scale6VO fromScale6PO(Scale6PO scale6PO){return new Scale6VO.Converter().convertToVO(scale6PO);}
    private static class Converter implements IConverter<Scale6PO, Scale6VO>{
        @Override
        public Scale6VO convertToVO(Scale6PO scale6PO){
            final Scale6VO scale6VO = new Scale6VO();
            BeanUtil.copyProperties(scale6PO, scale6VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale6VO.setGmtCreate(DateTimeUtils.formatDatetime(scale6PO.getGmtCreate()));
            scale6VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale6PO.getGmtUpdate()));
            return scale6VO;
        }
    }
}
