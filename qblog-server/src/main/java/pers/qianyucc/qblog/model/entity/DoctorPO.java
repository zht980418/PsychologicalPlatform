package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Doctor")
public class DoctorPO implements Serializable {
    private static final long serialVersionUID = 5498494190578317971L;
    @TableId
    private String doctorid;
    private String doctorname;
    private String status;
    private String description;
    private String avatar;
}
