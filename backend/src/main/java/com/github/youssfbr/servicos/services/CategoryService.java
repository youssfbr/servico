package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.CategoryDTO;
import com.github.youssfbr.servicos.model.entities.Category;
import com.github.youssfbr.servicos.model.repositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        return null;
    }

    @Override
    public CategoryDTO findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public CategoryDTO persist(final CategoryDTO dto) {

        Category entity = new Category();
        entity.setName(dto.getName());

        entity = categoryRepository.save(entity);

        return new CategoryDTO(entity);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
