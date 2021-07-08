package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("scale3")
public class Scale3PO implements Serializable {
    private static final long serialVersionUID = 6064199411680600904L;
    @TableId(value = "id", type = IdType.INPUT)
    private int id;
    private int uid;
    private String name;
    private String remark;
    private String sex;
    private Long gmtCreate;
    private Long gmtUpdate;
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
}
