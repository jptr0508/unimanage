package pt.iade.unimanage.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pt.iade.unimanage.models.Enrolment;
import pt.iade.unimanage.models.Student;
import pt.iade.unimanage.models.StudentRepository;
import pt.iade.unimanage.models.Unit;
import pt.iade.unimanage.models.UnitRepository;

@RestController
@RequestMapping(path="/api/students")

public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping(path = "", produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Student> getStudents() {
        logger.info("Sending all students");
        return StudentRepository.getStudents();
    }

    @GetMapping(path = "{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("number") int number) throws NotFoundException {
        logger.info("Sending student with the number " + number);
        Student student = StudentRepository.getStudentNumber(number);

        if(student != null)
        return student;

        else 

        throw new NotFoundException("" + number);
    }
    
    @DeleteMapping(path = "{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Response deleteStudent(@PathVariable("number") int number) {
        logger.info("deleting student with number " + number);
        if (StudentRepository.deleteStudent(number))
        return new Response(number + " was deleted.",null);
        else
        return new Response(number + " not found.",null);
 }
    
    @PostMapping(path = "", produces= MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@RequestBody Student student) {
        return student;      
    }

    public class Response { 

        private String message; 
        private Object object; 

        public Response(String message, Object object) { 
            this.message = message; 
            this.object = object; 
        } 
        
        public String getMessage() { 
            return message; 
        } 
        
        public Object getObject() { 
            return object; 
        } 
    }

    @GetMapping(path = "{number}/enrolments", 
    produces= MediaType.APPLICATION_JSON_VALUE) 
    public ArrayList<Enrolment> getEnrolments(@PathVariable("number") int number) throws NotFoundException{ 
        logger.info("Sending enrolments of student with number "+number); 
        Student student = StudentRepository.getStudentNumber(number); 
        
        if (student != null) 
        return student.getEnrolments(); 
        
        else 
        
        throw new NotFoundException("" + number); 
    }

    @GetMapping(path = "{number}/enrolments/{unitId}", 
    produces= MediaType.APPLICATION_JSON_VALUE) 
    public Enrolment getEnrolment(@PathVariable("number") int number, @PathVariable("unitId") int unitId) throws NotFoundException { 
        logger.info("Sending enrolment with id "+unitId+ " of student with number "+number);
        Student student = StudentRepository.getStudentNumber(number); 
        
        if (student != null) { 
            Enrolment enr = student.getEnrolmentByUnitId(unitId); 
            
            if (enr != null) 
            return enr; 
            
            else 
            
            throw new NotFoundException("" + unitId); 
        } 
        
        else 
        
        throw new NotFoundException("" + number); 
    }

    @PutMapping(path = "{number}/enrolments/{unitId}", 
    produces= MediaType.APPLICATION_JSON_VALUE) public Enrolment setGrade(@PathVariable("number") int number, @PathVariable("unitId") int unitId, @RequestBody double grade) throws NotFoundException { 
        logger.info("Setting grade of enrolment with id " + unitId + " of student with number " + number); 
        Student student = StudentRepository.getStudentNumber(number); 
        
        if (student != null) { 
            Enrolment enr = student.getEnrolmentByUnitId(unitId); 
            
            if (enr != null) {

                enr.setGrade(grade); 
                return enr; 
            } 
        
            else 
            
            throw new NotFoundException("" + unitId); 
        }
        
        else 
        
        throw new NotFoundException("" + number); 
    }

    @PostMapping(path = "{number}/enrolments", 
    produces= MediaType.APPLICATION_JSON_VALUE) public Enrolment addEnrolment(@PathVariable("number") int number, @RequestBody int unitId) throws NotFoundException, AlreadyExistsException {
        logger.info("Enroling student with number " + number + " in unit with id " + unitId);
        Student student = StudentRepository.getStudentNumber(number); 
        
        if (student != null) { 
            Unit unit = UnitRepository.getUnit(unitId); 
            
            if (unit != null) { 
                
                if (student.getEnrolmentByUnitId(unitId)!=null) 
                throw new Exception("" + unitId);
            else {
                Enrolment enrolment = new Enrolment(student, unit, -1);
                student.enroll(enrolment);
                return enrolment;
            }
        } else
            throw new NotFoundException("" + unitId);
    } else
        throw new NotFoundException("" + number);
}
}