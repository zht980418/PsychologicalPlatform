package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale7Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale7DTO;
import pers.qianyucc.qblog.model.entity.Scale7PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale7VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale7Service {
    @Autowired
    private Scale7Mapper scale7Mapper;

    public String insScale7(Scale7DTO scale7DTO) {
        Scale7PO scale7PO =  scale7DTO.toScale7PO(false);
        scale7PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale7Mapper.insert(scale7PO);
        return scale7PO.getId();
    }

    public void deleteScale7(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale7Mapper.deleteByMap(map);
    }

    public void updateScale7(Scale7DTO scale7DTO, String id){
        Scale7PO dbscale7 = scale7Mapper.selectById(id);
        if (Objects.isNull(dbscale7)) {
            throw new BlogException(INVALID_ID);
        }
        Scale7PO scale7PO = scale7DTO.toScale7PO(true);
//        System.out.println(scale7PO.getScale7id());
        scale7PO.setId(id);
        scale7Mapper.updateById(scale7PO);
    }

    public PageVO<Scale7VO> getAllScale7s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale7PO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        qw.between("gmt_create",sdf.parse(start),sdf.parse(end));
//        if(!start.isEmpty()){
//            qw.apply("date_format(gmt_create,'%Y-%m-%d %H:%i') >= date_format('" + start + "','%Y-%m-%d %H:%i')")
//                    .apply("date_format(gmt_create,'%Y-%m-%d %H:%i') <= date_format('" + end + "','%Y-%m-%d %H:%i')");
//        }
//        qw.apply("UNIX_TIMESTAMP(gmt_create) >= UNIX_TIMESTAMP('" +start  + "')")
//        .apply("UNIX_TIMESTAMP(gmt_create) <= UNIX_TIMESTAMP('" +end  + "')");

        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
            Date st = dateFormat.parse(start.substring(0,10)+start.substring(11,16));
            Date et = dateFormat.parse(end.substring(0,10)+end.substring(11,16));
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale7PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("id",search).select(Scale7PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale7PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale7PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale7PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale7PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale7PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale7PO> res = scale7Mapper.selectPage(page1, qw);

        List<Scale7VO> scale7VOS = res.getRecords().stream()
                .map(Scale7VO::fromScale7PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale7VOS.size();i++){
            re.add(scale7VOS.get(i));
            System.out.println(scale7VOS.get(i).getGmtCreate());
        }
        PageVO<Scale7VO> pageVO = PageVO.<Scale7VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale7PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale7Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale7VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale7PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10",
                        "question11","question12","question13","question14","question15",
                        "question16","question17","question18","question19","question20",
                        "question21","question22","question23","question24","question25",
                        "question26","question27","question28","question29","question30",
                        "question31","question32","question33","question34","question35",
                        "question36","question37","question38","question39","question40",
                        "question41","question42","question43","question44","question45",
                        "question46","question47","question48","question49","question50",
                        "question51","question52","question53","question54");
        List<Map<String,Object>> maps = scale7Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale7PO dbscale7PO = scale7Mapper.selectById(id);
        int result = dbscale7PO.getResult();
        res.add("本量表是用来评定自我评价的一个重要方面——自信。一般认为自信是一个人对自己能力或技能的感受，是对自己有效地应付各种环境的能力主观评价。" +
                    "PEI作为自我评定量表，其内容涵盖学业表现、体育运动、外表、爱情关系、社会相互作用及同人交谈等方面，还有一些条目是评定总体自信水平相有可能影响自信判断的心境状态。"+
                    "总分范围54—216，分值越高表示自信程度越高。"+
                    "您的得分为"+dbscale7PO.getResult()+"。"
            );
        return  res;
    }
}
