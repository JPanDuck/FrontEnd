package com.example.realrealfinal.dto.auth;

import com.example.realrealfinal.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {
    private User user;
    private Object roleEntity;  //학생, 교수 등 상세정보 담을 필드
}
