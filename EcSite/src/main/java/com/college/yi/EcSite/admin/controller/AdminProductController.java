package com.college.yi.EcSite.admin.controller;

import java.util.Collections;
import java.util.List;

import jakarta.validation.NoProviderFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.EcSite.admin.dto.ProductsDto;
import com.college.yi.EcSite.admin.form.ProductRegisterForm;
import com.college.yi.EcSite.admin.service.AdminProductRegisterService;
import com.college.yi.EcSite.admin.service.AdminProductService;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    
    private final AdminProductService adminProuductService;
    private final AdminProductRegisterService adminProductRegisterService;
    
    public AdminProductController(AdminProductService adminProuductService,
                                  AdminProductRegisterService adminProductRegisterService) {
        
this.adminProuductService = adminProuductService;
this.adminProductRegisterService = adminProductRegisterService;
}
    
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProducts() {
        try {
            List<ProductsDto> products = adminProuductService.getProductList();
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
//            e.printStackTrace();
            return ResponseEntity.badRequest().body(Collections.singletonMap("message",e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "登録に失敗しました"));
        }
    }

}

