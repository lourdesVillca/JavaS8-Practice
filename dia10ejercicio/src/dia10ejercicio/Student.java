package dia10ejercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student {
    private int id;
    private String nombre;
    private List<Integer> courses;
    Student(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
        courses=new ArrayList<>();
    }
    public void addCourses(int... courseIds) {
        for (int courseId : courseIds) {
            courses.add(courseId);
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", courses=" + courses +
                '}';
    }
}
