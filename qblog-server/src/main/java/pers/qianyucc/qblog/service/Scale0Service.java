package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.*;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale0DTO;
import pers.qianyucc.qblog.model.entity.*;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale0VO;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale0Service {
    @Autowired
    private Scale0Mapper scale0Mapper;
    @Autowired
    private Scale1Mapper scale1Mapper;
    @Autowired
    private Scale2Mapper scale2Mapper;
    @Autowired
    private Scale3Mapper scale3Mapper;
    @Autowired
    private Scale4Mapper scale4Mapper;


    public void insScale0(Scale0DTO scale0DTO) {
        Scale0PO scale0PO =  scale0DTO.toScale0PO(false);
        scale0Mapper.insert(scale0PO);
    }

    public void deleteScale0(int id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale0Mapper.deleteByMap(map);
    }

    public void updateScale0(Scale0DTO scale0DTO, int id){
        Scale0PO dbscale0 = scale0Mapper.selectById(id);
        if (Objects.isNull(dbscale0)) {
            throw new BlogException(INVALID_ID);
        }
        Scale0PO scale0PO = scale0DTO.toScale0PO(true);
//        System.out.println(scale0PO.getScale0id());
        scale0PO.setId(id);
        scale0Mapper.updateById(scale0PO);
    }

    public PageVO<Scale0VO> getAllScale0s(int page, int limit, String search, String field, String start, String end){
//        System.out.println(start);
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
        QueryWrapper<Scale0PO> qw = new QueryWrapper<>();
        qw.select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
        Page<Scale0PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale0PO> res = scale0Mapper.selectPage(page1, qw);
        List<Scale0VO> scale0VOS = res.getRecords().stream()
                .map(Scale0VO::fromScale0PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale0VOS.size();i++){
            String dbCreateTime = scale0VOS.get(i).getGmtCreate();
            String createtime = dbCreateTime.substring(0,4)+dbCreateTime.substring(5,7)+dbCreateTime.substring(8,10)+dbCreateTime.substring(11,13);
            Double CreateTime = Double.valueOf(createtime);
            if(start.isEmpty()||(StartTime<=CreateTime&&CreateTime<=EndTime)){
                System.out.println(field);
                System.out.println(search);
                if(search.equals("")) re.add(scale0VOS.get(i));
                else {
                    if(field.equals("orderId")&& Pattern.matches(".*"+search+".*",scale0VOS.get(i).getId()+""))
                        re.add(scale0VOS.get(i));
                    else if(field.equals("name")&&Pattern.matches(".*"+search+".*",scale0VOS.get(i).getName()))
                        re.add(scale0VOS.get(i));
                    else if(field.equals("uid")&&Pattern.matches(".*"+search+".*",scale0VOS.get(i).getUid()+""))
                        re.add(scale0VOS.get(i));
                    else if(field.equals("remark")&&Pattern.matches(".*"+search+".*",scale0VOS.get(i).getRemark()))
                        re.add(scale0VOS.get(i));
                }
            }else continue;
        }
        PageVO<Scale0VO> pageVO = PageVO.<Scale0VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale0PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale0Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale0VO> getAnsbyId(int id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale0PO> wrapper = new QueryWrapper<>();
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
                        "question51","question52","question53","question54","question55",
                        "question56"
                );
        List<Map<String,Object>> maps = scale0Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

}
