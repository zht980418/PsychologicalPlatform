package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.DejuePO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data
public class DejueVO {
    private int id;
    private String cover;
    private String title;
    private String tabloid;
    private String author;
    private String link;
    private Integer views;
    private String gmtCreate;
    private String gmtUpdate;

    public static DejueVO fromDejuePO(DejuePO dejuePO){return new DejueVO.Converter().convertToVO(dejuePO);}
    private static class Converter implements IConverter<DejuePO, DejueVO>{
        @Override
        public DejueVO convertToVO(DejuePO dejuePO){
            final DejueVO dejueVO = new DejueVO();
            BeanUtil.copyProperties(dejuePO, dejueVO, CopyOptions.create().ignoreNullValue().ignoreError());
            dejueVO.setGmtCreate(DateTimeUtils.formatDatetime(dejuePO.getGmtCreate()));
            dejueVO.setGmtUpdate(DateTimeUtils.formatDatetime(dejuePO.getGmtUpdate()));
            return dejueVO;
        }
    }

}
