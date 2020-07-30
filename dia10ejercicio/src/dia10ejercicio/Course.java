package dia10ejercicio;

public class Course {
    private final int id;
    private final String name;
    private final Teacher teacher;

    Course(int id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = new Teacher(teacher);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
