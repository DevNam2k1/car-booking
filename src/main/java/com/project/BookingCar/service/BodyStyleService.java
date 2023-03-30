package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.BodyStyleDTO;
import com.project.BookingCar.domain.model.BodyStyle;
import org.springframework.data.domain.Page;

public interface BodyStyleService {
    Page<BodyStyleDTO> getPagingBodyStyle(String name, Integer pageNo, Integer pageSize);

    void createNewBodyStyle(BodyStyleDTO bodyStyleDTO);

    void updateBodyStyle(Long id,BodyStyleDTO bodyStyleDTO);

    void deleteBrand(Long id);
}
