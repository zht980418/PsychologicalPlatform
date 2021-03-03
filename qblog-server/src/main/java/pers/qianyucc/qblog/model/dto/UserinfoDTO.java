package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.UserinfoPO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "用户信息", description = "前端穿过来的用户验证和注册信息")
public class UserinfoDTO {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(notes = "userid", example = "zhangsan123")
    private String userid;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(notes = "password", example = "123456")
    private String password;

    @NotEmpty(message = "权限不能为空")
    @ApiModelProperty(notes = "rolename", example = "管理员")
    private String rolename;

    public UserinfoPO toUserinfoPO(){return new UserinfoDTO.Converter().convertToPO(this);}

    private static class Converter implements IConverter<UserinfoDTO, UserinfoPO>{
        @Override
        public UserinfoPO convertToPO(UserinfoDTO userinfoDTO){
            UserinfoPO userinfoPO=new UserinfoPO();
            BeanUtil.copyProperties(userinfoDTO,userinfoPO);
            return userinfoPO;
        }
    }
}
