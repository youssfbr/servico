package com.github.youssfbr.servicos.services;

import com.github.youssfbr.servicos.dto.BrandDTO;

import java.util.List;

public interface IBrandService {

    List<BrandDTO> findAll();
    BrandDTO findById(Long id);
    BrandDTO persist(BrandDTO dto);
    BrandDTO update(Long id, BrandDTO dto);
    void delete(Long id);

}
