package com.example.realrealfinal.mapper.dept;

import com.example.realrealfinal.domain.dept.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {
    void insert(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();

    void update(Dept dept);

    void deleteById(Long id);

}
