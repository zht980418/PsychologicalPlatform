package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("UserQuizInfo")
public class UserQuizInfoPO implements Serializable {
    private static final long serialVersionUID = -7467345817192422877L;
    private String id;
    private String uid;
    private Long gmtCreate;
    private String quizname;
}
