package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.FormNameMapper;
import pers.qianyucc.qblog.model.entity.FormNamePO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.FormNameVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FormNameService {
    @Autowired
    private FormNameMapper formNameMapper;

    public PageVO<FormNameVO> getAllFormbyUid(int page, int limit, String uid){
        QueryWrapper<FormNamePO> qw = new QueryWrapper<>();

        qw.eq("uid",uid).select("orderid","doctorname","status","start","roomid").select(FormNamePO.class, i-> !"content".equals(i.getColumn()));
        Page<FormNamePO> page1 = new Page<>(page,limit);
        page1.setSize(limit);


        Page<FormNamePO> res = formNameMapper.selectPage(page1, qw);
        List<FormNameVO> formNameVOS = res.getRecords().stream()
                .map(FormNameVO::fromFormNamePO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<formNameVOS.size();i++){
//            if(formNameVOS.get(i).getUid().equals(uid))
            re.add(formNameVOS.get(i));
        }
        PageVO<FormNameVO> pageVO = PageVO.<FormNameVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }
}
