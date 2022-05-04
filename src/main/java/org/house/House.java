package org.house;

import org.devices.SmartDevice;
import org.exceptions.NonexistentDeviceException;
import org.exceptions.NonexistentRoomException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
public class House
{
    private String ownerName;
    private int ownerNIF;
    private List<Room> rooms;

    public House()
    {
        this.ownerName = "Unknown";
        this.ownerNIF = 0;
        this.rooms = new ArrayList<>();
    }

    public House(String owner, int nif)
    {
        this.ownerName = owner;
        this.ownerNIF = nif;
        this.rooms = new ArrayList<>();
    }

    public House(String owner, int nif, @NotNull List<Room> rooms)
    {
        this.ownerName = owner;
        this.ownerNIF = nif;

        this.rooms = new ArrayList<>();
        for (Room r : rooms)
        {
            if (r != null) this.rooms.add(r.clone());
        }
    }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public int getOwnerNIF() { return ownerNIF; }
    public void setOwnerNIF(int ownerNIF) { this.ownerNIF = ownerNIF; }

    public void setRoomOn(String roomName) throws NonexistentRoomException
    {
        Room currentRoom = this.rooms.stream().filter(room -> roomName.equals(room.getName())).findAny().orElse(null);
        if (currentRoom == null) throw new NonexistentRoomException("The room " + roomName + " does not exist in the house: " + this.ownerNIF);

        /* private HashMap<Integer, SmartDevice> devices; */
        currentRoom.setAllDevicesState(SmartDevice.State.ON);
    }

    // Método mal feito, melhorar.
    public void setDeviceStateOnRoom(int deviceId) throws NonexistentDeviceException
    {
        for (Room room : this.rooms)
        {
            if (room.deviceExists(deviceId))
            {
                room.setDeviceState(SmartDevice.State.ON, deviceId);
            }
        }
    }


}
