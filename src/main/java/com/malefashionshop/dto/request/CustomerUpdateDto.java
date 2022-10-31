package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder=true)
public class CustomerUpdateDto {

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private Long roleID;


}
