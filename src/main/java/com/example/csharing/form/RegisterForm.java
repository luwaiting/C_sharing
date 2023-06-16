package com.example.csharing.form;

//import com.example.csharing.domain.Student;
import com.example.csharing.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterForm {
    @Email
    private String email;
    @Length(min = 6, message = "密碼至少需要6位")
    private String password;
    //error set by myself
    @NotBlank
    private String ensurePassword;
    @NotBlank
    private String name;
    private int identity;
    private int sex;

    public boolean confirmPassword(){
        if(this.ensurePassword.equals(this.password)){
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnsurePassword() {
        return ensurePassword;
    }

    public void setEnsurePassword(String ensurePassword) {
        this.ensurePassword = ensurePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
    public User convert(){
        return new RegisterFormConverter().convertTo(this);
    }

    private class RegisterFormConverter implements FormConverter<RegisterForm, User>{
        public User convertTo(RegisterForm registerForm){
            User user=new User();
            BeanUtils.copyProperties(registerForm,user);
            return user;
        }
//        public Student convertToStudent(RegisterForm registerForm){
//            User user=new User();
//            Student student=new Student();
//            BeanUtils.copyProperties(registerForm,user);
//            BeanUtils.copyProperties(registerForm,student);
//            student.setUser(user);
//            return student;
//        }

    }
}
