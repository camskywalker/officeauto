package com.lizijian.officeauto.mapper;

import com.lizijian.officeauto.pojo.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper {
    public List<Major> getMajorList();
    public void addMajor(Major major);
    public void upDateMajorName(Major major);
    public void deleteMajor(Integer MajorId);
    public Major getMajorByMajorName(String majorName);
}
