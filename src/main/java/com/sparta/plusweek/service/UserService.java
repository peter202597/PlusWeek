package com.sparta.plusweek.service;

import com.sparta.plusweek.dto.LoginRequestDto;
import com.sparta.plusweek.dto.UserSignUpRequestDto;
import com.sparta.plusweek.entity.User;
import com.sparta.plusweek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String signup(UserSignUpRequestDto userSignupRequestDTO) {
        String nickname = userSignupRequestDTO.getNickname();
        String password = userSignupRequestDTO.getPassword();
        String confirmPassword = userSignupRequestDTO.getConfirmPassword();
        // 닉네임과 비밀번호가 일치하는지 확인
        if (nickname.equals(password)) {
            return "닉네임과 비밀번호는 동일하게 설정할 수 없습니다.";
        }
        // 비밀번호 확인과 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }
        // 데이터베이스에서 닉네임 중복여부 확인
        if (userRepository.existsById(nickname)) {
            return "중복된 닉네임입니다.";
        }
        // 모든 조건을 통과한 경우, 회원가입 처리
        User newUser = new User(nickname, password);
        userRepository.save(newUser);
        return "회원가입이 성공적으로 완료되었습니다.";
    }

        public String login(LoginRequestDto loginRequestDto) {
            String username = loginRequestDto.getNickname();
            String password = loginRequestDto.getPassword();
            //db에 이름 여부 파악
            User user = userRepository.findByUserNickname(username).orElseThrow(() -> new IllegalArgumentException("해당이름이 없습니다."));
            //비밀번호 일치
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
            return username;
        }
}
