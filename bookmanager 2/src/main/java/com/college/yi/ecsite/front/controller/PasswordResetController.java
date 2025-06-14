package com.college.yi.ecsite.front.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.yi.ecsite.front.exception.UserNotFoundException;
import com.college.yi.ecsite.front.form.PasswordResetForm;
import com.college.yi.ecsite.front.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @GetMapping("/password-reset")
    public String showPasswordResetForm(@ModelAttribute("passwordResetForm") PasswordResetForm passwordResetForm) {
        // formオブジェクトをModelにセットしなくても@ModelAttributeで初期化されるため省略可
        return "front/password_reset";
    }

    @PostMapping("/password-reset")
    public String processPasswordReset(
            @Valid @ModelAttribute PasswordResetForm passwordResetForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "front/password_reset";
        }

        try {
            passwordResetService.resetPassword(
                    passwordResetForm.getEmail(),
                    passwordResetForm.getNewPassword(),
                    passwordResetForm.getConfirmPassword()
            );

            redirectAttributes.addFlashAttribute("successMessage", "パスワードを再設定しました。ログインしてください。");
            return "redirect:/login";

        } catch (UserNotFoundException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/password-reset";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "システムエラーが発生しました。時間をおいて再度お試しください。");
            return "redirect:/password-reset";
        }
    }
}
