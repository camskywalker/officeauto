package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.MajorService;
import com.lizijian.officeauto.pojo.Major;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;

    @GetMapping
    public WebApiResult getMajorList(){
        return majorService.getMajorList();
    }

    @PostMapping
    public WebApiResult addMajor(Major major){
        WebApiResult webApiResult = new WebApiResult();
        if (major.getName() == null || major.getName().equals("")){
            webApiResult.isNull();
            webApiResult.setMsg("课程名为空");
        }else if (majorService.getMajorByMajorName(major.getName()) != null){
            webApiResult.isErr();
            webApiResult.setMsg("专业已存在！");
        }else {
            String majorName = major.getName().trim();
            major.setName(majorName);
            webApiResult = majorService.addMajor(major);
        }
        return webApiResult;
    }

    @DeleteMapping("/{majorId}")
    public WebApiResult deleteMajorById(@PathVariable("majorId") Integer id){
        return majorService.deleteMajorById(id);
    }

    @PutMapping
    //前端发送数据时，请求头必须是form-urlencode
    public WebApiResult upDateMajor(Major major){
        major.setName(major.getName().trim());
        return majorService.upDateMajor(major);
    }
}
