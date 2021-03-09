package pers.qianyucc.qblog.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ScheduleVO {
    private String appid;
    private String doctorid;
    private String doctorname;
    private String start;
    private String end;
    private String daysofweek;
    private String roomid;
}
