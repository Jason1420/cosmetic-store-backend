package com.store.cosmetic.converter;

import com.store.cosmetic.dto.invoice.CartItemDTO;
import com.store.cosmetic.entity.invoice.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartItemConverter {
    private final InvoiceItemConverter invoiceItemConverter;
    public CartItem toEntity (CartItemDTO cartItemDTO){
        return new CartItem(cartItemDTO.getTotalPrice(),
                cartItemDTO.getTotalQuantity(),
                cartItemDTO.getItems().stream().
                        map(invoiceItemConverter::toEntity).collect(Collectors.toList()));
    }
    public CartItemDTO toDTO (CartItem CartItemEntity){
        return new CartItemDTO(CartItemEntity.getTotalPrice(),
                CartItemEntity.getTotalQuantity(),
                CartItemEntity.getItems().stream().
                        map(invoiceItemConverter::toDTO).collect(Collectors.toList()));
    }



}
