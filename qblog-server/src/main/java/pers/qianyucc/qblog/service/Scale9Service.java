package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale9Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale9DTO;
import pers.qianyucc.qblog.model.entity.Scale9PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale9VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale9Service {
    @Autowired
    private Scale9Mapper scale9Mapper;

    public String insScale9(Scale9DTO scale9DTO) {
        Scale9PO scale9PO =  scale9DTO.toScale9PO(false);
        scale9PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale9Mapper.insert(scale9PO);
        return scale9PO.getId();
    }

    public void deleteScale9(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale9Mapper.deleteByMap(map);
    }

    public void updateScale9(Scale9DTO scale9DTO, String id){
        Scale9PO dbscale9 = scale9Mapper.selectById(id);
        if (Objects.isNull(dbscale9)) {
            throw new BlogException(INVALID_ID);
        }
        Scale9PO scale9PO = scale9DTO.toScale9PO(true);
//        System.out.println(scale9PO.getScale9id());
        scale9PO.setId(id);
        scale9Mapper.updateById(scale9PO);
    }

    public PageVO<Scale9VO> getAllScale9s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale9PO> qw = new QueryWrapper<>();
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date st = dateFormat.parse(start);
            Date et = dateFormat.parse(end);
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale9PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("orderId",search).select(Scale9PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale9PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale9PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale9PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale9PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale9PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale9PO> res = scale9Mapper.selectPage(page1, qw);

        List<Scale9VO> scale9VOS = res.getRecords().stream()
                .map(Scale9VO::fromScale9PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale9VOS.size();i++){
            re.add(scale9VOS.get(i));
            System.out.println(scale9VOS.get(i).getGmtCreate());
        }
        PageVO<Scale9VO> pageVO = PageVO.<Scale9VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale9PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale9Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale9VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale9PO> wrapper = new QueryWrapper<>();
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
                        "question46","question47","question48","question49","question50");
        List<Map<String,Object>> maps = scale9Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale9PO dbscale9PO = scale9Mapper.selectById(id);
        int result = dbscale9PO.getResult();
        res.add("  LES总分越高反映个体承受的精神压力越大。95％的正常人一年内的LES总分不超过10分，99％的不超过32分。"+
                    "您的得分为"+dbscale9PO.getResult()+"。"
            );
        return  res;
    }
}
