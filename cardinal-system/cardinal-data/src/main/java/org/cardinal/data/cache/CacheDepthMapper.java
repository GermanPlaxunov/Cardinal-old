package org.cardinal.data.cache;

import org.cardinal.data.entities.ProcessParamsEntity;
import org.cardinal.model.Indicators;

import java.util.Comparator;
import java.util.List;

public class CacheDepthMapper {

    public CacheDepths map(List<ProcessParamsEntity> params) {
        var result = new CacheDepths();
        result.setMaxDepth(getMaxDepth(params));
        result.setMinDepth(getMinDepth(params));
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

    private Long getMaxDepth(List<ProcessParamsEntity> params) {
        return params.stream()
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .max(Comparator.naturalOrder())
                .orElse(0L);
    }

    private Long getMinDepth(List<ProcessParamsEntity> params) {
        return params.stream()
                .map(ProcessParamsEntity::getNumberValue)
                .map(Double::longValue)
                .min(Comparator.naturalOrder())
                .orElse(0L);

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
