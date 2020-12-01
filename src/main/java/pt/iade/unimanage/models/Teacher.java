package pt.iade.unimanage.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Teacher extends Person {
    private static int nextNumber = 1;
    private int mecNumber;
    private ArrayList<Unit> units;

    public Teacher(String name, char gender, LocalDate birthDay){
        super(name, gender, birthDay);
        this.mecNumber = nextNumber;
        nextNumber++;
        units = new ArrayList<>();
    }

    public int getMecNumber(){
        return mecNumber;
    }

    public ArrayList<Unit> getUnits(){
        return units;
    }

    @Override
    public String getReference() {
        return "T<"+mecNumber+">";
    }

    
}
