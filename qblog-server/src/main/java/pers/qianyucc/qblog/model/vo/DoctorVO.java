package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.DoctorPO;
import pers.qianyucc.qblog.model.entity.DoctorPO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

import java.util.List;


@Data
public class DoctorVO {
    private String doctorid;
    private String doctorname;
    private String status;
    private String description;
    private String avatar;

    public static DoctorVO fromDoctorPO(DoctorPO doctorPO){return new DoctorVO.Converter().convertToVO(doctorPO);}
    private static class Converter implements IConverter<DoctorPO, DoctorVO>{
        @Override
        public DoctorVO convertToVO(DoctorPO doctorPO){
            final DoctorVO doctorVO = new DoctorVO();
            BeanUtil.copyProperties(doctorPO, doctorVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return doctorVO;
        }
    }
}
