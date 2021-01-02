package pt.iade.unimanage.models;

import java.util.ArrayList;

public class Computer extends Material{
    private static ArrayList<String> specifications = new ArrayList<String>();

    public Computer(String name){
        super(name);
    }

    public ArrayList<String> getSpecifications(){
        return specifications;
    }
}
