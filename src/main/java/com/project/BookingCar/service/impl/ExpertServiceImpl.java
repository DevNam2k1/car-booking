package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.ExpertDTO;
import com.project.BookingCar.domain.dto.page.ExpertPageDTO;
import com.project.BookingCar.domain.enums.ChangeStatusEnum;
import com.project.BookingCar.domain.model.Expert;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.ExpertRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl extends BaseService implements ExpertService {
    private final ExpertRepository expertRepository;
    private final CommonMapper mapper;
    @Override
    public Page<ExpertPageDTO> getAllExpert(String name, Integer pageNum, Integer pageSize) {
        int page = pageNum == 0? pageNum: pageNum - 1;
        Pageable pageable = PageRequest.of(page,pageSize);
        List<Expert> experts = null;
        if (Objects.nonNull(name)){
            experts = expertRepository.findAll()
                    .stream()
                    .filter(i -> i.getUsername().equals(name))
                    .collect(Collectors.toList());
        } else {
            experts = expertRepository.findAll();
        }
        List<ExpertPageDTO> expertPageDTOS = mapper.convertToResponseList(experts,ExpertPageDTO.class);
        return new PageImpl<>(expertPageDTOS,pageable,expertPageDTOS.size());
    }

    @Override
    public ExpertPageDTO getExpertById(Long id) {
        Expert expert = expertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Expert is not exist!"));
        return mapper.convertToResponse(expert,ExpertPageDTO.class);
    }

    @Override
    public void updateExpert(Long id, ExpertDTO expertDTO) {
        Expert expert = expertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Expert is not exist!"));
        expert.setUpdateUser(getUsername());
        expert.setAddress(expertDTO.getAddress());
        expert.setPhone(expertDTO.getPhone());
        expertRepository.save(expert);
    }

    @Override
    public void disableExpert(Long id) {
        Expert expert = expertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Expert is not exist!"));
        if (expert.getStatus().equals(ChangeStatusEnum.ACTIVE.getValue())) {
            expert.setStatus(ChangeStatusEnum.DISABLE.getValue());
        } else {
            expert.setStatus(ChangeStatusEnum.ACTIVE.getValue());
        }
        expertRepository.save(expert);
    }
}
