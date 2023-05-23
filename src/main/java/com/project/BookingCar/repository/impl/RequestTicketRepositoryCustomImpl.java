package com.project.BookingCar.repository.impl;

import com.project.BookingCar.domain.enums.RequestTicketType;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.enums.ServiceTicketsStatus;
import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.repository.BaseRepository;
import com.project.BookingCar.repository.RequestTicketRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RequestTicketRepositoryCustomImpl implements RequestTicketRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    private static final String FROM_TABLE = "FROM car_booking.request_ticket rt ";

    private static final String JOIN_SERVICE_TABLE = "LEFT JOIN car_booking.service_ticket st ON rt.id = st.request_ticket_id ";

    private static final String AND_DRIVER_REF_ID = "AND rt.driver_ref_id = :driverId ";

    private static final String SELECT_FIELD = "SELECT rt.* ";

    private static final String COUNT_TABLE = "SELECT COUNT(rt.id ) ";

    private static final String DRIVER_ID = "driverId";

    private static final String REQUEST_STATUS = "requestStatuses";

    private static final String SORT_CONDITION = "ORDER BY rt.appointment_date ASC, rt.created_at DESC";


    @Override
    public List<RequestTicket> listAppointmentScheduleForDriverWithRequestTicketStatus(List<RequestTicketsStatus> statusesRequestTicket, List<ServiceTicketsStatus> serviceTicketsStatuses, List<RequestTicketsStatus> notInStatusesRequestTicket, Long driverId, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(FROM_TABLE);
        strQuery.append(JOIN_SERVICE_TABLE);
        strQuery.append("WHERE (rt.status IN (:requestStatuses) OR st.status IN (:serviceTicketsStatuses)) ");
        strQuery.append(AND_DRIVER_REF_ID);
        strQuery.append("AND rt.type != :upgrade ");


        Map<String, Object> params = new HashMap<>();
        params.put(REQUEST_STATUS, statusesRequestTicket.stream().map(RequestTicketsStatus::toString).collect(Collectors.toList()));
        params.put("serviceTicketsStatuses", serviceTicketsStatuses.stream().map(ServiceTicketsStatus::toString).collect(Collectors.toList()));
        params.put("upgrade", RequestTicketType.UPGRADE.getValue());
        params.put(DRIVER_ID, driverId);
        this.handleNotInRequestStatuses(strQuery, notInStatusesRequestTicket, params);
        String strSelectQuery = SELECT_FIELD + strQuery + SORT_CONDITION;
        return BaseRepository.getResultListNativeQuery(this.em, strSelectQuery, params,RequestTicket.class);
    }

    @Override
    public List<RequestTicket> listAppointmentScheduleForDriverWithServiceTicketStatus(List<ServiceTicketsStatus> serviceTicketsStatuses, List<RequestTicketsStatus> statusesRequestTicket, Long driverId, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(FROM_TABLE);
        strQuery.append(JOIN_SERVICE_TABLE);
        strQuery.append("WHERE st.status IN (:serviceStatus) ");
        strQuery.append(AND_DRIVER_REF_ID);
        strQuery.append("AND rt.type != :upgrade ");

        Map<String, Object> params = new HashMap<>();
        params.put("serviceStatus", serviceTicketsStatuses.stream().map(ServiceTicketsStatus::toString).collect(Collectors.toList()));
        params.put(DRIVER_ID, driverId);
        params.put("upgrade", RequestTicketType.UPGRADE.getValue());
        this.handleNotInRequestStatuses(strQuery, statusesRequestTicket, params);
        String strSelectQuery = SELECT_FIELD + strQuery + SORT_CONDITION;
        return BaseRepository.getResultListNativeQuery(this.em, strSelectQuery, params, RequestTicket.class);
    }

    private void handleNotInRequestStatuses(StringBuilder stringBuilder, List<RequestTicketsStatus> requestTicketsStatuses, Map<String, Object> params){
        if (Objects.nonNull(requestTicketsStatuses) && !requestTicketsStatuses.isEmpty()) {
            stringBuilder.append(" AND rt.status NOT IN (:notInRequestStatuses) ");
            params.put("notInRequestStatuses", requestTicketsStatuses.stream().map(RequestTicketsStatus::getValue).collect(Collectors.toList()));
        }
    }
}
