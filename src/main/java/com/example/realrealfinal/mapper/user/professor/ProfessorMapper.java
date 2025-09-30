package com.example.realrealfinal.mapper.user.professor;

import com.example.realrealfinal.domain.user.Professor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfessorMapper {

    void insertProfessor(Professor professor);
    int updateProfessor(Professor professor);
    int deleteProfessor(@Param("id") Long userId);

    Professor findByUserId(@Param("userId") Long userId);
}
