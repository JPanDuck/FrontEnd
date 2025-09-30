package com.example.realrealfinal.controller.auth;

import com.example.realrealfinal.domain.user.User;
import com.example.realrealfinal.dto.auth.ChangePasswordDTO;
import com.example.realrealfinal.dto.auth.JwtResponseDTO;
import com.example.realrealfinal.dto.auth.LoginRequestDTO;
import com.example.realrealfinal.security.CustomUserDetails;
import com.example.realrealfinal.security.jwt.JwtTokenProvider;
import com.example.realrealfinal.service.log.LogHistoryService;
import com.example.realrealfinal.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 사용자 인증 관련 API
 * */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final LogHistoryService logHistoryService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequestDTO loginRequestDTO,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        //임시 비밀번호 여부 확인
        boolean tempPassword = ((CustomUserDetails) auth.getPrincipal()).getUser().isPasswordTemp();

        //인증 성공 시 토큰 발급
        String accessToken = tokenProvider.generateAccessToken(auth);
        String refreshToken = tokenProvider.generateRefreshToken(auth);

        //접속 기록 저장 로직
        User user = userService.findByUsername(loginRequestDTO.getUsername());  //로그인한 사용자 정보
        String ipAddress = request.getRemoteAddr(); //ip 주소 가져오기
        logHistoryService.saveLoginLog(user.getId(), user.getUsername(), ipAddress);

        //access token 쿠키에 담기
        Cookie accessTokenCookie = new Cookie("ACCESS_TOKEN", accessToken);
        accessTokenCookie.setHttpOnly(true);    // 자바스크립트 접근 방지 (XSS 공격 방어)
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(3600);      //1시간
        response.addCookie(accessTokenCookie);

        //토큰과 임시비번 상태를 클라이언트에게 응답
        return ResponseEntity.ok(
                JwtResponseDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
//                        .tokenType("Bearer")
                        .tempPassword(tempPassword)
                        .build()
        );
    }

    //비밀번호 변경
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Authentication auth){
        String username = auth.getName();
        try{
            userService.changePassword(username,
                    changePasswordDTO.getCurrentPassword(),
                    changePasswordDTO.getNewPassword());
            return ResponseEntity.ok("비밀번호 변경을 완료했습니다.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //로그아웃 - 접속기록관리용
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication auth){
        if(auth != null && auth.isAuthenticated()){
            String username = auth.getName();
            User user = userService.findByUsername(username);
            if(user != null){
                logHistoryService.userLogoutTime(user.getId());
            }
        }
        return ResponseEntity.ok("로그아웃 성공");
    }
}
