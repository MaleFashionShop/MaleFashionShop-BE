package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class UserUpdateDto {
    private String name;
    private String password;
    private Long roleID;


}
