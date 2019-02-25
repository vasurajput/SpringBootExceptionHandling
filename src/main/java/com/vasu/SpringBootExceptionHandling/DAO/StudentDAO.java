/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SpringBootExceptionHandling.DAO;

import com.vasu.SpringBootExceptionHandling.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rv
 */
@Repository
public interface StudentDAO extends JpaRepository<Student, Long> {

}
