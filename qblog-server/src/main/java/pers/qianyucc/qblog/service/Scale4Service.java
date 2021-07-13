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

import java.math.BigDecimal;
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
                        "question56","question57","question58","question59","question60",
                        "question61","question62","question63","question64","question65",
                        "question66","question67","question68","question69","question70",
                        "question71","question72","question73","question74","question75",
                        "question76","question77","question78","question79","question80",
                        "question81","question82","question83","question84","question85",
                        "question86","question87","question88","question89","question90"
                );
        List<Map<String,Object>> maps = scale4Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }
    public List<String> getResult(int id){
        ArrayList res = new ArrayList<>();
        Scale4PO dbscale4 = scale4Mapper.selectById(id);
//      总体情况
        res.add("总体情况");
        int mark1 = dbscale4.getSumaverage().compareTo(new BigDecimal(1.44));
        int mark2 = dbscale4.getSumaverage().compareTo(new BigDecimal(2.30));
        if(mark1==0||mark1==-1) res.add("总体情况反映的是您心理健康的总体水平。测试结果显示，你的心理健康总体状况很好。心态比较积极，能感受到生活的意义，更多地体会到的是那些积极的情绪体验，如开心、幸福、放松等。需要注意的是，虽然你的心理健康总体情况很好，但不意味着在所有方面都很好。" +
                "建议： " +
                "尝试以更积极地态度去面对生活和工作，增强自己的成就动机； " +
                "学习一些情绪调节的技术，特别是让你的情绪能比较积极方法，如看一些励志类电影什么的。 " +
                "处理人际关系的时候，多注意一些细节，以免无心伤害到别人。 ");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("总体情况反映的是您心理健康的总体水平。测试结果表明你是心理亚健康的边缘人群，你心理健康的所有方面或者是某些维度出现了一些问题。你可以检查具体的结果分析，看看是哪些方面敲响了心理亚健康的警钟。" +
                "建议： " +
                "你要认真对待身上已经出现的一些心理问题。你需要选择合适的生活目标，不要因为力不能及而长期焦虑和不快。在感到不快时，不要郁郁寡欢，自我封闭，应主动参与社会活动，热情坦诚与人交往，消除孤独感和自卑感，获得安全感和自信心。同时，你要多参加体育运动，使自己压抑的情绪有一个发泄的出口.记得寻求亲人和朋友的帮助。 ");
        else res.add("总体情况反映的是您心理健康的总体水平。测试结果表明你已是心理亚健康人群，压力或者一些情绪的问题已经困扰着你，而且这些心理问题严重影响了你的工作、学习和生活，甚至给你的家庭成员带来了比较大的困扰。或许你已意识到这些问题并试图解决，但由于你缺乏相应的知识和方法，你经常感到手足无措。" +
                    "建议 ： " +
                    "首先你要正视你的问题，你可以尝试在家人和朋友的帮助下寻找自己不适的原因，同时要善于积极主动的调整自己的生活方式， 使个人与社会协调一致.同时切记要善待自己，爱护自己，不要妄自菲薄.当然，在你觉得自己不能完全解决你的问题的时候，你应该尽快地到专业的心理机构中去寻求的专业的心理帮助。 ");
//      阳性症状
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(2.60));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(3.98));
        res.add("阳性症状");
        if(mark1==0||mark1==-1) res.add("阳性症状是指你具体心理问题症状的严重程度。你的阳性症状程度很轻微。说明你在某些方面出现了一些心理问题，但是程度很轻微。你可能对此有所察觉，但并没有对你的生活和工作造成太大影响。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("阳性症状是指你具体心理问题症状的严重程度。你的阳性症状程度中等，说明你出现的心理问题比较严重，并且对你的生活和工作造成一定程度的影响。你要认真地应对这个问题了。");
        else res.add("阳性症状是指你具体心理问题症状的严重程度。你的阳性症状程度相当严重，这说明你在某些方面（那些得分比较高的方面）已经出现心理问题，而且程度相当严重。这个问题对你的生活和工作带来了许多的困扰。");
//      躯体化
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.37));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.33));
        res.add("躯体化");
        if(mark1==0||mark1==-1) res.add("躯体化是指你最近的身体状态，这也是心理健康的重要标志之一。测试结果表明你没有明显的身体不适感，身体状态比较好，精力也比较充沛。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("躯体化是指你最近的身体状态，这也是心理健康的重要标志之一。你的身体已经有一定程度的不适感，这说明你的心理问题已经开始反映到你的身体上了。甚至可能已经开始影响到你的生活和工作。你要注意做一些自我的调节和改变了。");
        else res.add("躯体化是指你最近的身体状态，这也是心理健康的重要标志之一。你的身体有严重的不适感，这说明心理问题已经让你的身体也开始出现严重的问题，建议你进行身体检查，同时寻求专业的心理咨询师的帮助。");
//      强迫症
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.62));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.78));
        res.add("躯体化");
        if(mark1==0||mark1==-1) res.add("强迫症状是比较常见的心理问题，主要是指头脑中出现一些无法控制的思想，或者是对某些事情过分担心。例如总是担心门窗是不是锁好，事情是不是做好等。测试结果表明，你没有强迫症状。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("强迫症状是比较常见的心理问题，主要是指头脑中出现一些无法控制的思想，或者是对某些事情过分担心。例如总是担心门窗是不是锁好，事情是不是做好等。测试结果显示你头脑中已经开始反复出现一些无法自制的想法了，而且行为上也出现了一些机械性反复动作，自信心和记忆有些下降，但是在程度上与大多数人相同，属于可接受的范围，但是仍不能大意。");
        else res.add("强迫症状是比较常见的心理问题，主要是指头脑中出现一些无法控制的思想，或者是对某些事情过分担心。例如总是担心门窗是不是锁好，事情是不是做好等。测试结果显示你大脑时常出现无法控制的想法，甚至在行为上也出现了无法自制的反复的行为动作，这对生活造成严重的影响，建议寻求专业人士的帮助或接受专门的治疗。");
//      人际关系敏感度
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.65));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.77));
        res.add("人际关系敏感度");
        if(mark1==0||mark1==-1) res.add("人际关系的敏感程度是心理健康的重要指标。测试结果表明你不会太在意和担心你的人际关系。自信心强，与人相处积极主动，喜欢与人交往和互动，并在交往中处于比较积极主动的地位，与人交往的心态和技巧也比较好，拥有比较良好的人际关系。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("人际关系的敏感程度是心理健康的重要指标。测试结果表明你偶尔会很在意和担心你的人际关系。你虽然对自己有一定自信心，能与人和谐相处，能正确看待自己，但有时会有不如人的感受。交往中并不是很主动。如果你再增强一些自信，并主动地和人交往，你的人际关系会越来越好。");
        else res.add("人际关系的敏感程度是心理健康的重要指标。测试结果表明你太在意和担心你的人际关系。比较缺乏自信心，容易对人际交往中一些其实是无关紧要的事情担心，容易懊恼和感伤。与人相处的时候可能会存在一些障碍，因而你可能存在较严重的人际关系危机。");
//       抑郁
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.50));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.68));
        res.add("抑郁");
        if(mark1==0||mark1==-1) res.add("你基本上没有抑郁症状。你乐观开朗，有开阔的心境和积极向上的生活态度。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("你的情绪有轻微的抑郁，是抑郁症的边缘人群。心态比较现实，心境随环境、事情的不同而变化，有时候会比较悲观。");
        else res.add("你的抑郁情绪比较严重。你凡事倾向于往坏的方面想，生活比较的单调无趣，你的身体的健康也可能受到了严重影响。建议你高度重视这个问题。");
//      焦虑
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.39));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.25));
        res.add("焦虑");
        if(mark1==0||mark1==-1) res.add("你心情比较放松，没有焦虑症倾向。你心境极其平和，知足常乐，有时可能会过分疏懒，对什么都无所谓。总体上来说是一个不会或者说是比较少感到焦虑的那种人，你要注意一下对事情太过无所谓会影响进步的脚步。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("你有轻微的焦虑症倾向。你心境时好时坏，处事顺利时心情愉快，遇到挫折你会烦恼不安。你有时会感到比较焦虑，这种焦虑带你前进的动力，当然有时你也会因为太过焦虑不安而使生活有很多不便。不过你最后还是能通过自己的能力克服过分的焦虑，并最终和谐发展自己。");
        else res.add("你的焦虑情绪比较严重。非常缺乏耐心，容易过度兴奋，时常感觉疲劳，烦躁不安。焦虑情绪给你的生活和工作带来了许多的不便。建议你寻求专业的心理援助，使自己的焦虑转化会动力.");
//      敌对
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.48));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.60));
        res.add("敌对");
        if(mark1==0||mark1==-1) res.add("敌对是指你对他人友好和信任的程度。测试结果显示你心态平和、耐心、与人为善。你很少和他人发生冲突，就算在关乎到你的利益的事情上，只要不是太过分，你都能以友善的态度化解矛盾，大体上讲，你和周围的人相处的很和谐，是一个受到大家广泛欢迎的人。 ");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("敌对是指你对他人友好和信任程度。测试结果显示你基本上是一个有耐心，能接受和理解与自己不同观点的人。对他人比较信任，不会轻易与人发生冲突，但是有时候会难以控制自己的情绪，一旦某人得罪了你，你就会把他打入地狱。");
        else res.add("敌对是指你对他人友好和信任程度。测试结果显示你脾气暴躁，融合力比较差，常因情绪失控而与他人发生冲突，甚至在一些情况下会出现一些非理智行为。你在适当的时候要注意控制自己的情绪避免之后自己会后悔，如果必要的话参加一些愤怒情绪控制的训练。");
//      恐怖
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.23));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.04));
        res.add("恐怖");
        if(mark1==0||mark1==-1) res.add("恐怖症是指对一些很常见和普通的事物产生不必要的惧怕感，例如害怕空旷的街道、广场等，恐怖症是心理不健康的一个重要指标。测试结果表明你没有恐惧症状，是一个胆子比较大的人，即便是别人害怕的东西你也不怕。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("恐怖症是指对一些很常见和普通的事物产生不必要的惧怕感，例如害怕空旷的街道、广场等，恐怖症是心理问题出现的一个重要指标。测试结果显示在这个方面很正常，尽管对于常人都很害怕的东西你也没有特殊的免疫力，但是也不会害怕那种其实很安全、很细微的事物。");
        else res.add("恐怖症是指对一些很常见和普通的事物产生不必要的惧怕感，例如害怕空旷的街道、广场等，恐怖症是心理问题出现的一个重要指标。测试结果你有严重的恐怖倾向，对一些事物的恐惧感超过了普通人的范围，例如小猫和小狗之类。建议你寻求专业的心理治疗。");

        //      偏执
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.43));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.57));
        res.add("偏执");
        if(mark1==0||mark1==-1) res.add("偏执是指你喜欢钻牛角尖的程度，如果思维过于极端，经常走入死胡同，那是心理不健康的状态。测试结果显示你思维开放，灵活，包容性强，待人接物温和，不喜欢钻牛角尖，对人有一种基本的信任感，相信大家会公平公正友好的对待你，不过你也可能会轻信他人导致上当受骗。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("偏执是指你喜欢钻牛角尖的程度，如果思维过于极端，经常走入死胡同，那是心理不健康的状态。你思想比较开放，对人和对事都比较信任，不过也不会盲目轻信他人，能比较客观公正地看待事物，具有自己的观点，不会随便附和他人的观点。");
        else res.add("偏执是指你喜欢钻牛角尖的程度，如果思维过于极端，经常走入死胡同，那是心理不健康的状态。测试结果显示你思想比较偏激和固执，处理事情时主观性较强，对他人非常不信任，经常是按自己的个人意志行事，别人很难劝说和说服你。");

        //      精神病性
        mark1 = dbscale4.getSomatization().compareTo(new BigDecimal(1.29));
        mark2 = dbscale4.getSomatization().compareTo(new BigDecimal(2.13));
        res.add("精神病性");
        if(mark1==0||mark1==-1) res.add("精神病性是指你思维的健康性。测试结果显示你精神健康，思维流畅，工作效率高，生活满意度很高，人际关系较好。在你的身上没有精神症状和较严重的心理问题。");
        else if(mark1==1&&(mark2==0||mark2==1)) res.add("精神病性是指你思维的健康性。测试结果显示你有时会出现一些非自主的精神症状，不过这些症状暂时还没有影响到你的正常生活，你只要自己稍加留意，找一些适当的方法对自己的生活进行一些自我调节，你很快又会恢复充满活力，精神饱满的状态。");
        else res.add("你有严重的思维失控感，思维不能自主，经常出现一些杞人忧天的想法，有时候伴随着一些不需要的行为和动作，可能你也意识到这些想法和动作没有必要，但是不能控制，这些情况对你的生活造成了严重的影响。");




        return  res;
    }
}
