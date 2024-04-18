package com.his.his.PublicPrivateMapping;

import java.util.UUID;

import com.his.his.converter.UUIDStringCryptoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name="public_private_id_mapping")
public class PublicPrivateId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private String type;

    @Convert(converter = UUIDStringCryptoConverter.class)
    @Column(unique = true)
    private UUID privateId;

    private String publicId;

    public PublicPrivateId(String type, UUID privateId) {
        this.type = type;
        this.privateId = privateId;
    }

    @PostPersist
    public void generatePublicId() {
        this.publicId = type + "_" + id;
    }
}
