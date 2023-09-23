package org.project.core.core.process.params.cache;

import org.project.data.entities.ProcessParamsEntity;
import org.project.model.Indicators;

import java.util.List;

public class CacheDepthMapper {

    public CacheDepths map(List<ProcessParamsEntity> params) {
        var result = new CacheDepths();
        for (var param : params) {
            if (isApo(param))
                result.setApoDepth(param.getNumberValue().longValue());
            if (isBband(param))
                result.setBbandDepth(param.getNumberValue().longValue());
            if (isEma(param))
                result.setEmaDepth(param.getNumberValue().longValue());
            if (isRsi(param))
                result.setRsiDepth(param.getNumberValue().longValue());
            if (isSma(param))
                result.setSmaDepth(param.getNumberValue().longValue());
            if (isStd(param))
                result.setStdDepth(param.getNumberValue().longValue());
        }
        return result;
    }

    private boolean isApo(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.APO.name());
    }

    private boolean isBband(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.BBAND.name());
    }

    private boolean isEma(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.EMA.name());
    }

    private boolean isRsi(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.RSI.name());
    }

    private boolean isSma(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.SMA.name());
    }

    private boolean isStd(ProcessParamsEntity param) {
        return param.getName()
                .contains(Indicators.STD.name());
    }

}
