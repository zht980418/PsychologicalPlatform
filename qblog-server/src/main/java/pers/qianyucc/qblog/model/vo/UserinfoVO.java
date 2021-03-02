package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.UserinfoPO;

@Data
public class UserinfoVO {
    private String userid;
    private String password;
    private String rolename;


    public static UserinfoVO fromUserinfoPO(UserinfoPO userinfoPO){return new Converter().convertToVO(userinfoPO);}
    private static class Converter implements IConverter<UserinfoPO, UserinfoVO>{
        @Override
        public UserinfoVO convertToVO(UserinfoPO userinfoPO){
            final UserinfoVO userinfoVO = new UserinfoVO();
            BeanUtil.copyProperties(userinfoPO, userinfoVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return userinfoVO;
        }
    }
}
