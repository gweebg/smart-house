package org.house;

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
        this.ownerName = "John Doe";
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

}
