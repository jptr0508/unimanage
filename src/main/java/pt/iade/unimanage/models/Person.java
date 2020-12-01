package pt.iade.unimanage.models;

import java.time.LocalDate;

public abstract class Person {
    protected String name;
    protected String email;
    protected char gender;
    protected LocalDate birthDay;

    public Person(String name, char gender, LocalDate birthDay){
        this.name = name;
        this.email = "";
        this.gender = gender;
        this.birthDay = birthDay;
    }
    
    public abstract String getReference(); 
}
