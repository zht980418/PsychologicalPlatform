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
import pers.qianyucc.qblog.model.vo.Scale0VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Scale0Mapper scale2Mapper;
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

    public PageVO<Scale0VO> getAllScale0s(int page, int limit, String search, String field, String start, String end) throws ParseException {
        QueryWrapper<Scale0PO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");

        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
            Date st = dateFormat.parse(start.substring(0,10)+start.substring(11,16));
            Date et = dateFormat.parse(end.substring(0,10)+end.substring(11,16));
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("id",search).select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale0PO.class, i-> !"content".equals(i.getColumn()));
        }




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
            re.add(scale0VOS.get(i));
            System.out.println(scale0VOS.get(i).getGmtCreate());
        }
        PageVO<Scale0VO> pageVO = PageVO.<Scale0VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
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
