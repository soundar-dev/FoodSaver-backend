package com.foodsaver.foodsaver_backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.foodsaver.foodsaver_backend.dto.AdminSurplusCardDTO;
import com.foodsaver.foodsaver_backend.entity.Surplus;

public interface SurplusRepository extends JpaRepository<Surplus, Long> {

    /* ---------- COMMON ---------- */

    long countByStatus(Surplus.Status status);

    long countByAcceptedBy(String acceptedBy);

    List<Surplus> findByFoodNameContainingIgnoreCaseOrLocationContainingIgnoreCase(
        String foodName,
        String location
    );

    /* ---------- ADMIN ONLY ---------- */

    List<Surplus> findByCreatedBy(String createdBy);

   @Query("""
        SELECT new com.foodsaver.foodsaver_backend.dto.AdminSurplusCardDTO(
            s.id,
            s.foodName,
            s.location,
            s.quantity,
            s.postedBy,
            s.acceptedBy,
            s.expiryHours,
            s.status
        )
        FROM Surplus s
        WHERE s.createdBy = :adminEmail
    """)
    List<AdminSurplusCardDTO> findAllForAdmin(@Param("adminEmail") String adminEmail);
    /* ---------- USER / NGO ---------- */

    @Query("""
        SELECT s
        FROM Surplus s
        WHERE s.status IN ('AVAILABLE', 'ACCEPTED')
    """)
    List<Surplus> findForUsers();

    /* ---------- CLEANUP ---------- */

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Surplus s
        WHERE s.status = 'PICKED'
        AND s.createdAt <= :cutoffTime
    """)
    int deletePickedOlderThan(@Param("cutoffTime") LocalDateTime cutoffTime);

    @Query("""
        SELECT COUNT(s)
        FROM Surplus s
        WHERE s.status <> 'PICKED'
        AND s.createdAt <= :cutoffTime
    """)
    long countExpired(@Param("cutoffTime") LocalDateTime cutoffTime);
}

