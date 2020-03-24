package com.pink.gmall.service;

import com.pink.gmall.bean.PmsBaseAttrInfo;
import com.pink.gmall.bean.PmsBaseAttrValue;
import com.pink.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);


    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);


    List<PmsBaseSaleAttr> baseSaleAttrList();
}
