package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Schedule")
public class SchedulePO implements Serializable {

    private static final long serialVersionUID = -8723758253388327375L;
    @TableId(value = "appid", type = IdType.INPUT)
    private String appid;
    private String doctorid;
    private String doctorname;
    private String start;
    private String end;
    private String daysofweek;
}
