package com.backend.mongodb.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
@NoArgsConstructor
public class BaseResponseList implements Serializable {
    private int count;
    private Long total;
    private Object data;
}
