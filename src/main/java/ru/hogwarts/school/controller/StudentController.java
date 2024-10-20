package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @GetMapping("{id}")
    public Student readStudent(@PathVariable long id){
        return studentService.readStudent(id);
    }
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student student){
        return studentService.editStudent(id,student);
    }
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

}