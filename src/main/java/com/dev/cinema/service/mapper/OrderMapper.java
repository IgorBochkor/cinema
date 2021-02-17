package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm";

    public OrderResponseDto mapOrderToResponseDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        responseDto.setUserEmail(order.getUser().getEmail());
        responseDto.setTickets(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        responseDto.setShowTime(order.getOrderDate()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        return responseDto;
    }
}
