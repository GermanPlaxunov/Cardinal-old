package org.cardinal.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParamData {

    private String name;
    private Double numberValue;
    private LocalDateTime dateValue;
    private String stringValue;

}
