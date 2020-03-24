package com.pink.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pink.gmall.service.UserService;
import com.pink.gmall.bean.UmsMember;
import com.pink.gmall.bean.UmsMemberReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Reference
    UserService userService;

    /**
     * 获取所有用户信息
     * @return
     */
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers = userService.getAllUser();
        return umsMembers;
    }

    /**
     *根据用户id查找收获地址
     * @return
     */
    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList
                = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddressList;
    }

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }
}
