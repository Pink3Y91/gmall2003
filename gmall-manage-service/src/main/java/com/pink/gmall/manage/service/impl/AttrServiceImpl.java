package com.pink.gmall.manage.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.pink.gmall.bean.PmsBaseAttrInfo;
import com.pink.gmall.bean.PmsBaseAttrValue;
import com.pink.gmall.bean.PmsBaseSaleAttr;
import com.pink.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.pink.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.pink.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.pink.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo baseAttrInfo:pmsBaseAttrInfos) {
            List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){
            //id为空 保存操作
            //保存属性
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);//insertSelective不插入null


            //保存属性值
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrInfoMapper.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValues) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            }
        }else{
            //id不为空 修改操作
            //修改属性
            Example e = new Example(PmsBaseAttrInfo.class);
            e.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,e);
            //修改属性值
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            //将原有属性值删除
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValue);


            for (PmsBaseAttrValue attrValue : attrValueList) {
                pmsBaseAttrValueMapper.insertSelective(attrValue);
            }
        }


        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {

        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);

        return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {


        return pmsBaseSaleAttrMapper.selectAll();
    }


}
