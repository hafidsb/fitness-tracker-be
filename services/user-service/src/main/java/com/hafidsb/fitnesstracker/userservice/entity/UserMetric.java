package com.hafidsb.fitnesstracker.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_metrics")
public class UserMetric {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "metric_type_enum")
    private MetricType type;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", columnDefinition = "metric_source_enum")
    private MetricSource source;

    @Column(name = "recorded_at", nullable = false)
    private OffsetDateTime recordedAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    void prePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = OffsetDateTime.now();
    }

    public enum MetricType {
        WEIGHT,
        BODY_FAT
    }

    public enum MetricSource {
        MANUAL,
        DEVICE
    }
}
