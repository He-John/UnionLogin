package com.ky.param;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OpenRequest<T> implements Serializable {
    private static final long serialVersionUID = -8866600287352184187L;
    private String ua;
    private String call;
    private T args;
    private String sign;
    private String timestamp;
}
