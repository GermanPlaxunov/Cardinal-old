package org.cardinal.data.services.interfaces;

import org.cardinal.data.entities.ShareEntity;

public interface ShareService {

    void save(ShareEntity shareEntity);

    ShareEntity findByName(String name);

}
