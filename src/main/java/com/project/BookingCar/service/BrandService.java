package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.BrandDTO;
import org.springframework.data.domain.Page;

public interface BrandService {
    Page<BrandDTO> getPagingBrand(String name, Integer pageNo, Integer pageSize);

    void createNewBrand(BrandDTO brandDTO);

    void updateBrand(Long id, BrandDTO brandDTO);

    void deleteBrand(Long id);
}
