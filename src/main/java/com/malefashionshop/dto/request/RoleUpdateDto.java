package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class RoleUpdateDto {
    private String name;
    private String temp;
}
