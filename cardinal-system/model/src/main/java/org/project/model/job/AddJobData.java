package org.project.model.job;

import lombok.Data;

@Data
public class AddJobData {

    private String name;
    private Long intervalMillis;
    private String stockName;
    private String jobType;
    private String description;
    private Boolean isActive;
    private String triggerName;
    private String triggerDescription;

}
