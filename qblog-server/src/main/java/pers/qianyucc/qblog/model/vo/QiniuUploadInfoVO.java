package pers.qianyucc.qblog.model.vo;

import lombok.*;

@Data
@Builder
public class QiniuUploadInfoVO {
    private String token;
    private String imageUrl;
    private String uploadUrl;
}
