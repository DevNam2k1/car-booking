package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.UtilityDTO;
import com.project.BookingCar.domain.model.Utility;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.UtilityRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.UtilityService;
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
public class UtilityServiceImpl extends BaseService implements UtilityService {
    private final UtilityRepository utilityRepository;
    private final CommonMapper mapper;
    @Override
    public Page<UtilityDTO> getPagingOfUtility(String name, Integer pageNum, Integer pageSize) {
        int page = pageNum == 0 ? pageNum : pageNum - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Utility> utilities = null;
        if (Objects.nonNull(name)) {
            utilities = utilityRepository.findAll()
                    .stream()
                    .filter(i -> i.getName().equals(name))
                    .collect(Collectors.toList());
        } else {
            utilities = utilityRepository.findAll();
        }
        return new PageImpl<>(mapper.convertToResponseList(utilities, UtilityDTO.class), pageable, utilities.size());
    }

    @Override
    public void createUtility(UtilityDTO utilityDTO) {
        Utility utility = mapper.convertToEntity(utilityDTO, Utility.class);
        utility.setCreateUser(getUsername());
        utilityRepository.save(utility);
    }

    @Override
    public void updateUtility(Long id, UtilityDTO utilityDTO) {
        Utility utility = utilityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Utility is not exist!"));
        utility.setUpdateUser(getUsername());
        utility.setName(utility.getName());
        utility.setCode(utilityDTO.getCode());
        utilityRepository.save(utility);
    }

    @Override
    public void deleteUtility(Long id) {
        utilityRepository.deleteById(id);
    }
}
