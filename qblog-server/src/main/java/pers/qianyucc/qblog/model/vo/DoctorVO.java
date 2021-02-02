package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.DoctorPO;

import java.util.List;


@Data
@Builder
public class DoctorVO {
    private Integer id;
    private String name;
    private String time;
    private String daysOfWeek;
    private String startTime;
    private String endTime;
    private List<Timelist> items;

    @Data
    @Builder
    public static class Timelist {
        private String daysOfWeek;
        private String startTime;
        private String endTime;

    }


}
