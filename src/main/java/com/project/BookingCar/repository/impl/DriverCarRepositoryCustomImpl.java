package com.project.BookingCar.repository.impl;

import com.project.BookingCar.domain.model.DriverCar;
import com.project.BookingCar.domain.param.DriverCarParam;
import com.project.BookingCar.repository.BaseRepository;
import com.project.BookingCar.repository.DriverCarRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class DriverCarRepositoryCustomImpl implements DriverCarRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    private final static String FROM_GARAGE = " FROM car_booking.driver_car dc ";

    @Override
    public Page<DriverCar> getCarOfDriverByDriverId(Long driverId, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(FROM_GARAGE);
        strQuery.append(" INNER JOIN car_booking.driver d ");

        Map<String, Object> params = new HashMap<>();
        if (!driverId.equals(null)) {
            strQuery.append(" WHERE d.id = :driverId");
            params.put("driverId", driverId);
        }
        String selectQuery = "SELECT * " + strQuery;

        String selectCount = "SELECT COUNT(DISTINCT c.id)" + strQuery;
        return BaseRepository.getPagedNativeQuery(em,selectQuery, selectCount ,params,pageable,DriverCar.class);
    }

    @Override
    public Page<DriverCar> getAllCarOfDriverAndSearchingForParam(DriverCarParam driverCarParam, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(FROM_GARAGE);
        strQuery.append("WHERE 1=1");

        Map<String, Object> params = new HashMap<>();
        if(Objects.nonNull(driverCarParam.getChassisNumber()) && !driverCarParam.getChassisNumber().isEmpty() ){
            strQuery.append("AND dc.chassis_number = :chassisNumber");
            params.put("chassisNumber",driverCarParam.getChassisNumber());
        }
        if (Objects.nonNull(driverCarParam.getEngineNumber()) && !driverCarParam.getEngineNumber().isEmpty()) {
            strQuery.append("AND dc.engine_number = :engineNumber");
            params.put("engineNumber", driverCarParam.getEngineNumber());
        }
        if (Objects.nonNull(driverCarParam.getLicensePlate()) && !driverCarParam.getLicensePlate().isEmpty()){
            strQuery.append("AND dc.license_plate = :licensePlate");
            params.put("licensePlate", driverCarParam.getLicensePlate());
        }
        if (Objects.nonNull(driverCarParam.getColor()) && !driverCarParam.getColor().isEmpty()){
            strQuery.append("AND dc.color = :color");
            params.put("color", driverCarParam.getColor());
        }
        String selectQuery = "SELECT * " + strQuery ;

        String countQuery = "SELECT COUNT(DISTINCT c.id)" + strQuery;

        return BaseRepository.getPagedNativeQuery(em,selectQuery, countQuery,params,pageable, DriverCar.class);
    }
}
