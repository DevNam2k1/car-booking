package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.UtilityDTO;
import org.springframework.data.domain.Page;

public interface UtilityService {

    Page<UtilityDTO> getPagingOfUtility(String name, Integer pageNum, Integer pageSize);

    void createUtility(UtilityDTO utilityDTO);

    void updateUtility(Long id, UtilityDTO utilityDTO);

    void deleteUtility(Long id);
}
