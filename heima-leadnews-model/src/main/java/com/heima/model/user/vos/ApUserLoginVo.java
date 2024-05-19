package com.heima.model.user.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApUserLoginVo {
    private ApUserInfoVo user;
    private String token;
}
