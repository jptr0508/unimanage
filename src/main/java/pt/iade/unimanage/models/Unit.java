package pt.iade.unimanage.models;

import java.util.ArrayList;

public class Unit {
    private int id;
    private String name;
    private int credits;
    private Teacher teacher;
    private ArrayList<Student> students;
    private ArrayList<Enrolment> enrolments;
    public Unit(int id, String name, int credits, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        students = new ArrayList<Student>();
        enrolments = new ArrayList<Enrolment>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getCredits() {
        return credits;
    }
    public Teacher getTeacher(){
        return teacher;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }
}
