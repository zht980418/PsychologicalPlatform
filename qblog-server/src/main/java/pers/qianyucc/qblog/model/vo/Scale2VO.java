package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale2VO {
    private String id;
    private int uid;
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

    public static Scale2VO fromScale2PO(Scale2PO scale2PO){return new Scale2VO.Converter().convertToVO(scale2PO);}
    private static class Converter implements IConverter<Scale2PO, Scale2VO>{
        @Override
        public Scale2VO convertToVO(Scale2PO scale2PO){
            final Scale2VO scale2VO = new Scale2VO();
            BeanUtil.copyProperties(scale2PO, scale2VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale2VO.setGmtCreate(DateTimeUtils.formatDatetime(scale2PO.getGmtCreate()));
            scale2VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale2PO.getGmtUpdate()));
            return scale2VO;
        }
    }
}
