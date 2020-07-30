package dia10ejercicio;

import java.util.*;

public class School {
    private final Map<Integer, Student> studentList;
    private final Map<Integer, Course> courseList;
    School(){
        studentList = new HashMap<>();
        courseList = new HashMap<>();
    }
    public void addCourse(int id, String name, String teacher) {
        Course course = new Course(id, name, teacher);
        courseList.put(id, course);
    }

    public Student add(int id, String name) {
        Student student = new Student(id, name);
        studentList.put(id,student);
        return student;
    }

    public Collection<Student> getStudentsByCourse(int courseId) {
        List<Student> studentByCourseList= new ArrayList<>();
        for (Map.Entry<Integer, Student> student : studentList.entrySet()) {
            if (student.getValue().getCourses().contains(courseId)){
                studentByCourseList.add(student.getValue());
            }
        }
        return studentByCourseList;
    }

    public Collection<Course> getCoursesByStudent(int studentId) {
        List<Course> coursesByStudentList = new ArrayList<>();
        List<Integer> studentCourses = studentList.get(studentId).getCourses();
        for (int courseId: studentCourses){
            coursesByStudentList.add(courseList.get(courseId));
        }
        return coursesByStudentList;
    }

    public Collection<Course> getCoursesByStudent(String name) {
        Collection<Course> studentCoursesList=new ArrayList<>();
        for (Map.Entry<Integer, Student> student : studentList.entrySet()) {
            if(student.getValue().getNombre().equals(name)){
                return getCoursesByStudent(student.getValue().getId());
            }
        }
        return studentCoursesList;

    }

    public Collection<Student> getStudentsByTeacher(String name) {
        List<Student> studentByTeacherList= new ArrayList<>();
        for(Map.Entry<Integer, Student> student : studentList.entrySet()){
            for (Course course:getCoursesByStudent(student.getValue().getId())){
                if(course.getTeacher().getName().equals(name)){
                    studentByTeacherList.add(student.getValue());
                }
            }
        }
        return studentByTeacherList;

    }

    public Collection<Teacher> getAllTeachers() {
        List<Teacher> teacherList= new ArrayList<>();
        for (Map.Entry<Integer, Course> course : courseList.entrySet()){
            teacherList.add(course.getValue().getTeacher());
        }
        return teacherList;
    }

}
