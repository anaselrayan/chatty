package com.anas.chatty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
    private Long sourceId;
    private Long destId;
    private String content;
    private LocalDateTime sentAt;

    public MessageDTO(Long sourceId, Long destId, String content) {
        this.sourceId = sourceId;
        this.destId = destId;
        this.content = content;
        this.sentAt = LocalDateTime.now();
    }
}
