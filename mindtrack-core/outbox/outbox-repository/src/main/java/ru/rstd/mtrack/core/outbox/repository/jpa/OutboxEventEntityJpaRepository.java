package ru.rstd.mtrack.core.outbox.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rstd.mtrack.core.outbox.entity.OutboxEventEntity;

import java.util.List;
import java.util.UUID;

public interface OutboxEventEntityJpaRepository extends JpaRepository<OutboxEventEntity, UUID> {

    @Query(value = """
            select * from mtrack.outbox_event
            where event_type = :type and status = cast(:status as mtrack.outbox_status)
            order by created_at
            limit :limit
            for update skip locked
            """, nativeQuery = true)
    List<OutboxEventEntity> findAllWithLock(@Param("type") String type,
                                            @Param("status") String status,
                                            @Param("limit") Integer limit);


    @Modifying
    @Query(value = """
            UPDATE mtrack.outbox_event
            SET status = cast(:status as mtrack.outbox_status)
            WHERE id IN (:ids)
            """, nativeQuery = true)
    void updateAll(@Param("ids") List<UUID> ids, @Param("status") String status);
}
