package com.college.yi.EcSite.admin.controller;

import java.util.Collections;
import java.util.List;

import jakarta.validation.NoProviderFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.EcSite.admin.dto.ProductEditDto;
import com.college.yi.EcSite.admin.dto.ProductsDto;
import com.college.yi.EcSite.admin.form.ProductEditForm;
import com.college.yi.EcSite.admin.form.ProductRegisterForm;
import com.college.yi.EcSite.admin.service.AdminProductEditService;
import com.college.yi.EcSite.admin.service.AdminProductRegisterService;
import com.college.yi.EcSite.admin.service.AdminProductService;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;
    private final AdminProductRegisterService adminProductRegisterService;
    private final AdminProductEditService adminProductEditService;

    public AdminProductController(AdminProductService adminProductService,
                                 AdminProductRegisterService adminProductRegisterService,
                                 AdminProductEditService adminProductEditService) {
        this.adminProductService = adminProductService;
        this.adminProductRegisterService = adminProductRegisterService;
        this.adminProductEditService = adminProductEditService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProducts() {
        try {
            List<ProductsDto> products = adminProductService.getProductList();
            return ResponseEntity.ok(products);
        } catch (NoProviderFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "システムエラーが発生しました"));
        }
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerProduct(@ModelAttribute ProductRegisterForm form) {
        try {
            adminProductRegisterService.registerProduct(form);
            return ResponseEntity.ok(Collections.singletonMap("message", "商品を登録しました"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "登録に失敗しました"));
        }
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductForEdit(@PathVariable Long id) {
        try {
            ProductEditDto dto = adminProductEditService.getProductForEdit(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "取得に失敗しました"));
        }
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @ModelAttribute ProductEditForm form) {
        try {
            adminProductEditService.updateProduct(id, form);
            return ResponseEntity.ok(Collections.singletonMap("message", "商品を更新しました"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "更新に失敗しました"));
        }
    }
    @PostMapping("/{productId}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStock(
            @PathVariable Long productId,
            @RequestParam Integer stockQuantity) {
        try {
            
            if (stockQuantity == null || stockQuantity < 0) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "在庫数は0以上で入力してください"));
            }

            
            var productOpt = adminProductService.getProduct(productId);
            if (productOpt == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "対象商品が見つかりません"));
            }

            
            adminProductService.updateStock(productId, stockQuantity);

            return ResponseEntity.ok(Collections.singletonMap("message", "在庫数を更新しました"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "在庫更新に失敗しました"));
        }
    }

}
