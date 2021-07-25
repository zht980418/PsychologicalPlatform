package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("FormName")
public class FormNamePO implements Serializable {
    private static final long serialVersionUID = -4619280092523538931L;
    private String doctorid;
    private String orderid;
    private String status;
    private String start;
    private String end;
    private String roomid;
    private String doctorname;
}
