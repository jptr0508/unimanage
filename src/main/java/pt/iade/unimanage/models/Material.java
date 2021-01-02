package pt.iade.unimanage.models;

public class Material {
    private String name;
    private MaterialState state;

    public Material(String name){
        this.name = name;
        state = MaterialState.OK;
    }

    public String getName(){
        return name;
    }

    public MaterialState getState(){
        return state;
    }

    public void setState(MaterialState state){
        this.state = state;
    }
}