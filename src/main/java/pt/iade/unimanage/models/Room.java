package pt.iade.unimanage.models;

public class Room {
    protected String designation;
    protected int capacity;

    public Room(String designation, int capacity){
        this.designation = designation;
        this.capacity = capacity;
    }

	public String getDesignation(){
        return designation;
    }

    public int getCapacity(){
        return capacity;
    }
}