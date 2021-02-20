package pers.qianyucc.qblog.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormVO {
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
    private String sleep;
    private String relationship;
    private String stress;
    private String mood;
    private String hurt;
    private String suicide;
    private String roomid;
    private String start;
    private String end;

}
