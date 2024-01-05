package hu.balogh.restaurantbe.admin.controller;

import hu.balogh.restaurantbe.admin.controller.dto.CategoryDto;
import hu.balogh.restaurantbe.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto created = adminService.createCategory(categoryDto);
        if (created == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(created);
    }

}
