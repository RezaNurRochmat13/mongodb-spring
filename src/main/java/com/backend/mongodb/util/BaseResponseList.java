package com.backend.mongodb.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class BaseResponseList implements Serializable {
    private int count;
    private Long total;
    private Object data;
}
