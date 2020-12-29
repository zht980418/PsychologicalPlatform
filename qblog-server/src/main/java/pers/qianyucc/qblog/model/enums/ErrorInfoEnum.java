package pers.qianyucc.qblog.model.enums;

/**
 * @author lijing
 * @e-mail 1413979079@qq.com
 * @date 2020-06-28 23:09
 * @description 错误信息以及错误码
 */
public enum ErrorInfoEnum implements IErrorInfo {
    SUCCESS(0, "操作成功"),
    TOKEN_EXPIRED(4001, "您的token已过期"),
    NOT_LOGIN(4002, "请先登录再进行访问"),
    RESOURCES_NOT_FOUND(4003, "找不到相应资源"),
    TOKEN_INVALID(4004, "无效的token"),
    NO_AUTHORITY(4005, "对不起，您没有权限访问该接口"),
    INCOMPLETE_PARAMETERS(4006, "请求字段不完整"),
    UN_INIT(4007, "博客尚未初始化"),
    INVALID_ID(4008, "你的id不合法"),
    USERNAME_PASSWORD_ERROR(4009, "用户名或密码错误"),
    UNKNOWN_ERROR(5000, "出现未知错误"),
    FILE_UPLOAD_ERROR(5001, "图片上传失败"),
    FILE_DOWNLOAD_ERROR(5002, "图片下载失败");

    private int code;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    ErrorInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }
}
