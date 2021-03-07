package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Form")
public class FormPO implements Serializable{

    private static final long serialVersionUID = 4242353588092631058L;
    @TableId(value = "orderid",type = IdType.INPUT)
//    private String id;
    private String orderid;
    private String doctorid;
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
    private String sleep;
    private String relationship;
    private String stress;
    private String mood;
    private String hurt;
    private String suicide;
    private String roomid;
    private String start;
    private String end;
    private String status;
}
