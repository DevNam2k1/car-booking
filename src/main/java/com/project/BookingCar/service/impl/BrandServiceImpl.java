package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.BrandDTO;
import com.project.BookingCar.domain.model.Brand;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.BrandRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl extends BaseService implements BrandService {
    private final BrandRepository brandRepository;

    private final CommonMapper mapper;

    @Override
    public Page<BrandDTO> getPagingBrand(String name, Integer pageNo, Integer pageSize) {
        int page = pageNo == 0 ? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        List<BrandDTO> list = null;
        if (Objects.nonNull(name)) {
            list = mapper.convertToResponseList(brandRepository.findAll()
                            .stream()
                            .filter(i -> name.equals(i.getName()))
                            .collect(Collectors.toList())
                    , BrandDTO.class);
        } else {
            list = mapper.convertToResponseList(brandRepository.findAll(), BrandDTO.class);
        }
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public void createNewBrand(BrandDTO brandDTO) {
        Brand brand = mapper.convertToEntity(brandDTO, Brand.class);
        brand.setCreatedAt(LocalDateTime.now());
        brand.setCreateUser(getUsername());
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(Long id, BrandDTO brandDTO) {
        if(Objects.nonNull(brandRepository.findByName(brandDTO.getName()))){
            throw new IllegalArgumentException("Brand name have existed!");
        }
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand is not exist"));
        brand.setName(brandDTO.getName());
        brand.setUpdatedAt(LocalDateTime.now());
        brand.setUpdateUser(getUsername());
        brandRepository.save(brand);

    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
