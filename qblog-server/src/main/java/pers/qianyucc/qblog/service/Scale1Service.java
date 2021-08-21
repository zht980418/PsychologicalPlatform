package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale1Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale1DTO;
import pers.qianyucc.qblog.model.entity.Scale1PO;
import pers.qianyucc.qblog.model.entity.Scale1PO;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale1VO;
import pers.qianyucc.qblog.model.vo.Scale1VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale1Service {
    @Autowired
    private Scale1Mapper scale1Mapper;

    public void insScale1(Scale1DTO scale1DTO) {
        Scale1PO scale1PO =  scale1DTO.toScale1PO(false);
        scale1Mapper.insert(scale1PO);
    }

    public void deleteScale1(int id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale1Mapper.deleteByMap(map);
    }

    public void updateScale1(Scale1DTO scale1DTO, int id){
        Scale1PO dbscale1 = scale1Mapper.selectById(id);
        if (Objects.isNull(dbscale1)) {
            throw new BlogException(INVALID_ID);
        }
        Scale1PO scale1PO = scale1DTO.toScale1PO(true);
//        System.out.println(scale1PO.getScale1id());
        scale1PO.setId(id);
        scale1Mapper.updateById(scale1PO);
    }

    public PageVO<Scale1VO> getAllScale1s(int page, int limit, String search, String field, String start, String end) throws ParseException {
        QueryWrapper<Scale1PO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
            Date st = dateFormat.parse(start.substring(0,10)+start.substring(11,16));
            Date et = dateFormat.parse(end.substring(0,10)+end.substring(11,16));
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("id",search).select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
        }


        qw.select(Scale1PO.class, i-> !"content".equals(i.getColumn()));
        Page<Scale1PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale1PO> res = scale1Mapper.selectPage(page1, qw);
        List<Scale1VO> scale1VOS = res.getRecords().stream()
                .map(Scale1VO::fromScale1PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale1VOS.size();i++){
            re.add(scale1VOS.get(i));
        }
        PageVO<Scale1VO> pageVO = PageVO.<Scale1VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale1PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale1Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale1VO> getAnsbyId(int id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale1PO> wrapper = new QueryWrapper<>();
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
                        "question46","question47","question48","question49","question50"
                        );
        List<Map<String,Object>> maps = scale1Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }
}
