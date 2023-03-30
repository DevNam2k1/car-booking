package com.project.BookingCar.repository.impl;

import com.project.BookingCar.domain.model.Garage;
import com.project.BookingCar.domain.param.GarageParam;
import com.project.BookingCar.repository.BaseRepository;
import com.project.BookingCar.repository.GarageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class GarageRepositoryCustomImpl implements GarageRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    private final static String FROM_GARAGE = " FROM car_booking.garage g ";

    @Override
    public Page<Garage> getPagingGarage(GarageParam garageParam, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(FROM_GARAGE);
        strQuery.append("WHERE 1=1");

        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(garageParam.getAddress()) && !garageParam.getAddress().isEmpty()) {
            strQuery.append(" AND g.address LIKE :address");
            params.put("address", "%" +garageParam.getAddress()+"%");
        }

        if (Objects.nonNull(garageParam.getName()) && !garageParam.getName().isEmpty()) {
            strQuery.append(" AND g.name LIKE :name");
            params.put("name", "%" +garageParam.getName()+"%");
        }

        if (Objects.nonNull(garageParam.getUsername()) && !garageParam.getUsername().isEmpty()) {
            strQuery.append(" AND g.username LIKE :username");
            params.put("username", "%" +garageParam.getUsername()+"%");
        }

        if (Objects.nonNull(garageParam.getLatiTude())) {
            strQuery.append(" AND g.latitude = :latitude");
            params.put("latitude", garageParam.getLatiTude());
        }

        if (Objects.nonNull(garageParam.getLongiTude())) {
            strQuery.append(" AND g.longitude = :longitude");
            params.put("longitude", garageParam.getLongiTude());
        }

        String strSelectQuery = "SELECT * " + strQuery;

        String strCountQuery = "SELECT COUNT(DISTINCT g.id) "+ strQuery;

        return BaseRepository.getPagedNativeQuery(this.em, strSelectQuery, strCountQuery,params, pageable, Garage.class);
    }
}
