package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale2Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale2DTO;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.entity.Scale2PO;
import pers.qianyucc.qblog.model.vo.Scale2VO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale2VO;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale2Service {
    @Autowired
    private Scale2Mapper scale2Mapper;

    public String insScale2(Scale2DTO scale2DTO) {
        Scale2PO scale2PO =  scale2DTO.toScale2PO(false);
        scale2PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale2Mapper.insert(scale2PO);
        return scale2PO.getId();
    }

    public void deleteScale2(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale2Mapper.deleteByMap(map);
    }

    public void updateScale2(Scale2DTO scale2DTO, String id){
        Scale2PO dbscale2 = scale2Mapper.selectById(id);
        if (Objects.isNull(dbscale2)) {
            throw new BlogException(INVALID_ID);
        }
        Scale2PO scale2PO = scale2DTO.toScale2PO(true);
//        System.out.println(scale2PO.getScale2id());
        scale2PO.setId(id);
        scale2Mapper.updateById(scale2PO);
    }

    public PageVO<Scale2VO> getAllScale2s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale2PO> qw = new QueryWrapper<>();
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

        if(search.equals("")) qw.select(Scale2PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("id",search).select(Scale2PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale2PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale2PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale2PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale2PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale2PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale2PO> res = scale2Mapper.selectPage(page1, qw);

        List<Scale2VO> scale2VOS = res.getRecords().stream()
                .map(Scale2VO::fromScale2PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale2VOS.size();i++){
            re.add(scale2VOS.get(i));
            System.out.println(scale2VOS.get(i).getGmtCreate());
        }
        PageVO<Scale2VO> pageVO = PageVO.<Scale2VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale2PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale2Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale2VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale2PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10",
                        "question11","question12","question13","question14","question15",
                        "question16","question17","question18","question19","question20");
        List<Map<String,Object>> maps = scale2Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale2PO dbscale2PO = scale2Mapper.selectById(id);
        int result = dbscale2PO.getResult();
        if(result<50) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在50分以下; 2-轻度焦虑，标准分50-- 59;"+
                "3-中度焦虑，标准分为60- 69; 4-重度焦虑，标准分在70分以.上。"+
                "您的得分为"+dbscale2PO.getResult()+"。"+
                "您的状态为正常状态"
                );
        else  if(result>=50&&result<60) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在50分以下; 2-轻度焦虑，标准分50-- 59;"+
                "3-中度焦虑，标准分为60- 69; 4-重度焦虑，标准分在70分以.上。"+
                "您的得分为"+dbscale2PO.getResult()+"。"+
                "您的状态为轻度焦虑"
        );
        else if(result>=60&&result<70) res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                "1-正常状态，标准分在50分以下; 2-轻度焦虑，标准分50-- 59;"+
                "3-中度焦虑，标准分为60- 69; 4-重度焦虑，标准分在70分以.上。"+
                "您的得分为"+dbscale2PO.getResult()+"。"+
                "您的状态为中度焦虑"
        );
        else res.add("按照中国常摸结果，SAS标准分的分界值为50分。分数等级说明:" +
                    "1-正常状态，标准分在50分以下; 2-轻度焦虑，标准分50-- 59;"+
                    "3-中度焦虑，标准分为60- 69; 4-重度焦虑，标准分在70分以.上。"+
                    "您的得分为"+dbscale2PO.getResult()+"。"+
                    "您的状态为重度焦虑"
            );
        return  res;
    }
}
