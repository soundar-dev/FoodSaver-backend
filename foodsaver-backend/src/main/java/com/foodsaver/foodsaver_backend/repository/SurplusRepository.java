package com.foodsaver.foodsaver_backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.foodsaver.foodsaver_backend.entity.Surplus;

public interface SurplusRepository extends JpaRepository<Surplus, Long> {

    /* =========================
       AUTO CLEAN (ALREADY GOOD)
       ========================= */
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Surplus s
        WHERE s.status = 'PICKED'
        AND s.createdAt <= :cutoffTime
    """)
    int deletePickedOlderThan(@Param("cutoffTime") LocalDateTime cutoffTime);

    /* =========================
       EXPIRY SUPPORT
       ========================= */
    List<Surplus> findByCreatedAtBefore(LocalDateTime time);

    /* =========================
       DASHBOARD COUNTS (NEW)
       ========================= */

    // Total by status (AVAILABLE / ACCEPTED / PICKED)
    long countByStatus(Surplus.Status status);

    // Items added today
    long countByCreatedAtAfter(LocalDateTime time);

    // Expired but not picked
    @Query("""
        SELECT COUNT(s)
        FROM Surplus s
        WHERE s.status <> 'PICKED'
        AND s.createdAt <= :cutoffTime
    """)
    long countExpired(@Param("cutoffTime") LocalDateTime cutoffTime);
}
