package com.example.realrealfinal.mapper.user.staff;

import com.example.realrealfinal.domain.user.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StaffMapper {

    void insertStaff(Staff staff);
    int updateStaff(Staff staff);
    int deleteStaff(@Param("id") Long userId);

    Staff findByUserId(@Param("userId") Long userId);
}
