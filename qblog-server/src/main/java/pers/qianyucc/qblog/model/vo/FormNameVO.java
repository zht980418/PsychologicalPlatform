package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.FormNamePO;

@Data
public class FormNameVO {
    private String doctorid;
    private String orderid;
    private String status;
    private String start;
    private String end;
    private String roomid;
    private String doctorname;

    public static FormNameVO fromFormNamePO(FormNamePO formNamePO){return new FormNameVO.Converter().convertToVO(formNamePO);}
    private static class Converter implements IConverter<FormNamePO, FormNameVO>{
        @Override
        public FormNameVO convertToVO(FormNamePO formNamePO){
            final FormNameVO formNameVO = new FormNameVO();
            BeanUtil.copyProperties(formNamePO, formNameVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return formNameVO;
        }
    }
}
