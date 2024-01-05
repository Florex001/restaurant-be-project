package hu.balogh.restaurantbe.admin.service;

import hu.balogh.restaurantbe.admin.controller.dto.CategoryDto;

import java.io.IOException;

public interface AdminService {
    CategoryDto createCategory(CategoryDto categoryDto) throws IOException;
}
