package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Course")
public class CoursePO implements Serializable {

    private static final long serialVersionUID = 1840715146773140762L;

    @TableId(value = "courseid", type = IdType.INPUT)
    private String courseid;
    private String coursetitle;
    private String coursedescription;
    private String courselink;
    private String cover;
}
