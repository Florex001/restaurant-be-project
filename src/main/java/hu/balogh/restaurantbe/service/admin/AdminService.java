package hu.balogh.restaurantbe.service.admin;

import hu.balogh.restaurantbe.controller.admin.dto.CategoryDto;

import java.io.IOException;

public interface AdminService {
    CategoryDto createCategory(CategoryDto categoryDto) throws IOException;
}
