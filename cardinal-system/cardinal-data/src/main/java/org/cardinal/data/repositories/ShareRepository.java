package org.cardinal.data.repositories;

import org.cardinal.data.entities.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareRepository extends JpaRepository<ShareEntity, Long> {

    Optional<ShareEntity> findByName(String name);

}
