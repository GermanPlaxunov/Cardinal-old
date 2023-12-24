package org.project.gate.param;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParamData {

    private String name;
    private String stringValue;
    private Double numberValue;
    private LocalDateTime dateValue;

}
