package com.malefashionshop.dto.request;

import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class OrderUpdateDto {

    @NotNull(message= "Customer ID  is required")
    private Long customerID;

    private List<Long> orderItemID;


}
