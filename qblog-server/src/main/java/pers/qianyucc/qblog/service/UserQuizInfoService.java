package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.UserQuizInfoMapper;
import pers.qianyucc.qblog.model.entity.UserQuizInfoPO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.UserQuizInfoVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserQuizInfoService {
    @Autowired
    private UserQuizInfoMapper userQuizInfoMapper;

    public PageVO<UserQuizInfoVO> getAllQuizbyUid(int page, int limit,String uid){
        QueryWrapper<UserQuizInfoPO> qw = new QueryWrapper<>();
        qw.select(UserQuizInfoPO.class, i-> !"content".equals(i.getColumn()));
        Page<UserQuizInfoPO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<UserQuizInfoPO> res = userQuizInfoMapper.selectPage(page1, qw);
        List<UserQuizInfoVO> userQuizInfoVOS = res.getRecords().stream()
                .map(UserQuizInfoVO::fromUserQuizInfoPO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<userQuizInfoVOS.size();i++){
            if(userQuizInfoVOS.get(i).getUid().equals(uid))
                re.add(userQuizInfoVOS.get(i));
        }
        PageVO<UserQuizInfoVO> pageVO = PageVO.<UserQuizInfoVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }
}
