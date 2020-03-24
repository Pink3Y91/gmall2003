package com.pink.gmall.service;

import com.pink.gmall.bean.PmsProductImage;
import com.pink.gmall.bean.PmsProductInfo;
import com.pink.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {

    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
