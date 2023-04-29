package com.anas.chatty.resource;

import com.anas.chatty.dto.RoomDTO;
import com.anas.chatty.entity.Room;
import com.anas.chatty.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomResource {
    private final RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room addRoom(@RequestBody RoomDTO room) {
        return roomService.addRoom(room);
    }

    @GetMapping("user/{userId}")
    public List<Room> getUserRooms(@PathVariable Long userId) {
        return roomService.getContactRooms(userId);
    }

    @GetMapping("except/{userId}")
    public List<Room> getAllRoomsExceptContact(@PathVariable Long userId) {
        return roomService.getAllRoomsExceptContact(userId);
    }

    @DeleteMapping("{roomId}")
    public boolean deleteRoom(@PathVariable Long roomId) {
        return roomService.deleteRoom(roomId);
    }

    @GetMapping("lock/{roomId}")
    public boolean lockRoom(@PathVariable Long roomId) {
        return roomService.lockRoom(roomId);
    }

    @GetMapping("unlock/{roomId}")
    public boolean unlockRoom(@PathVariable Long roomId) {
        return roomService.unlockRoom(roomId);
    }
}
