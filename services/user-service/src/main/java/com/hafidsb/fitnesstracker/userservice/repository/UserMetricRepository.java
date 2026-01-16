package com.hafidsb.fitnesstracker.userservice.repository;

import com.hafidsb.fitnesstracker.userservice.entity.UserMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserMetricRepository extends JpaRepository<UserMetric, UUID> {

    @Query(value = """
        SELECT um.*
        FROM user_metrics um
        JOIN users u ON um.user_id = u.id
        WHERE u.id = :userId
            AND um.type = CAST(:type AS metric_type_enum)
            AND u.status = 'ACTIVE'
        ORDER BY um.recorded_at DESC
        LIMIT :limit
    """, nativeQuery = true)
    List<UserMetric> findByUserIdAndTypeOrderByRecordedAtDesc(
            @Param("userId") UUID userId,
            @Param("type") UserMetric.MetricType type,
            @Param("limit") int limit
    );
}
