package ru.hogwarts.school.service.Impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student readStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student editStudent(Long id, Student student) {
        return studentRepository.findById(id).map(studenFromBd -> {
            studenFromBd.setName(student.getName());
            studenFromBd.setAge(student.getAge());
            studentRepository.save(studenFromBd);
            return studenFromBd;
        }).orElse(null);
    }

    @Override
    public Student deleteStudent(Long id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.deleteById(id);
            return student;
        }).orElse(null);
    }

    @Override
    public List<Student> filterByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
}