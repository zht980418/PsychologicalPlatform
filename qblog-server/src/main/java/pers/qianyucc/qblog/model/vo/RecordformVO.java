package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.RecordformPO;

@Data
public class RecordformVO {
    private int consultno;
    private String type;
    private String userid;
    private String doctorname;
    private String date1;
    private String date2;
    private String date3;
    private String consulttype;
    private String mainpoint;
    private String nextexist;
    private String specialattention;
    private String followplan;
    private String sign;
    private String complaint;
    private String impression;


    public static RecordformVO fromRecordformPO(RecordformPO recordformPO){return new RecordformVO.Converter().convertToVO(recordformPO);}
    private static class Converter implements IConverter<RecordformPO, RecordformVO>{
        @Override
        public RecordformVO convertToVO(RecordformPO recordformPO){
            RecordformVO recordformVO = new RecordformVO();
            BeanUtil.copyProperties(recordformPO, recordformVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return recordformVO;
        }
    }

}
