package com.ky.param;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OpenResponse implements Serializable {
    private static final long serialVersionUID = -2766293760596458795L;
    private int status;
    private String message;
    private Object response;
}
