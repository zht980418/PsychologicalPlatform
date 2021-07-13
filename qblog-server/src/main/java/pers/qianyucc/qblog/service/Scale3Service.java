package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale3Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale3DTO;
import pers.qianyucc.qblog.model.entity.Scale3PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale3VO;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale3Service {
    @Autowired
    private Scale3Mapper scale3Mapper;

    public void insScale3(Scale3DTO scale3DTO) {
        Scale3PO scale3PO =  scale3DTO.toScale3PO(false);
        scale3Mapper.insert(scale3PO);
    }

    public void deleteScale3(int id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale3Mapper.deleteByMap(map);
    }

    public void updateScale3(Scale3DTO scale3DTO, int id){
        Scale3PO dbscale3 = scale3Mapper.selectById(id);
        if (Objects.isNull(dbscale3)) {
            throw new BlogException(INVALID_ID);
        }
        Scale3PO scale3PO = scale3DTO.toScale3PO(true);
//        System.out.println(scale3PO.getScale3id());
        scale3PO.setId(id);
        scale3Mapper.updateById(scale3PO);
    }

    public PageVO<Scale3VO> getAllScale3s(int page, int limit, String search, String field, String start, String end){
        System.out.println(start);
        String starttime = new String();
        String endtime = new String();
        Double StartTime = Double.MIN_VALUE;
        Double EndTime = Double.MAX_VALUE;
        if(!start.isEmpty()&&!end.isEmpty()){
            starttime = start.substring(0,4)+start.substring(5,7)+start.substring(8,10)+start.substring(11,13);
            System.out.println(starttime);
            endtime = end.substring(0,4)+end.substring(5,7)+end.substring(8,10)+end.substring(11,13);
            StartTime = Double.valueOf(starttime);
            EndTime = Double.valueOf(endtime);
        }
        QueryWrapper<Scale3PO> qw = new QueryWrapper<>();
        qw.select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
        Page<Scale3PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale3PO> res = scale3Mapper.selectPage(page1, qw);
        List<Scale3VO> scale3VOS = res.getRecords().stream()
                .map(Scale3VO::fromScale3PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale3VOS.size();i++){
            String dbCreateTime = scale3VOS.get(i).getGmtCreate();
            String createtime = dbCreateTime.substring(0,4)+dbCreateTime.substring(5,7)+dbCreateTime.substring(8,10)+dbCreateTime.substring(11,13);
            Double CreateTime = Double.valueOf(createtime);
            if(start.isEmpty()||(StartTime<=CreateTime&&CreateTime<=EndTime)){
                System.out.println(field);
                System.out.println(search);
                if(search.equals("")) re.add(scale3VOS.get(i));
                else {
                    if(field.equals("orderId")&& Pattern.matches(".*"+search+".*",scale3VOS.get(i).getId()+""))
                        re.add(scale3VOS.get(i));
                    else if(field.equals("name")&&Pattern.matches(".*"+search+".*",scale3VOS.get(i).getName()))
                        re.add(scale3VOS.get(i));
                    else if(field.equals("uid")&&Pattern.matches(".*"+search+".*",scale3VOS.get(i).getUid()+""))
                        re.add(scale3VOS.get(i));
                    else if(field.equals("remark")&&Pattern.matches(".*"+search+".*",scale3VOS.get(i).getRemark()))
                        re.add(scale3VOS.get(i));
                }
            }else continue;
        }
        PageVO<Scale3VO> pageVO = PageVO.<Scale3VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale3PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale3Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale3VO> getAnsbyId(int id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale3PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10"
                );
        List<Map<String,Object>> maps = scale3Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResultbyId(int id) {
        List<String> res = new ArrayList<>();
        Scale3PO dbscale3PO = scale3Mapper.selectById(id);
        res.add("您的客观支持分为"+dbscale3PO.getObjective()+"分");
        res.add("您的主观支持分为"+dbscale3PO.getSubjective()+"分");
        res.add("您的对支持的利用度的得分为"+dbscale3PO.getSupport()+"分");
        res.add("您的总分为"+dbscale3PO.getSum()+"分");
        if(dbscale3PO.getSum()<20) res.add("您的社会支持较少");
        else if(dbscale3PO.getSum()>=20&&dbscale3PO.getSum()<30) res.add("您具有一般的社会支持");
        else res.add("您具有较为满意的社会支持度");
        return  res;
    }
}
