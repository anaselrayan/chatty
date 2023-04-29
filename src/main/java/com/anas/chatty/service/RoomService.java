package com.anas.chatty.service;

import com.anas.chatty.dto.RoomDTO;
import com.anas.chatty.entity.Room;
import com.anas.chatty.repo.RoomRepository;
import com.anas.chatty.websockets.utils.RoomStateHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomStateHolder roomStateHolder;

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    public Room addRoom(RoomDTO dto) {
        var room = new Room(dto.getName(), dto.getContact());
        room.setBackground(getRandomBackground());
        var savedRoom =  this.repository.save(room);
        roomStateHolder.createBasicRoomDetail(savedRoom.getId());
        return savedRoom;
    }

    private String getRandomBackground() {
        int v1 = new Random().nextInt(50);
        int v2 = new Random().nextInt(50);
        int v3 = new Random().nextInt(50);

        return "rgb(" + v1 + "," + v2 + "," + v3 + ")";
    }

    public List<Room> getContactRooms(Long contactId) {
        return repository.findByContactId(contactId);
    }

    public List<Room> getAllRoomsExceptContact(Long contactId) {
        return repository.findAllRoomsExceptContact(contactId);
    }

    public boolean deleteRoom(Long roomId) {
        repository.deleteById(roomId);
        roomStateHolder.getRoomState().remove(roomId);
        return true;
    }

    public boolean lockRoom(Long roomId) {
        roomStateHolder.getRoomState().get(roomId).setLocked(true);
        return true;
    }

    public boolean unlockRoom(Long roomId) {
        roomStateHolder.getRoomState().get(roomId).setLocked(false);
        return true;
    }
}
