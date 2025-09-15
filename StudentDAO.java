import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection conn;

    public StudentDAO() {
        conn = DBconnection.getConnection();
    }

    // ✅ Add Student
    public void addStudent(Student s) {
        String sql = "INSERT INTO students(name, roll_no, department, email, phone, marks) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getDepartment());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setInt(6, s.getMarks());
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ View All Students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getInt("marks"));
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // ✅ Update Student (by Roll No)
    public void updateStudent(String rollNo, Student s) {
        String sql = "UPDATE students SET name=?, department=?, email=?, phone=?, marks=? WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getPhone());
            ps.setInt(5, s.getMarks());
            ps.setString(6, rollNo);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student with Roll No " + rollNo + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete Student (by Roll No)
    public void deleteStudent(String rollNo) {
        String sql = "DELETE FROM students WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student with Roll No " + rollNo + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Search Student by Roll No
    public Student searchByRollNo(String rollNo) {
        String sql = "SELECT * FROM students WHERE roll_no=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Search Students by Department
    public List<Student> searchByDepartment(String dept) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE department=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dept);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getInt("marks"));
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // ✅ Search Students by Marks Range
    public List<Student> searchByMarksRange(int min, int max) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE marks BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getInt("marks"));
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // ✅ Statistics - total students
    public int getTotalStudents() {
        String sql = "SELECT COUNT(*) AS total FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ✅ Statistics - Highest marks
    public int getHighestMarks() {
        String sql = "SELECT MAX(marks) AS highest FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("highest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ✅ Statistics - Lowest marks
    public int getLowestMarks() {
        String sql = "SELECT MIN(marks) AS lowest FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("lowest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


