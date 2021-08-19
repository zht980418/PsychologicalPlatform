package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale8PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale8VO {
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
    private int result;

    public static Scale8VO fromScale8PO(Scale8PO scale8PO){return new Scale8VO.Converter().convertToVO(scale8PO);}
    private static class Converter implements IConverter<Scale8PO, Scale8VO>{
        @Override
        public Scale8VO convertToVO(Scale8PO scale8PO){
            final Scale8VO scale8VO = new Scale8VO();
            BeanUtil.copyProperties(scale8PO, scale8VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale8VO.setGmtCreate(DateTimeUtils.formatDatetime(scale8PO.getGmtCreate()));
            scale8VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale8PO.getGmtUpdate()));
            return scale8VO;
        }
    }
}
