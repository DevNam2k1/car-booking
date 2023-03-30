package com.project.BookingCar.repository.impl;

import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.param.CarParam;
import com.project.BookingCar.repository.BaseRepository;
import com.project.BookingCar.repository.CarRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
    private final static String FROM_GARAGE = " FROM car_booking.car c ";
    private final static String JOIN_TABLE_FUEL = " LEFT JOIN car_booking.fuel f on c.fuel_id = f.id ";
    private final static String JOIN_TABLE_BODY_STYLE = " LEFT JOIN car_booking.body_style bs on c.body_style_id  = bs.id ";
    private final static String JOIN_TABLE_BRAND = " LEFT JOIN car_booking.brand b on c.brand_id = b.id ";
    @Override
    public Page<Car> getPagingOfCar(CarParam carParam, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder();
        strQuery.append(" WHERE 1=1");

        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(carParam.getModel()) && !carParam.getModel().isEmpty()) {
            strQuery.append(" AND c.model LIKE :model");
            params.put("model", "%" +carParam.getModel()+"%");
        }
        if (Objects.nonNull(carParam.getOrigin()) && !carParam.getOrigin().isEmpty()){
            strQuery.append(" AND c.origin LIKE :origin");
            params.put("origin","%"+carParam.getOrigin()+"%");
        }
        if (Objects.nonNull(carParam.getBodyStyleId())) {
            joinQuery.append(JOIN_TABLE_BODY_STYLE);
            strQuery.append(" AND bs.id = :bodyStyleId");
            params.put("bodyStyleId", carParam.getBodyStyleId());
        }
        if (Objects.nonNull(carParam.getBrandId())) {
            joinQuery.append(JOIN_TABLE_BRAND);
            strQuery.append(" AND b.id = :brandId");
            params.put("brandId", carParam.getBrandId());
        }
        if (Objects.nonNull(carParam.getFuelId())) {
            joinQuery.append(JOIN_TABLE_FUEL);
            strQuery.append(" AND f.id = :fuelId");
            params.put("fuelId", carParam.getFuelId());
        }

        String strSelectQuery = "SELECT * " + FROM_GARAGE + joinQuery + strQuery;

        String strCountQuery = "SELECT COUNT(DISTINCT c.id)" + FROM_GARAGE + joinQuery + strQuery;
        return BaseRepository.getPagedNativeQuery(em,strSelectQuery, strCountQuery, params, pageable, Car.class);
    }
}
