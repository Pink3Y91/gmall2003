package com.pink.gmall.manage.mapper;

import com.pink.gmall.bean.PmsBaseAttrInfo;
import com.pink.gmall.bean.PmsBaseAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsBaseAttrInfoMapper extends Mapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrValue> getAttrValueList();
}
