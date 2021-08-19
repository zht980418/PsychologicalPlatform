package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale8Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale8DTO;
import pers.qianyucc.qblog.model.entity.Scale8PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale8VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale8Service {
    @Autowired
    private Scale8Mapper scale8Mapper;

    public String insScale8(Scale8DTO scale8DTO) {
        Scale8PO scale8PO =  scale8DTO.toScale8PO(false);
        scale8PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale8Mapper.insert(scale8PO);
        return scale8PO.getId();
    }

    public void deleteScale8(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale8Mapper.deleteByMap(map);
    }

    public void updateScale8(Scale8DTO scale8DTO, String id){
        Scale8PO dbscale8 = scale8Mapper.selectById(id);
        if (Objects.isNull(dbscale8)) {
            throw new BlogException(INVALID_ID);
        }
        Scale8PO scale8PO = scale8DTO.toScale8PO(true);
//        System.out.println(scale8PO.getScale8id());
        scale8PO.setId(id);
        scale8Mapper.updateById(scale8PO);
    }

    public PageVO<Scale8VO> getAllScale8s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale8PO> qw = new QueryWrapper<>();
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

        if(search.equals("")) qw.select(Scale8PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("orderId",search).select(Scale8PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale8PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale8PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale8PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale8PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale8PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale8PO> res = scale8Mapper.selectPage(page1, qw);

        List<Scale8VO> scale8VOS = res.getRecords().stream()
                .map(Scale8VO::fromScale8PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale8VOS.size();i++){
            re.add(scale8VOS.get(i));
            System.out.println(scale8VOS.get(i).getGmtCreate());
        }
        PageVO<Scale8VO> pageVO = PageVO.<Scale8VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale8PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale8Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale8VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale8PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10",
                        "question11","question12","question13","question14","question15",
                        "question16","question17","question18","question19","question20");
        List<Map<String,Object>> maps = scale8Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale8PO dbscale8PO = scale8Mapper.selectById(id);
        int result = dbscale8PO.getResult();
        if(result<53) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在53分以下; 2-轻度焦虑，标准分54-- 62;"+
                "3-中度焦虑，标准分为63- 72; 4-重度焦虑，标准分在73分以上。"+
                "您的得分为"+dbscale8PO.getResult()+"。"+
                "您的状态为正常状态"
                );
        else  if(result>=53&&result<62) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在53分以下; 2-轻度焦虑，标准分54-- 62;"+
                "3-中度焦虑，标准分为63- 72; 4-重度焦虑，标准分在73分以上。"+
                "您的得分为"+dbscale8PO.getResult()+"。"+
                "您的状态为轻度焦虑"
        );
        else if(result>=62&&result<72) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在53分以下; 2-轻度焦虑，标准分54-- 62;"+
                "3-中度焦虑，标准分为63- 72; 4-重度焦虑，标准分在73分以上。"+
                "您的得分为"+dbscale8PO.getResult()+"。"+
                "您的状态为中度焦虑"
        );
        else res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                    "1-正常状态，标准分在53分以下; 2-轻度焦虑，标准分54-- 62;"+
                    "3-中度焦虑，标准分为63- 72; 4-重度焦虑，标准分在73分以上。"+
                    "您的得分为"+dbscale8PO.getResult()+"。"+
                    "您的状态为重度焦虑"
            );
        return  res;
    }
}
