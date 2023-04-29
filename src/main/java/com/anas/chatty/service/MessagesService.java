package com.anas.chatty.service;

import com.anas.chatty.dto.MessageDTO;
import com.anas.chatty.entity.Message;
import com.anas.chatty.repo.MessagesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {
    private final MessagesRepository messagesRepository;
    private final EntityManager manager;

    public void addMessage(Message msg) {
        messagesRepository.save(msg);
    }

    @Transactional
    public void insertMessage(MessageDTO msg) {
        String sql = "INSERT INTO messages (content, source_id, dest_id, sent_at) VALUES (?, ?, ?, ?)";
        Query query = manager.createNativeQuery(sql);
        query.setParameter(1, msg.getContent());
        query.setParameter(2, msg.getSourceId());
        query.setParameter(3, msg.getDestId());
        query.setParameter(4, msg.getSentAt());
        query.executeUpdate();
    }

    public List<Message> getMessagesBetweenTwoContacts(Long contact1Id, Long contact2Id) {
        return messagesRepository.findMessageBetweenTwoContacts(contact1Id, contact2Id);
    }
}
