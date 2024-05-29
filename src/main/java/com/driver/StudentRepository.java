package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
    	//Student savedStudent = new Student(student.getName(),student.getAge(), student.getAverageScore());
    	studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
    	teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
        	//update the number of students
        	Teacher savedTeacher =teacherMap.get(teacher);
        	savedTeacher.setNumberOfStudents(savedTeacher.getNumberOfStudents()+1);
        	teacherMap.put(teacher, savedTeacher);
        	List<String> studentList;
        	if(teacherStudentMapping.get(teacher)==null) {
        		 studentList = new ArrayList<>();
        		studentList.add(student);
        	}else {
        		 studentList = teacherStudentMapping.get(teacher);
        		studentList.add(student);
        	}
        	teacherStudentMapping.put(teacher,studentList);
        }
    }

    public Student findStudent(String student){
    	 // your code goes here
		Student obtainedStudent=null;
		if(studentMap.containsKey(student))
			obtainedStudent=studentMap.get(student);
       
		return obtainedStudent;
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
    	Teacher obtainedTeacher=null;
    	if(teacherMap.containsKey(teacher))
    		obtainedTeacher=teacherMap.get(teacher);
    	
    	return obtainedTeacher;
    }

    public List<String> findStudentsFromTeacher(String teacher){
		List<String> stundentsOfGivenTeacherList=null;
        // your code goes here
        // find student list corresponding to a teacher
		if(teacherStudentMapping.containsKey(teacher))
			stundentsOfGivenTeacherList=teacherStudentMapping.get(teacher);
		
		return stundentsOfGivenTeacherList;
		
    }

    public List<String> findAllStudents(){
    	
    	Set<String> studentSet = studentMap.keySet();
    	List<String> studentList = new ArrayList<>(studentSet);
		return studentList;
        // your code goes here
    }

    public void deleteTeacher(String teacher){
        // your code goes here
    	//--delete from teacher map
    	if(teacherMap.containsKey(teacher))
    		teacherMap.remove(teacher);
    	//--delete from teacherStudentMap
    	Set<String> teacherKeys = teacherStudentMapping.keySet();
    	Iterator<String> iterator = teacherKeys.iterator();
    	while(iterator.hasNext()) {
    		String getTeacher = iterator.next();
    		if(getTeacher.equals(teacher))
    			iterator.remove();
    	}
    }

    public void deleteAllTeachers(){
        // your code goes here
    	teacherMap.clear();
    	teacherStudentMapping.clear();
    }
}