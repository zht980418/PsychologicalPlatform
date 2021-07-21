package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.UserQuizInfoPO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class UserQuizInfoVO {
    private String id;
    private String uid;
    private Long gmtCreate;
    private String quizname;

    public static UserQuizInfoVO fromUserQuizInfoPO(UserQuizInfoPO userQuizInfoPO){return new UserQuizInfoVO.Converter().convertToVO(userQuizInfoPO);}
    private static class Converter implements IConverter<UserQuizInfoPO, UserQuizInfoVO>{
        @Override
        public UserQuizInfoVO convertToVO(UserQuizInfoPO userQuizInfoPO){
            final UserQuizInfoVO userQuizInfoVO = new UserQuizInfoVO();
            BeanUtil.copyProperties(userQuizInfoPO, userQuizInfoVO, CopyOptions.create().ignoreNullValue().ignoreError());
            return userQuizInfoVO;
        }
    }
}
