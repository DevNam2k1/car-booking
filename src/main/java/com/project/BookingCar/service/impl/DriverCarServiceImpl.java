package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.DriverCarDTO;
import com.project.BookingCar.domain.dto.page.DriverCarPageDTO;
import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.model.Driver;
import com.project.BookingCar.domain.model.DriverCar;
import com.project.BookingCar.domain.param.DriverCarParam;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.CarRepository;
import com.project.BookingCar.repository.DriverCarRepository;
import com.project.BookingCar.repository.DriverCarRepositoryCustom;
import com.project.BookingCar.repository.DriverRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.DriverCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverCarServiceImpl extends BaseService implements DriverCarService {
    private final DriverCarRepository driverCarRepository;
    private final DriverCarRepositoryCustom driverCarRepositoryCustom;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final CommonMapper mapper;
    @Override
    public Page<DriverCarPageDTO> getPagingCarOfDriverId(Long driverId, Integer pageNum, Integer pageSize) {
        int page = pageNum == 0 ? pageNum : pageNum - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<DriverCar> driverCars = driverCarRepositoryCustom.getCarOfDriverByDriverId(driverId, pageable);
        return mapper.convertToResponsePage(driverCars,DriverCarPageDTO.class,pageable);
    }

    @Override
    public Page<DriverCarPageDTO> getPagingCarAndSearchingForParam(DriverCarParam driverCarParam, Integer pageNum, Integer pageSize) {
        int page = pageNum == 0 ? pageNum : pageNum - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<DriverCar> driverCars = driverCarRepositoryCustom.getAllCarOfDriverAndSearchingForParam(driverCarParam,pageable);
        return mapper.convertToResponsePage(driverCars,DriverCarPageDTO.class,pageable);
    }

    @Override
    public DriverCarPageDTO getCarOfDriverById(Long driverCarId) {
        DriverCar driverCar = driverCarRepository.findById(driverCarId).orElseThrow(() -> new IllegalArgumentException("Car of driver is not exist!"));
        return mapper.convertToResponse(driverCar,DriverCarPageDTO.class);
    }

    @Override
    public void createCarOfDriver(DriverCarDTO driverCarDTO) {
        DriverCar driverCar = mapper.convertToEntity(driverCarDTO,DriverCar.class);
        if (driverCarDTO.getCarId() != null) {
            Car car = carRepository.findById(driverCarDTO.getCarId())
                    .orElseThrow(() -> new IllegalArgumentException("Car is not exist!"));
            driverCar.setCar(car);
        }
        if (driverCarDTO.getDriverId() != null) {
            Driver driver = driverRepository.findById(driverCarDTO.getDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver is not exist!"));
            driverCar.setDriver(driver);
            driverCar.setCreatedAt(LocalDateTime.now());
            driverCar.setCreateUser(getUsername());
        driverCarRepository.save(driverCar);
        }

    }

    @Override
    public void updateCarOfDriver(Long id, DriverCarDTO driverCarDTO) {
        DriverCar driverCar = driverCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car of driver is not exist!"));
        driverCar.setChassisNumber(driverCarDTO.getChassisNumber());
        driverCar.setCarImage(driverCar.getCarImage());
        driverCar.setColor(driverCarDTO.getColor());
        driverCar.setLicensePlate(driverCarDTO.getLicensePlate());
        driverCar.setEngineNumber(driverCarDTO.getEngineNumber());
        if (!driverCarDTO.getStatus().isEmpty() || driverCarDTO.getDriverId() != null || driverCarDTO.getCarId() != null){
            throw new IllegalArgumentException("You can't change this information! Ex:status, driverId, carId");
        }
        driverCarRepository.save(driverCar);
    }

    @Override
    public void disableCarOfDriver(Long id) {
        DriverCar driverCar = driverCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car of driver is not exist!"));
        if (driverCar.getStatus() == "active"){
            driverCar.setStatus("disable");
        } else {
            driverCar.setStatus("active");
        }
        driverCarRepository.save(driverCar);
    }
}
