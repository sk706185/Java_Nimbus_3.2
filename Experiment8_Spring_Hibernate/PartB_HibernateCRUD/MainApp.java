import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Create
        dao.saveStudent(new Student("Alice", "CSE"));
        dao.saveStudent(new Student("Bob", "ECE"));

        // Read
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.println(s);
        }

        // Update
        dao.updateStudent(1, "Alice Updated");

        // Delete
        dao.deleteStudent(2);
    }
}
