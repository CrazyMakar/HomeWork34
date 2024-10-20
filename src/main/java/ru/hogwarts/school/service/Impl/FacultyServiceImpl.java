package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public Faculty readFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        return facultyRepository.findById(id).map(facultyFromBd -> {
            facultyFromBd.setName(faculty.getName());
            facultyFromBd.setColor(faculty.getColor());
            facultyRepository.save(facultyFromBd);
            return facultyFromBd;
        }).orElse(null);
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return facultyRepository.findById(id).map(faculty -> {
            facultyRepository.deleteById(id);
            return faculty;
        }).orElse(null);
    }

    @Override
    public List<Faculty> filterByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
}