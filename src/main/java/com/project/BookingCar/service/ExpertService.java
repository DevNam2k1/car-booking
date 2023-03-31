package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.ExpertDTO;
import com.project.BookingCar.domain.dto.page.ExpertPageDTO;
import org.springframework.data.domain.Page;

public interface ExpertService {
    Page<ExpertPageDTO> getAllExpert(String name, Integer pageNum, Integer pageSize);

    ExpertPageDTO getExpertById(Long id);
    void updateExpert(Long id, ExpertDTO expertDTO);

    void disableExpert(Long id);
}
