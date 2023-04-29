package com.anas.chatty.resource;

import com.anas.chatty.entity.Message;
import com.anas.chatty.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessagesResource {
    private final MessagesService messagesService;

    @GetMapping("/{contact1Id}/{contact2Id}")
    public List<Message> getAllMessages(@PathVariable Long contact1Id,
                                        @PathVariable Long contact2Id) {
        return messagesService.getMessagesBetweenTwoContacts(contact1Id, contact2Id);
    }

    @PostMapping
    public void insertMessage(@RequestBody Message message) {
        System.out.println("Message: " + message.getContent());
        messagesService.addMessage(message);
    }
}
