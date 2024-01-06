package org.cardinal.gate;

import lombok.RequiredArgsConstructor;
import org.cardinal.data.entities.ProcessParamsEntity;
import org.cardinal.data.services.interfaces.ProcessParamsService;
import org.cardinal.model.ParamData;

@RequiredArgsConstructor
public class ParamsSaver {

    private final ProcessParamsService processParamsService;

    /**
     * Сохраняет новый параметр в БД
     *
     * @param paramData
     */
    public void saveNewParam(ParamData paramData) {
        processParamsService.save(new ProcessParamsEntity()
                .setName(paramData.getName())
                .setStringValue(paramData.getStringValue())
                .setNumberValue(paramData.getNumberValue())
                .setDateValue(paramData.getDateValue()));
    }

}
