package com.javainterviewpoint.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.javainterviewpoint.model.Student;

public class SpringRestTemplateClient
{
    public static final String REST_BASE_URI = "http://localhost:8070/SpringRestHibernateCRUDAndClientBhami99";
   
    static RestTemplate restTemplate = new RestTemplate();
    
    /**POST**/
    public static void createStudent()
    {
        Student student = new Student();
        student.setId(6);
        student.setName("Sami");
        student.setAge(45);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
      HttpEntity<Student> entity = new HttpEntity<>(student,headers);
        
        Student result = restTemplate.postForObject(REST_BASE_URI+"/create", entity, Student.class);
        System.out.println("name is :" +  student.getName() );
    }
    
    /**GET**/
    private static void getStudent(int id)
    {
        Student student = restTemplate.getForObject(REST_BASE_URI+"/student/"+id, Student.class);
        System.out.println("**** Student with id : "+id+"****");
        System.out.println("Id :"+student.getId()+"    Name : "+student.getName()+"   Age : "+student.getAge());
    }
    
    public static void getAllStudents()
    {
       

        List<Map<String, Object>> studentList = restTemplate.getForObject(REST_BASE_URI + "/students", List.class);
        if (studentList != null)
        {
            System.out.println("**** All Students ****");
            for (Map<String, Object> map : studentList)
            {
                System.out.println("Id : id=" + map.get("id") + "   Name=" + map.get("name") +  "   Age="  + map.get("age") );
            }
        } else
        {
            System.out.println("No Students exist!!");
        }
    }
    
    /**PUT**/
    public static void updateStudent()
    {
        Student student = new Student();
        student.setId(5);
        student.setName("Bhami 5 updated");
        student.setAge(55);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Student> entity = new HttpEntity<>(student,headers);
        
        restTemplate.put(REST_BASE_URI + "/update", entity,Student.class);
    }
    
    /**DELETE**/
    public static void deleteStudent(int id)
    {
        restTemplate.delete(REST_BASE_URI + "/delete/"+id);
    }
    public static void main(String args[])
    {
    	deleteStudent(6);
        createStudent();
        
        getAllStudents();
        getStudent(1);
        
        updateStudent();
        
        
    }
}
