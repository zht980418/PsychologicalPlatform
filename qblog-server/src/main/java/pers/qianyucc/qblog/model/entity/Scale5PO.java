package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("scale5")
public class Scale5PO implements Serializable {
    private static final long serialVersionUID = -7156278696768090146L;
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String uid;
    private String quizname;
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
    private String question11;
    private String question12;
    private String question13;
    private String question14;
    private String question15;
    private String question16;
    private String question17;
    private String question18;
    private String question19;
    private String question20;
    private int result;
}
