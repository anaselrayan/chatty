package com.anas.chatty.repo;

import com.anas.chatty.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("""
        SELECT r FROM Room r WHERE r.contact.id = :contactId
    """)
    List<Room> findByContactId(Long contactId);

    @Query("""
        SELECT r FROM Room r WHERE r.contact.id <> :contactId
    """)
    List<Room> findAllRoomsExceptContact(Long contactId);

    void deleteById(Long roomId);
}
