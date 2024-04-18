package com.his.his.PublicPrivateMapping;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicPrivateRepository extends JpaRepository<PublicPrivateId, Integer>{

    PublicPrivateId findByPublicId(String publicId);

    PublicPrivateId findByPrivateId(UUID privateId);
    
}
