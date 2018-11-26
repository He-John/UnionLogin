package com.ky.entity;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -5652419863004462459L;

    private String userName;
    private String userPhone;
    private String userIdcard;

}
