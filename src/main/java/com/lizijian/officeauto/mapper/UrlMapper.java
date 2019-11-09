package com.lizijian.officeauto.mapper;

import com.lizijian.officeauto.pojo.Url;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UrlMapper {
    public List<Url> getUrl();
}
