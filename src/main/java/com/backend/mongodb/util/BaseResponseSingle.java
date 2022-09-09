package com.backend.mongodb.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class BaseResponseSingle implements Serializable {
    private Object data;
}
