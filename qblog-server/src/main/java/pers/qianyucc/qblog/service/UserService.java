package pers.qianyucc.qblog.service;

import cn.hutool.core.collection.*;
import cn.hutool.crypto.digest.*;
import cn.hutool.setting.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pers.qianyucc.qblog.exception.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.model.enums.*;
import pers.qianyucc.qblog.utils.*;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

@Service
public class UserService {
    @Autowired
    private Setting setting;

    /**
     * 校验用户名和密码
     *
     * @param userDTO 用户对象
     * @return 校验成功就返回token
     */
    public String checkUsernamePassword(UserDTO userDTO) {
        String username = setting.getStr("username");
        String password = setting.getStr("password");
        String Md5Password = MD5.create().digestHex(userDTO.getPassword());
        if (Objects.equals(username, userDTO.getUsername()) &&
                Objects.equals(password, Md5Password)) {
            return JwtUtils.createToken(username, CollUtil.newArrayList(UserRoleEnum.ADMIN.getValue()));
        } else {
            throw new BlogException(USERNAME_PASSWORD_ERROR);
        }
    }
}
