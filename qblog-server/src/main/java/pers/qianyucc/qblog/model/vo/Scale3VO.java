package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.Scale3PO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class Scale3VO {
    private int id;
    private int uid;
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

    public static Scale3VO fromScale3PO(Scale3PO scale3PO){return new Scale3VO.Converter().convertToVO(scale3PO);}
    private static class Converter implements IConverter<Scale3PO, Scale3VO>{
        @Override
        public Scale3VO convertToVO(Scale3PO scale3PO){
            final Scale3VO scale3VO = new Scale3VO();
            BeanUtil.copyProperties(scale3PO, scale3VO, CopyOptions.create().ignoreNullValue().ignoreError());
            scale3VO.setGmtCreate(DateTimeUtils.formatDatetime(scale3PO.getGmtCreate()));
            scale3VO.setGmtUpdate(DateTimeUtils.formatDatetime(scale3PO.getGmtUpdate()));
            return scale3VO;
        }
    }
}
