package com.azizONeill.userservice.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
}
