package org.project.gate.param;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.services.interfaces.ProcessParamsService;

@RequiredArgsConstructor
public class ParamsSaver {

    private final ProcessParamsService processParamsService;

    public void saveParam(ParamData data) {
        processParamsService.save(new ProcessParamsEntity()
                .setNumberValue(data.getNumberValue())
                .setStringValue(data.getStringValue())
                .setDateValue(data.getDateValue())
                .setName(data.getName()));
    }

}
