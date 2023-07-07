package org.project.gate.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.gate.client.CoreClient;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
public class SimpleJobService {

    private final CoreClient coreClient;
    private final String url;

    public void execute() {
        coreClient.startProcess(URI.create(url));
    }

}
