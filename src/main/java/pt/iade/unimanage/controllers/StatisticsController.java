package pt.iade.unimanage.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanage.models.GroupResult;
import pt.iade.unimanage.models.HistogramSlot;
import pt.iade.unimanage.models.Statistical;
import pt.iade.unimanage.models.StatisticsResult;
import pt.iade.unimanage.models.StudentRepository;
import pt.iade.unimanage.models.TeacherRepository;
import pt.iade.unimanage.models.UnitRepository;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path="/api/statistics")

public class StatisticsController {
    private Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    private static final int NHISTSLOTS = 5;

    @GetMapping(path = "/histogram/{type}/{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public HistogramSlot[] getHistogram( 
        @PathVariable("type") String type, @PathVariable("number") int number){
            logger.info("Obtaining histogram info");
            Statistical stats;
            if (type.equalsIgnoreCase("student"))
            stats = StudentRepository.getStudentNumber(number);
            else stats = UnitRepository.getUnit(number);
            return stats.getHistogram(NHISTSLOTS);
        }

    @GetMapping(path = "/{type}/{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public StatisticsResult getStatistics( 
         @PathVariable("type") String type, @PathVariable("number") int number){
            logger.info("Obtaining statistics");
            Statistical stats;
            if (type.equalsIgnoreCase("student"))
            stats = StudentRepository.getStudentNumber(number);
            else stats = UnitRepository.getUnit(number);
            return new StatisticsResult(stats.getAverage(), stats.getMax(), stats.getMin(), stats.getRange());
        }

    @GetMapping(path = "/group/average/{type}/{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    public GroupResult getGroupStatistics( 
        @PathVariable("type") String type, @PathVariable("number") int number, @PathVariable("id") int id){
            logger.info("Obtaining group average");
            ArrayList<Statistical> stats = new ArrayList<>();

            if (type.equalsIgnoreCase("teacher"))
            stats.addAll(TeacherRepository.getMecNumber(number).getUnits());
            else stats.addAll(UnitRepository.getUnit(id).getStudents());
            return new GroupResult(stats, stats.getAverage(), stats.size());
    }
}

