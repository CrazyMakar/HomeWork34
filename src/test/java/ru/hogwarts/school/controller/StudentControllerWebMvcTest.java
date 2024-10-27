package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultActionsDsl;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.Impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private FacultyRepository facultyRepository;


    @Test
    void shouldCreateStudent() throws Exception {

        Long studentId = 1L;
        Student student = new Student(20, "Ivan");
        Student savedStudent = new Student(20, "Ivan");
        savedStudent.setId(studentId);
        when(studentService.addStudent(student)).thenReturn(savedStudent);

        ResultActions perform = mockMvc.perform(post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
        perform
                .andExpect(jsonPath("$.id").value(savedStudent.getId()))
                .andExpect(jsonPath("$.name").value(savedStudent.getName()))
                .andExpect(jsonPath("$.age").value(savedStudent.getAge()))
                .andDo(print());
    }

    @Test
    void shouldUpdateStudent() throws Exception {

        Student student = new Student(20, "Ivan");
        when(studentService.editStudent(student)).thenReturn(student);

        ResultActions perform = mockMvc.perform(put("/student/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student(20, "Ivan");
        ResultActions perform = mockMvc.perform(delete("/student/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));
        perform
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetStudentByAge() throws Exception {
        Collection<Student> students = new ArrayList<>();
        Student student1 = new Student(12, "Вася");
        Student student2 = new Student(15, "Вася");
        when(studentService.findByAgeBetween(11, 16)).thenReturn(new ArrayList<>());
        ResultActions perform = mockMvc.perform(get("/student/filter?min=11&max=16"));
        perform
                .andExpect(content().json(objectMapper.writeValueAsString(students)));
    }
}
