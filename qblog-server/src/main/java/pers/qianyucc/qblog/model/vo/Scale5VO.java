package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale5PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale5VO {
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

    public static Scale5VO fromScale5PO(Scale5PO scale5PO){return new Scale5VO.Converter().convertToVO(scale5PO);}
    private static class Converter implements IConverter<Scale5PO, Scale5VO>{
        @Override
        public Scale5VO convertToVO(Scale5PO scale5PO){
            final Scale5VO scale5VO = new Scale5VO();
            BeanUtil.copyProperties(scale5PO, scale5VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale5VO.setGmtCreate(DateTimeUtils.formatDatetime(scale5PO.getGmtCreate()));
            scale5VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale5PO.getGmtUpdate()));
            return scale5VO;
        }
    }
}
