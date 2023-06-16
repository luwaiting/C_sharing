package com.example.csharing.domain;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Entity
//@IdClass(Student.StudentId.class)
//public class Student {
//    @Id
//    @GeneratedValue
////    private Long student_id;
//    private Long id;
//    private int identity;
//
//    @Id
////    @GeneratedValue
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
////    @JoinColumn(name = "user_id")
//    private User user;
//
//    public Student() {
//    }
//
////    public Long getStudent_id() {
////        return student_id;
////    }
////
////    public void setStudent_id(Long student_id) {
////        this.student_id = student_id;
////    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getIdentity() {
//        return identity;
//    }
//
//    public void setIdentity(int identity) {
//        this.identity = identity;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
////        user.setStudent(this);
//        this.user = user;
//    }
//
//    //    @NoArgsConstructor
//    public static class StudentId implements Serializable {
////        private Long student_id;
//
//        private Long id;
//        //        private User user;
//        private Long user;
//
//
//        public StudentId() {
//        }
//
//        public Long getId() {
//            return id;
//        }
//
////    public void setId(Long id) {
////        this.id = id;
////    }
////        public Long getStudent_id() {
////        return student_id;
////    }
////
////    public void setStudent_id(Long student_id) {
////        this.student_id = student_id;
////    }
//
//
//        public Long getUser() {
//            return user;
//        }
//
//        public void setUser(Long user) {
//            this.user = user;
//
//        }
//
////    public User getUser() {
////        return user;
////    }
////
////    public void setUser(User user) {
////        this.user = user;
////    }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            StudentId studentId = (StudentId) o;
//            return Objects.equals(id, studentId.id) && Objects.equals(user, studentId.user);
//        }
//
////        @Override
////        public int hashCode() {
////            return Objects.hash(id, (user != null) ? user.getId() : 1);
////        }
//    }
//}
//
//
