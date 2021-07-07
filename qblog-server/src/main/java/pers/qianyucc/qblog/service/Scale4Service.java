package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale4Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale4DTO;
import pers.qianyucc.qblog.model.entity.Scale4PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale4VO;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale4Service {
    @Autowired
    private Scale4Mapper scale4Mapper;

    public void insScale4(Scale4DTO scale4DTO) {
        Scale4PO scale4PO =  scale4DTO.toScale4PO(false);
        scale4Mapper.insert(scale4PO);
    }

    public void deleteScale4(int id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale4Mapper.deleteByMap(map);
    }

    public void updateScale4(Scale4DTO scale4DTO, int id){
        Scale4PO dbscale4 = scale4Mapper.selectById(id);
        if (Objects.isNull(dbscale4)) {
            throw new BlogException(INVALID_ID);
        }
        Scale4PO scale4PO = scale4DTO.toScale4PO(true);
//        System.out.println(scale4PO.getScale4id());
        scale4PO.setId(id);
        scale4Mapper.updateById(scale4PO);
    }

    public PageVO<Scale4VO> getAllScale4s(int page, int limit, String search, String field, String start, String end){
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
        QueryWrapper<Scale4PO> qw = new QueryWrapper<>();
        qw.select(Scale4PO.class, i-> !"content".equals(i.getColumn()));
        Page<Scale4PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale4PO> res = scale4Mapper.selectPage(page1, qw);
        List<Scale4VO> scale4VOS = res.getRecords().stream()
                .map(Scale4VO::fromScale4PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale4VOS.size();i++){
            String dbCreateTime = scale4VOS.get(i).getGmtCreate();
            String createtime = dbCreateTime.substring(0,4)+dbCreateTime.substring(5,7)+dbCreateTime.substring(8,10)+dbCreateTime.substring(11,13);
            Double CreateTime = Double.valueOf(createtime);
            if(start.isEmpty()||(StartTime<=CreateTime&&CreateTime<=EndTime)){
                System.out.println(field);
                System.out.println(search);
                if(search.equals("")) re.add(scale4VOS.get(i));
                else {
                    if(field.equals("orderId")&& Pattern.matches(".*"+search+".*",scale4VOS.get(i).getId()+""))
                        re.add(scale4VOS.get(i));
                    else if(field.equals("name")&&Pattern.matches(".*"+search+".*",scale4VOS.get(i).getName()))
                        re.add(scale4VOS.get(i));
                    else if(field.equals("uid")&&Pattern.matches(".*"+search+".*",scale4VOS.get(i).getUid()+""))
                        re.add(scale4VOS.get(i));
                    else if(field.equals("remark")&&Pattern.matches(".*"+search+".*",scale4VOS.get(i).getRemark()))
                        re.add(scale4VOS.get(i));
                }
            }else continue;
        }
        PageVO<Scale4VO> pageVO = PageVO.<Scale4VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale4PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale4Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale4VO> getAnsbyId(int id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale4PO> wrapper = new QueryWrapper<>();
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
        List<Map<String,Object>> maps = scale4Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }
}
