package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.FuelDTO;
import com.project.BookingCar.domain.model.Fuel;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.FuelRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
public class FuelServiceImpl extends BaseService implements FuelService {

    private final FuelRepository fuelRepository;

    private final CommonMapper mapper;

    @Override
    public Page<FuelDTO> getPagingFuel(String name,Integer pageSize,Integer pageNo) {
        int page = pageNo == 0 ? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        List<FuelDTO> fuelDTOs = null;
        if (!Objects.nonNull(name)) {
            fuelDTOs = mapper.convertToResponseList(fuelRepository.findAll(), FuelDTO.class);
        } else {
            fuelDTOs = mapper.convertToResponseList(fuelRepository.findAll()
                    .stream()
                            .filter(i -> i.getName().equals(name))
                    .collect(Collectors.toList()), FuelDTO.class);
        }
        return new PageImpl<>(fuelDTOs, pageable, fuelDTOs.size());
    }

    @Override
    public void createNewFuel(FuelDTO fuelDTO) {
        Fuel fuel = mapper.convertToEntity(fuelDTO, Fuel.class);
        fuel.setCreatedAt(LocalDateTime.now());
        fuel.setCreateUser(getUsername());
        fuelRepository.save(fuel);
    }

    @Override
    public void updateFuel(Long id, FuelDTO fuelDTO) {
        if (Objects.nonNull(fuelRepository.findByName(fuelDTO.getName()))) {
            throw new IllegalArgumentException("Fuel have existed!");
        }
        Fuel fuel = fuelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist"));
        fuel.setUpdateUser(getUsername());
        fuel.setUpdatedAt(LocalDateTime.now());
        fuel.setName(fuelDTO.getName());
        fuelRepository.save(fuel);
    }

    @Override
    public void deleteFuel(Long id) {
        fuelRepository.deleteById(id);
    }
}
