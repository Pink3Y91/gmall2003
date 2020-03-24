package com.pink.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pink.gmall.bean.PmsProductImage;
import com.pink.gmall.bean.PmsProductInfo;
import com.pink.gmall.bean.PmsProductSaleAttr;
import com.pink.gmall.manage.util.PmsUploadUtils;
import com.pink.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {
    @Reference
    SpuService spuService;

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> PmsProductImages = spuService.spuImageList(spuId);
        return PmsProductImages;
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
        //将图片或者音视频上传到分布式存储系统
        //将图片的存储路径返回给页面
        String imgUrl= PmsUploadUtils.uploadImage(multipartFile);
        return imgUrl;
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @RequestMapping("saveSpuValue")
    @ResponseBody
    public String saveSpuValue(PmsProductInfo pmsProductInfo){
        return "success";
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(PmsProductInfo pmsProductInfo){

        spuService.saveSpuInfo(pmsProductInfo);

        return "success";
    }
}
