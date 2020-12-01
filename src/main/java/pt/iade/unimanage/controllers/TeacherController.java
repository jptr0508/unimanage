package pt.iade.unimanage.controllers;

import java.util.ArrayList;

import com.azure.core.http.rest.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pt.iade.unimanage.models.Teacher;
import pt.iade.unimanage.models.TeacherRepository;

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

    @DeleteMapping(path = "{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Response deleteStudent(@PathVariable("number") int number) {
        logger.info("deleting student with number " + number);
        if (TeacherRepository.deleteUnit(number))
        return new Response(number + " was deleted.",null);
        else
        return new Response(number + " not found.",null);
    }
}
