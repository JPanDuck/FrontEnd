package com.example.realrealfinal.mapper.user.student;

import com.example.realrealfinal.domain.user.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    void insertStudent(Student student);
    int updateStudent(Student student); //마이페이지, 관리자 페이지
    int deleteStudent(@Param("id") Long userId);

    Student findByUserId(@Param("userId") Long userId);

    //학생 상태값 변경 (재학중, 휴학중, 졸업)
    int updateStatusByUserId(@Param("userId") Long userId, @Param("status") String status);
}
