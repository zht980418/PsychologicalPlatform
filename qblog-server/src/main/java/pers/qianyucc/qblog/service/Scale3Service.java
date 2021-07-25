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
import pers.qianyucc.qblog.model.entity.Scale3PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale3VO;
import pers.qianyucc.qblog.model.vo.Scale3VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.UUID;
import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale3Service {
    @Autowired
    private Scale3Mapper scale3Mapper;

    public String insScale3(Scale3DTO scale3DTO) {
        Scale3PO scale3PO =  scale3DTO.toScale3PO(false);
        scale3PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale3Mapper.insert(scale3PO);
        return scale3PO.getId();
    }

    public void deleteScale3(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale3Mapper.deleteByMap(map);
    }

    public void updateScale3(Scale3DTO scale3DTO, String id){
        Scale3PO dbscale3 = scale3Mapper.selectById(id);
        if (Objects.isNull(dbscale3)) {
            throw new BlogException(INVALID_ID);
        }
        Scale3PO scale3PO = scale3DTO.toScale3PO(true);
//        System.out.println(scale3PO.getScale3id());
        scale3PO.setId(id);
        scale3Mapper.updateById(scale3PO);
    }

    public PageVO<Scale3VO> getAllScale3s(int page, int limit, String search, String field, String start, String end) throws ParseException {
        QueryWrapper<Scale3PO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date st = dateFormat.parse(start);
            Date et = dateFormat.parse(end);
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("orderId",search).select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale3PO.class, i-> !"content".equals(i.getColumn()));
        }


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
            re.add(scale3VOS.get(i));
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

    public List<Scale3VO> getAnsbyId(String id) {
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

    public List<String> getResultbyId(String id) {
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
