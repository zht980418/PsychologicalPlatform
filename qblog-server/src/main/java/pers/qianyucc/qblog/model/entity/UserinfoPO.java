package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Userinfo")
public class UserinfoPO implements Serializable {

    private static final long serialVersionUID = 130754249864216027L;

    @TableId(value = "userid", type = IdType.INPUT)
    private String userid;
    private String password;
    private String rolename;
}
