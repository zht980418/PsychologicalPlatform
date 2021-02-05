package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Form")
public class FormPO implements Serializable{

    private static final long serialVersionUID = 4242353588092631058L;
    @TableId
//    private String id;
    private String doctorid;
    private String orderid;
    private String uid;
    private String type;
    private String name;
    private String ordertime;
    private String gender;
    private String birth;
    private String occupation;
    private String phone;
    private String address;
    private String emergency;
    private String emergencyphone;
    private String question;
    private String family;
    private String expectation;
    private String history;
    private String test;
    private String roomid;
    private String start;
    private String end;
}
