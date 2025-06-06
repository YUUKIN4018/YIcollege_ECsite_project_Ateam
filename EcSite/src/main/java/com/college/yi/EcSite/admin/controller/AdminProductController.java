package com.college.yi.EcSite.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.EcSite.admin.dto.ProductsDto;
import com.college.yi.EcSite.admin.dto.UserDto;
import com.college.yi.EcSite.admin.service.AdminProductService;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    
    private final AdminProductService adminProuductService;
    
    public AdminProductController(AdminProductService adminProuductService) {
    this.adminProuductService = adminProuductService;
    
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProducts(){
//    public ResponseEntity<?> getProducts(@AuthenticationPrincipal UserDto user){
        try {
            UserDto user = new UserDto();
            user.setRole((short)1);
            List<ProductsDto> products = adminProuductService.getProdutList(user);
            return ResponseEntity.ok(products);
         
        }catch (jakarta.validation.NoProviderFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "システムエラーが発生しました"));
        }
        
    }

}

