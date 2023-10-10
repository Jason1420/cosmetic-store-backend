package com.store.cosmetic.dto.invoice;
import com.store.cosmetic.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDTO {
    private ItemDTO item;
    private Long quantity;
}
