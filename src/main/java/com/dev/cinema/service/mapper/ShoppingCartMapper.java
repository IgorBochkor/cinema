package com.dev.cinema.service.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto toDto = new ShoppingCartResponseDto();
        toDto.setShoppingCartId(shoppingCart.getId());
        toDto.setUserEmail(shoppingCart.getUser().getEmail());
        toDto.setTickets(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return toDto;
    }
}
