package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("DoctorTimetable")
public class DoctorPO implements Serializable {
    private static final long serialVersionUID = 5498494190578317971L;
    @TableId
    private Integer id;
    private String time;
    private String name;
    private String daysOfWeek;
    private String startTime;
    private String endTime;
}
