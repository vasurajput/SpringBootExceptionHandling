/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SpringBootExceptionHandling.Controller;

import com.vasu.SpringBootExceptionHandling.DAO.StudentDAO;
import com.vasu.SpringBootExceptionHandling.Exception.StudentNotFoundException;
import com.vasu.SpringBootExceptionHandling.Model.Student;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author rv
 */
@RestController
public class MyController {

    @Autowired
    private StudentDAO dao;

    @GetMapping("/")
    public String test() {
        return "Nice Try";
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() throws IOException {
        
        int[] arr = {2};
        System.out.println("arr= " + arr[3]);
        return dao.findAll();
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = dao.findById(id);

        if (!student.isPresent()) {
            throw new StudentNotFoundException("id Not Found-" + id);
        }

        Student one = dao.getOne(id);
        System.out.println("data= " + one);
        return one;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        dao.deleteById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = dao.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = dao.findById(id);

        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);

        dao.save(student);

        return ResponseEntity.noContent().build();
    }
}
