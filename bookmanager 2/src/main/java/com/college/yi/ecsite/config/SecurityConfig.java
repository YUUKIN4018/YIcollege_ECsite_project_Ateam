package com.college.yi.ecsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 認可の設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/css/**").permitAll() // ログインページとCSSは誰でもアクセス可能
                .anyRequest().authenticated() // その他は認証が必要
            )
            .formLogin(form -> form
                .loginPage("/login") // カスタムログインページ
                .defaultSuccessUrl("/mypage", true) // ログイン成功後の遷移先
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login") // ログアウト後の遷移先
            );

        return http.build();
    }

    // 開発用のユーザーをメモリ上に定義
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder().encode("password")) // BCryptでハッシュ化
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    // パスワードエンコーダの定義（全体で使える）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
