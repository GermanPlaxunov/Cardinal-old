package org.project.core.core.process.params.cache;

import lombok.Data;

@Data
public class CacheDepths {

    private Long defaultDepth;
    private Long apoDepth;
    private Long bbandDepth;
    private Long emaDepth;
    private Long rsiDepth;
    private Long smaDepth;
    private Long stdDepth;

}
