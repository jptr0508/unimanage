package pt.iade.unimanage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pt.iade.unimanage.models.Teacher;
import pt.iade.unimanage.models.TeacherRepository;
import pt.iade.unimanage.models.Unit;
import pt.iade.unimanage.models.UnitRepository;

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path="/api/teachers")
public class TeacherController {
    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping(path = "", produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Teacher> getTeachers() {
        logger.info("Sending all teachers");
        return TeacherRepository.getTeachers();
    }

    @GetMapping(path = "{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Teacher getTeachers(@PathVariable("number") int mecNumber) throws NotFoundException {
        logger.info("Sending teacher with the number " + mecNumber);
        Teacher teacher = TeacherRepository.getMecNumber(mecNumber);

        if(teacher != null)
        return teacher;

        else 

        throw new NotFoundException("" + mecNumber);
    }

    @GetMapping(path = "{mecNumber}/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Unit> getUnits(@PathVariable("mecNumber") int mecNumber) throws NotFoundException {
        logger.info("Sending units of teacher with mecNumber " + mecNumber);
        Teacher teacher = TeacherRepository.getMecNumber(mecNumber);

        if (teacher != null)
        return teacher.getUnits();

        else
        
        throw new NotFoundException("" + mecNumber);
    }
    
    @DeleteMapping(path = "{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void unassignTeacher(@PathVariable("unitId") int unitId) {
        logger.info("unassigning teacher from unit " + unitId);
        UnitRepository.unassignTeacher(unitId);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public void assignTeacher(@RequestBody Teacher teacher, @RequestBody Unit unit) {
        logger.info("assigning teacher " + teacher.getMecNumber() + " to unit " + unit.getId());
        teacher.assignUnit(unit);
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
}
