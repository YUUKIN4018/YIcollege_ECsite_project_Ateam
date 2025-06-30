package com.college.yi.ecsite.front.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.exception.AuthenticationException;
import com.college.yi.ecsite.front.repository.UserMapper;

class LoginServiceTest {

    private UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        userMapper = mock(UserMapper.class);
        passwordEncoder = new BCryptPasswordEncoder();
        loginService = new LoginService(userMapper, passwordEncoder);
    }

    @Nested
    @DisplayName("正常系")
    class NormalCases {

        @Test
        @DisplayName("正しいメールアドレスとパスワードでログイン成功")
        void loginSuccess() {
            String email = "test@example.com";
            String rawPassword = "password123";
            String hashedPassword = passwordEncoder.encode(rawPassword);

            User mockUser = new User();
            mockUser.setEmail(email);
            mockUser.setPasswordHash(hashedPassword);

            when(userMapper.findByEmail(email)).thenReturn(mockUser);

            User result = loginService.authenticate(email, rawPassword);

            assertThat(result).isNotNull();
            assertThat(result.getEmail()).isEqualTo(email);
        }

        @Test
        @DisplayName("メールアドレスが99文字でログイン成功")
        void email99CharsSuccess() {
            String email = "a".repeat(88) + "@test.com"; // 99文字
            String rawPassword = "password123";
            String hashedPassword = passwordEncoder.encode(rawPassword);

            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(hashedPassword);

            when(userMapper.findByEmail(email)).thenReturn(user);

            User result = loginService.authenticate(email, rawPassword);

            assertThat(result).isNotNull();
            assertThat(result.getEmail()).isEqualTo(email);
        }

        @Test
        @DisplayName("メールアドレスが100文字でログイン成功")
        void email100CharsSuccess() {
            String email = "a".repeat(89) + "@test.com"; // 100文字
            String rawPassword = "password123";
            String hashedPassword = passwordEncoder.encode(rawPassword);

            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(hashedPassword);

            when(userMapper.findByEmail(email)).thenReturn(user);

            User result = loginService.authenticate(email, rawPassword);

            assertThat(result).isNotNull();
            assertThat(result.getEmail()).isEqualTo(email);
        }

        @Test
        @DisplayName("パスワードが8文字で認証成功")
        void password8CharsSuccess() {
            String email = "test@example.com";
            String rawPassword = "asdf1234"; // 8文字
            String hashedPassword = passwordEncoder.encode(rawPassword);

            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(hashedPassword);

            when(userMapper.findByEmail(email)).thenReturn(user);

            User result = loginService.authenticate(email, rawPassword);

            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("パスワードが9文字で認証成功")
        void password9CharsSuccess() {
            String email = "test@example.com";
            String rawPassword = "password9";
            String hashedPassword = passwordEncoder.encode(rawPassword);

            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(hashedPassword);

            when(userMapper.findByEmail(email)).thenReturn(user);

            User result = loginService.authenticate(email, rawPassword);

            assertThat(result).isNotNull();
        }
    }

    @Nested
    @DisplayName("異常系")
    class ErrorCases {

        @Test
        @DisplayName("正しいメールアドレスに誤ったパスワード")
        void wrongPassword() {
            String email = "test@example.com";
            String correctPassword = "password123";
            String wrongPassword = "wrongpassword";

            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(correctPassword));

            when(userMapper.findByEmail(email)).thenReturn(user);

            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(email, wrongPassword));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスまたはパスワードが間違っています");
        }

        @Test
        @DisplayName("誤ったメールアドレスと誤ったパスワード")
        void wrongEmailAndPassword() {
            String email = "notfound@example.com";
            when(userMapper.findByEmail(email)).thenReturn(null);

            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(email, "somepassword"));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスまたはパスワードが間違っています");
        }

        @Test
        @DisplayName("誤ったメールアドレスに正しいパスワード")
        void wrongEmailCorrectPassword() {
            String email = "notfound@example.com";
            when(userMapper.findByEmail(email)).thenReturn(null);

            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(email, "password123"));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスまたはパスワードが間違っています");
        }

        @Test
        @DisplayName("メールアドレス101文字でバリデーションエラー")
        void email101CharsValidationError() {
            String email = "a".repeat(92) + "@test.com"; // 101文字
            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(email, "password123"));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスは100文字以内で入力してください");
        }

        @Test
        @DisplayName("パスワードが7文字でバリデーションエラー")
        void password7CharsValidationError() {
            String email = "test@example.com";
            String shortPassword = "passwrd"; // 7文字

            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(email, shortPassword));

            assertThat(ex.getMessage()).isEqualTo("パスワードは8文字以上で入力してください");
        }

        @Test
        @DisplayName("メールアドレスがnullでバリデーションエラー")
        void emailNull() {
            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate(null, "password123"));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスを入力してください");
        }

        @Test
        @DisplayName("パスワードがnullでバリデーションエラー")
        void passwordNull() {
            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate("test@example.com", null));

            assertThat(ex.getMessage()).isEqualTo("パスワードを入力してください");
        }

        @Test
        @DisplayName("UserMapperがnullを返したとき認証失敗")
        void userMapperReturnsNull() {
            when(userMapper.findByEmail("test@example.com")).thenReturn(null);

            AuthenticationException ex = assertThrows(AuthenticationException.class,
                    () -> loginService.authenticate("test@example.com", "password123"));

            assertThat(ex.getMessage()).isEqualTo("メールアドレスまたはパスワードが間違っています");
        }
    }
}
