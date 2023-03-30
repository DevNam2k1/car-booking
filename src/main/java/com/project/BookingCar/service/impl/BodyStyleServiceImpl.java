package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.BodyStyleDTO;
import com.project.BookingCar.domain.model.BodyStyle;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.BodyStyleRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.BodyStyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyStyleServiceImpl extends BaseService implements BodyStyleService {
    private final BodyStyleRepository bodyStyleRepository;

    private final CommonMapper mapper;

    @Override
    public Page<BodyStyleDTO> getPagingBodyStyle(String name, Integer pageNo, Integer pageSize) {
        int page = pageNo == 0 ? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        List<BodyStyleDTO> bodyStyleDTOs = null;
        if (Objects.nonNull(name)) {
             bodyStyleDTOs = mapper.convertToResponseList(bodyStyleRepository.findAll()
                             .stream()
                             .filter(i -> i.getName().equals(name))
                             .collect(Collectors.toList())
                     , BodyStyleDTO.class);
        } else {
             bodyStyleDTOs = mapper.convertToResponseList(bodyStyleRepository.findAll(), BodyStyleDTO.class);
        }
        return new PageImpl<>(bodyStyleDTOs, pageable, bodyStyleDTOs.size());
    }

    @Override
    public void createNewBodyStyle(BodyStyleDTO bodyStyleDTO) {
        BodyStyle bodyStyle = mapper.convertToEntity(bodyStyleDTO, BodyStyle.class);
        bodyStyle.setCreatedAt(LocalDateTime.now());
        bodyStyle.setCreateUser(getUsername());
        bodyStyleRepository.save(bodyStyle);
    }

    @Override
    public void updateBodyStyle(Long id, BodyStyleDTO bodyStyleDTO) {
        if (Objects.nonNull(bodyStyleRepository.findByName(bodyStyleDTO.getName()))) {
            throw new IllegalArgumentException("Body-style have existed!");
        }
        BodyStyle bodyStyle = bodyStyleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Body style not exist"));
        bodyStyle.setUpdatedAt(LocalDateTime.now());
        bodyStyle.setUpdateUser(getUsername());
        bodyStyle.setName(bodyStyleDTO.getName());
        bodyStyleRepository.save(bodyStyle);
    }

    @Override
    public void deleteBrand(Long id) {
        bodyStyleRepository.deleteById(id);
    }
}
