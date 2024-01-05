package hu.balogh.restaurantbe.admin.service.impl;

import hu.balogh.restaurantbe.admin.service.AdminService;
import hu.balogh.restaurantbe.admin.controller.dto.CategoryDto;
import hu.balogh.restaurantbe.repository.CategoryRepository;
import hu.balogh.restaurantbe.repository.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());
        categoryRepository.save(category);
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        return dto;
    }
}
