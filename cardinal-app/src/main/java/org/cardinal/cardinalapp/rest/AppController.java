package org.cardinal.cardinalapp.rest;

import org.cardinal.model.ParamData;

import java.util.List;

public interface AppController {

    void saveNewParam(ParamData paramData);

    List<String> getInstrumentIds();

}
