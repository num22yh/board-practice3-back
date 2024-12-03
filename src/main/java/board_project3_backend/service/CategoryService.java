package board_project3_backend.service;

import board_project3_backend.entity.Category;
import board_project3_backend.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> getCategoryList() {
        return categoryMapper.getAllCategories();
    }
}