package com.anas.chatty.repo;

import com.anas.chatty.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, String> {

    @Query("""
        SELECT m from Message m WHERE m.dest.id = :contactId or m.source.id = :contactId
    """)
    List<Message> finBySourceOrDest(Long contactId);

    @Query("""
        SELECT m from Message m
        WHERE (m.source.id = :con1Id and m.dest.id = :con2Id)
        or (m.source.id = :con2Id and m.dest.id = :con1Id)
    """)
    List<Message> findMessageBetweenTwoContacts(Long con1Id, Long con2Id);

    @Query(value = """
        INSERT INTO messages (content, source_id, dest_id, sent_at) VALUES (?, ?, ?, ?)
    """ , nativeQuery = true)
    void insertMessage(String content, Long sourceId, Long destId, LocalDateTime sentAt);

}
