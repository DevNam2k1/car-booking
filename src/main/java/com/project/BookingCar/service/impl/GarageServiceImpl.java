package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.model.Garage;
import com.project.BookingCar.domain.model.User;
import com.project.BookingCar.domain.param.GarageParam;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.GarageRepository;
import com.project.BookingCar.repository.GarageRepositoryCustom;
import com.project.BookingCar.repository.UserRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GarageServiceImpl extends BaseService implements GarageService {

    private final GarageRepository garageRepository;

    private final UserRepository userRepository;

    private final GarageRepositoryCustom garageRepositoryCustom;

    private final CommonMapper mapper;


    @Override
    public Page<GarageDTO> getPagingOfGarage(GarageParam garageParam, Integer pageNo, Integer pageSize) {
        int page = pageNo == 0? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Garage> garagePage = garageRepositoryCustom.getPagingGarage(garageParam, pageable);
        return mapper.convertToResponsePage(garagePage,GarageDTO.class,pageable);
    }

    @Override
    public GarageDTO getGarageById(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Garage not exist")
                );
        return mapper.convertToResponse(garage, GarageDTO.class);
    }

    @Override
    public void createNewGarage(GarageDTO garageDTO) {
        User user = userRepository.findById(garageDTO.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not exist")
                );
        Garage garage = mapper.convertToEntity(garageDTO, Garage.class);
        garage.setUser(user);
        garage.setCreatedAt(LocalDateTime.now());
        garage.setCreateUser(getUsername());
        garageRepository.save(garage);
    }

    @Override
    public void updateGarage(Long id, GarageDTO garageDTO) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Garage not exist")
                );
        garage.setUpdateUser(getUsername());
        garage.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(garageDTO.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not exist")
                );
        garage.setUser(user);
        garageRepository.save(garage);
    }

    @Override
    public void deleteGarage(Long id) {
        garageRepository.deleteById(id);
    }
}
