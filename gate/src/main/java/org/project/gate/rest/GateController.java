package org.project.gate.rest;

import org.springframework.web.bind.annotation.RequestParam;

public interface GateController {
    void saveNewJob(String name, Long interval, String stock);


}
