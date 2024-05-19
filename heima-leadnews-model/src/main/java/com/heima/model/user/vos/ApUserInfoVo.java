package com.heima.model.user.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApUserInfoVo {
    private Integer id;
    private String name;
    private String phone;
}
