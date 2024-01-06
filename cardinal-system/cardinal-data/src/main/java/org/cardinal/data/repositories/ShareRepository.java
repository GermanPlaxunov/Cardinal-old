package org.cardinal.data.repositories;

import org.cardinal.data.entities.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
}
