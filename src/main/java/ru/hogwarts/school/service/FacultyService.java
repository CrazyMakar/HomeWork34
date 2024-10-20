package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);
    Faculty readFaculty(Long id);
    Faculty editFaculty(Long id, Faculty faculty);
    Faculty deleteFaculty(Long id);

    List<Faculty> filterByColor(String color);
}