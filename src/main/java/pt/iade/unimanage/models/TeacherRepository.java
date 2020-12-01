package pt.iade.unimanage.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TeacherRepository {
    private static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private static ArrayList<Unit> units = new ArrayList<Unit>();

    public static void populate() {
        Teacher t; // auxiliary variable

        t = new Teacher("Tom", 'M', LocalDate.parse("1980-11-30"));
        teachers.add(t);
        teachers.add(new Teacher("Ella", 'F', LocalDate.parse("1975-12-23")));
        t = new Teacher("Timo", 'M', LocalDate.parse("1977-07-02"));
        teachers.add(t);
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public static Teacher getMecNumber(int mecNumber) {
        for (Teacher teacher : teachers)
            if (teacher.getMecNumber() == mecNumber)
                return teacher;
        return null;
    }

    public static List<Unit> getTeacherUnits() {return units;}

    //public static boolean deleteUnit() {}

    //public static Unit addUnit(Unit unit) {}
}
