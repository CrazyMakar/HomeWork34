package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student readStudent(Long id);
    Student editStudent(Long id, Student student);
    Student deleteStudent(Long id);

    List<Student> filterByAge(int age);
}