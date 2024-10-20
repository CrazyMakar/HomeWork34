package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;


@RestController
@RequestMapping("faculties")

public class FacultyController {
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    private final FacultyService facultyService;



    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty readFaculty(@PathVariable long id) {
        return facultyService.readFaculty(id);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }
}