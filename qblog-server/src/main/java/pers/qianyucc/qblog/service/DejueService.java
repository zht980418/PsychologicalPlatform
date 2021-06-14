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
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

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
    public PageVO<DejueVO> getAllDejue(int page, int limit, String search,String field,String start,String end){
        String starttime = new String();
        String endtime = new String();
        Double StartTime = Double.MIN_VALUE;
        Double EndTime = Double.MAX_VALUE;
        if(!start.isEmpty()&&!end.isEmpty()){
            starttime = start.substring(0,4)+start.substring(5,7)+start.substring(8,10)+start.substring(11,13);
            endtime = end.substring(0,4)+end.substring(5,7)+end.substring(8,10)+end.substring(11,13);
            StartTime = Double.valueOf(starttime);
            EndTime = Double.valueOf(endtime);
        }
//        System.out.println(StartTime);
//        System.out.println(EndTime);
        QueryWrapper<DejuePO> qw = new QueryWrapper<>();
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
            String dbCreateTime = dejueVOS.get(i).getGmtCreate();
            String createtime = dbCreateTime.substring(0,4)+dbCreateTime.substring(5,7)+dbCreateTime.substring(8,10)+dbCreateTime.substring(11,13);
            Double CreateTime = Double.valueOf(createtime);
            if(start.isEmpty()||(StartTime<=CreateTime&&CreateTime<=EndTime)){
                System.out.println(field);
                System.out.println(search);
                if(search.equals("")) re.add(dejueVOS.get(i));
                else {
                    if(field.equals("title")&& Pattern.matches(".*"+search+".*",dejueVOS.get(i).getTitle()))
                        re.add(dejueVOS.get(i));
                    else if(field.equals("tabloid")&&Pattern.matches(".*"+search+".*",dejueVOS.get(i).getTabloid()))
                        re.add(dejueVOS.get(i));
                    else if(field.equals("author")&&Pattern.matches(".*"+search+".*",dejueVOS.get(i).getAuthor()))
                        re.add(dejueVOS.get(i));
                }
            }else continue;
        }
        PageVO<DejueVO> pageVO = PageVO.<DejueVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;



//        ArrayList res = new ArrayList<>();
//        QueryWrapper<DejuePO> wrapper = new QueryWrapper<>();
//        wrapper.select("id","cover","title","tabloid","author","link","views","gmt_create","gmt_update");
//        List<Map<String, Object>> maps = dejueMapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            maps.get(i).put("gmt_create", DateTimeUtils.formatDatetime(Long.valueOf(maps.get(i).get("gmt_create").toString())));
//            maps.get(i).put("gmt_update", DateTimeUtils.formatDatetime(Long.valueOf(maps.get(i).get("gmt_update").toString())));
//            res.add(maps.get(i));
//        }
//        return res;
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
