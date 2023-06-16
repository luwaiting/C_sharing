package com.example.csharing.service;
//import com.example.csharing.dao.StudentRepository;
//import com.example.csharing.domain.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;

//@Service
//public class StudentService {
//    @Autowired
//    StudentRepository studentRepository;
//    @Transactional
//    public Student findStudentById(Long id){
//        return studentRepository.findById(id).orElse(null);
//    }
//    @Transactional
//    public Student saveStudent(Student student){
//        return studentRepository.save(student);
//    }
//    @Transactional
//    public Student updateStudent(Student student){
//        return studentRepository.save(student);
//    }
//    @Transactional
//    public void deleteStudentById(Long id){
//        studentRepository.deleteById(id);
//    }
//
//}
