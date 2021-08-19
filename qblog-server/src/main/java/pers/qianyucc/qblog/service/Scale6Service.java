package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale6Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale6DTO;
import pers.qianyucc.qblog.model.entity.Scale6PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale6VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale6Service {
    @Autowired
    private Scale6Mapper scale6Mapper;

    public String insScale6(Scale6DTO scale6DTO) {
        Scale6PO scale6PO =  scale6DTO.toScale6PO(false);
        scale6PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale6Mapper.insert(scale6PO);
        return scale6PO.getId();
    }

    public void deleteScale6(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale6Mapper.deleteByMap(map);
    }

    public void updateScale6(Scale6DTO scale6DTO, String id){
        Scale6PO dbscale6 = scale6Mapper.selectById(id);
        if (Objects.isNull(dbscale6)) {
            throw new BlogException(INVALID_ID);
        }
        Scale6PO scale6PO = scale6DTO.toScale6PO(true);
//        System.out.println(scale6PO.getScale6id());
        scale6PO.setId(id);
        scale6Mapper.updateById(scale6PO);
    }

    public PageVO<Scale6VO> getAllScale6s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale6PO> qw = new QueryWrapper<>();
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

        if(search.equals("")) qw.select(Scale6PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("orderId",search).select(Scale6PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale6PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale6PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale6PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale6PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale6PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale6PO> res = scale6Mapper.selectPage(page1, qw);

        List<Scale6VO> scale6VOS = res.getRecords().stream()
                .map(Scale6VO::fromScale6PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale6VOS.size();i++){
            re.add(scale6VOS.get(i));
            System.out.println(scale6VOS.get(i).getGmtCreate());
        }
        PageVO<Scale6VO> pageVO = PageVO.<Scale6VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale6PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale6Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale6VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale6PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10",
                        "question11","question12","question13","question14","question15",
                        "question16","question17","question18","question19","question20",
                        "question21","question22","question23","question24","question25",
                        "question26","question27","question28");
        List<Map<String,Object>> maps = scale6Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale6PO dbscale6PO = scale6Mapper.selectById(id);
        int result = dbscale6PO.getResult();
        if(result<15) res.add(
                "您的得分为"+dbscale6PO.getResult()+"。"+
                "您的状态为正常状态"
                );
        else res.add(
                    "您的得分为"+dbscale6PO.getResult()+"。"+
                    "您对网络的依赖已达成瘾程度"
            );
        return  res;
    }
}
