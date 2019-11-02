package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.MajorMapper;
import com.lizijian.officeauto.pojo.Major;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;

    public WebApiResult getMajorList(){
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.setData(majorMapper.getMajorList());
        webApiResult.isOk();
        webApiResult.setMsg("查询成功");
        return webApiResult;
    }

    public WebApiResult addMajor(Major major){
        WebApiResult webApiResult = new WebApiResult();
        majorMapper.addMajor(major);
        webApiResult.setData(majorMapper.getMajorByMajorName(major.getName()));
        webApiResult.isOk();
        webApiResult.setMsg("新建成功");
        return webApiResult;
    }
    public Major getMajorByMajorName(String majorName){
        return majorMapper.getMajorByMajorName(majorName);
    }

    public WebApiResult deleteMajorById(Integer id){
        majorMapper.deleteMajor(id);
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("删除成功!");
        return webApiResult;
    }

    public WebApiResult upDateMajor(Major major){
        majorMapper.upDateMajorName(major);
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("更新成功！！！");
        webApiResult.setData( majorMapper.getMajorByMajorName(major.getName()));
        return webApiResult;
    }
}
