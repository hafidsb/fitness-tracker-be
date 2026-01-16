package com.hafidsb.fitnesstracker.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    @Column
    private String fullName;

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "gender::text", write = "?::gender_enum")
    private Gender gender;

    @Column
    private LocalDate birthDate;

    @Column
    private Short heightCm;

    @Column
    private BigDecimal currentWeightKg;

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "status::text", write = "?::user_status_enum")
    private Status status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        this.status = Status.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum Status {
        ACTIVE, SUSPENDED, DELETED
    }
}
