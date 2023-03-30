package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.CarDTO;
import com.project.BookingCar.domain.dto.page.CarPageDTO;
import com.project.BookingCar.domain.model.BodyStyle;
import com.project.BookingCar.domain.model.Brand;
import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.model.Fuel;
import com.project.BookingCar.domain.param.CarParam;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.*;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarServiceImpl extends BaseService implements CarService {
    private final CarRepository carRepository;

    private final CarRepositoryCustom carRepositoryCustom;

    private final FuelRepository fuelRepository;

    private final BrandRepository brandRepository;

    private final BodyStyleRepository bodyStyleRepository;

    private final CommonMapper mapper;
    @Override
    public Page<CarDTO> getPagingOfCar(CarParam carParam, Integer pageNumber, Integer pageSize) {
        int page = pageNumber == 0 ? pageNumber: pageNumber-1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Car> carPage = carRepositoryCustom.getPagingOfCar(carParam, pageable);
        return mapper.convertToResponsePage(carPage,CarDTO.class,pageable);
    }

    @Override
    public CarPageDTO getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car is not exist!"));
        return mapper.convertToResponse(car, CarPageDTO.class);
    }

    @Override
    public void createCar(CarDTO carDTO) {
        Car car = Car.builder()
                .model(carDTO.getModel())
                .origin(carDTO.getOrigin())
                .build();
        car.setCreatedAt(LocalDateTime.now());
        car.setCreateUser(getUsername());

        Fuel fuel = fuelRepository.findById(carDTO.getFuelId()).orElseThrow(() -> new IllegalArgumentException("Fuel is not exist!"));
        car.setFuel(fuel);
        Brand brand = brandRepository.findById(carDTO.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Brand is not exist!"));
        car.setBrand(brand);
        BodyStyle bodyStyle = bodyStyleRepository.findById(carDTO.getBodyStyleId()).orElseThrow(() -> new IllegalArgumentException("Body-style is not exist!"));
        car.setBodyStyle(bodyStyle);

        carRepository.save(car);

    }

    @Override
    public void updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car is not exist!"));
        car.setUpdatedAt(LocalDateTime.now());
        car.setUpdateUser(getUsername());

        Fuel fuel = fuelRepository.findById(carDTO.getFuelId()).orElseThrow(() -> new IllegalArgumentException("Fuel is not exist!"));
        car.setFuel(fuel);
        Brand brand = brandRepository.findById(carDTO.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Brand is not exist!"));
        car.setBrand(brand);
        BodyStyle bodyStyle = bodyStyleRepository.findById(carDTO.getBodyStyleId()).orElseThrow(() -> new IllegalArgumentException("Body-style is not exist!"));
        car.setBodyStyle(bodyStyle);

        carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
