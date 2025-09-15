
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private static StudentDAO dao = new StudentDAO();
    private static Scanner sc = new Scanner(System.in);

    // Show Admin Menu
    public static void showMenu() {
        int choice = -1;

        do {
            System.out.println("\n===== SmartStudent Admin Panel =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Search Students by Department");
            System.out.println("7. Search Students by Marks Range");
            System.out.println("8. Statistics");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudentUI();
                    break;
                case 2:
                    viewAllStudentsUI();
                    break;
                case 3:
                    updateStudentUI();
                    break;
                case 4:
                    deleteStudentUI();
                    break;
                case 5:
                    searchByRollNoUI();
                    break;
                case 6:
                    searchByDepartmentUI();
                    break;
                case 7:
                    searchByMarksRangeUI();
                    break;
                case 8:
                    showStatistics();
                    break;
                case 0:
                    System.out.println("Logging out... 👋");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);
    }

    // ------------------ CRUD Methods for UI ------------------

    private static void addStudentUI() {
        System.out.println("\n--- Add Student ---");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Roll No: ");
        String roll = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        Student s = new Student();
        s.setName(name);
        s.setRollNo(roll);
        s.setDepartment(dept);
        s.setEmail(email);
        s.setPhone(phone);
        s.setMarks(marks);

        dao.addStudent(s);
    }

    private static void viewAllStudentsUI() {
        System.out.println("\n--- All Students ---");
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void updateStudentUI() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Roll No of student to update: ");
        String roll = sc.nextLine();

        Student s = dao.searchByRollNo(roll);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("New Name (" + s.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) s.setName(name);

        System.out.print("New Department (" + s.getDepartment() + "): ");
        String dept = sc.nextLine();
        if (!dept.isEmpty()) s.setDepartment(dept);

        System.out.print("New Email (" + s.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) s.setEmail(email);

        System.out.print("New Phone (" + s.getPhone() + "): ");
        String phone = sc.nextLine();
        if (!phone.isEmpty()) s.setPhone(phone);

        System.out.print("New Marks (" + s.getMarks() + "): ");
        String marksInput = sc.nextLine();
        if (!marksInput.isEmpty()) s.setMarks(Integer.parseInt(marksInput));

        dao.updateStudent(roll, s);
    }

    private static void deleteStudentUI() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        dao.deleteStudent(roll);
    }

    private static void searchByRollNoUI() {
        System.out.println("\n--- Search Student by Roll No ---");
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        Student s = dao.searchByRollNo(roll);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchByDepartmentUI() {
        System.out.println("\n--- Search Students by Department ---");
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        List<Student> students = dao.searchByDepartment(dept);
        if (students.isEmpty()) {
            System.out.println("No students found in department " + dept);
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void searchByMarksRangeUI() {
        System.out.println("\n--- Search Students by Marks Range ---");
        System.out.print("Enter Minimum Marks: ");
        int min = sc.nextInt();
        System.out.print("Enter Maximum Marks: ");
        int max = sc.nextInt();
        sc.nextLine();

        List<Student> students = dao.searchByMarksRange(min, max);
        if (students.isEmpty()) {
            System.out.println("No students found in this marks range.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void showStatistics() {
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Students: " + dao.getTotalStudents());
        System.out.println("Highest Marks: " + dao.getHighestMarks());
        System.out.println("Lowest Marks: " + dao.getLowestMarks());
    }
}

