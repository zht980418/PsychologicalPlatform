package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.DejueMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.DejueDTO;
import pers.qianyucc.qblog.model.entity.DejuePO;
import pers.qianyucc.qblog.model.entity.DejuePO;
import pers.qianyucc.qblog.model.entity.DejuePO;
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class DejueService {
    @Autowired
    private DejueMapper dejueMapper;
    //    增
    public void insDejue(DejueDTO dejueDTO){
        DejuePO dejuePO = dejueDTO.toDejuePO(false);
        dejueMapper.insert(dejuePO);
    }
    //    删
    public void deleteDejue(int id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        dejueMapper.deleteByMap(map);
    }
    //    改
    public void updateDejue(DejueDTO dejueDTO,int id){
        DejuePO dbDejue = dejueMapper.selectById(id);
        if(Objects.isNull(dbDejue)) throw new BlogException(INVALID_ID);
        DejuePO dejuePO = dejueDTO.toDejuePO(true);
        dejuePO.setId(id);
        dejueMapper.updateById(dejuePO);
    }
    //    批量查
    public PageVO<DejueVO> getAllDejue(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<DejuePO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date st = dateFormat.parse(start);
            Date et = dateFormat.parse(end);
            qw.between("gmt_create",st.getTime(),et.getTime());
        }


        if(search.equals("")) qw.select(DejuePO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("title"))
                qw.like("title",search).select(DejuePO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(DejuePO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("tabloid"))
                qw.like("tabloid",search).select(DejuePO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("author"))
                qw.like("author",search).select(DejuePO.class, i-> !"content".equals(i.getColumn()));
        }
        qw.select(DejuePO.class, i-> !"content".equals(i.getColumn()));
        Page<DejuePO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<DejuePO> res = dejueMapper.selectPage(page1, qw);
        List<DejueVO> dejueVOS = res.getRecords().stream()
                .map(DejueVO::fromDejuePO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<dejueVOS.size();i++){
            re.add(dejueVOS.get(i));
            System.out.println(dejueVOS.get(i).getGmtCreate());
        }
        PageVO<DejueVO> pageVO = PageVO.<DejueVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }
    //    id查
    public DejueVO getDejueByid(int id){
        DejuePO dbDejue = dejueMapper.selectById(id);
        if(Objects.isNull(dbDejue)) throw new BlogException(INVALID_ID);
        dbDejue.setViews(dbDejue.getViews()+1);
        dejueMapper.updateById(dbDejue);
        return DejueVO.fromDejuePO(dbDejue);

    }

}
