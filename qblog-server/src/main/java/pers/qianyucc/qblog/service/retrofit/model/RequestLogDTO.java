package pers.qianyucc.qblog.service.retrofit.model;

import lombok.*;

import java.io.*;

/**
 * @author mizzle_rain
 * @date 2020年10月31日22:50:11
 */
@Data
public class RequestLogDTO implements Serializable {
    private static final long serialVersionUID = 680860673782635275L;
    private String ip;
    private String url;
    private String method;
    private String classMethod;
    private String parameters;
    private String userAgent;
    private String result;
    private Long timeCost;
    private String exception;
    private String createdBy;
}
