package pt.iade.unimanage.models;

import java.util.ArrayList;

public class Unit implements Statistical{
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

    @Override
    public double getAverage(){
        double sum = 0; int n = 0;
        for(Enrolment enr: enrolments)
        if(enr.getGrade() > 0){
            n++;
            sum += enr.getGrade();
        }
        return sum / n;
    }

    @Override
    public double getMax(){
        return 0;
    }

    @Override
    public double getMin(){
        return 0;
    }

    @Override
    public HistogramSlot[] getHistogram(int nSlots) {
        return null;
    }
}
