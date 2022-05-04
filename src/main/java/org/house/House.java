package org.house;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public class House {

    private String owner;
    private int ownerNif;
    private List<Room> rooms;

    public House() {
        this.owner = "John Doe";
        this.ownerNif = 0;
        this.rooms = new ArrayList<Room>();
    }

    public House(String owner, int nif, List<Room> rooms) {
        this.owner = owner;
        this.ownerNif = nif;
        this.rooms = new ArrayList<>(rooms);
    }

}
