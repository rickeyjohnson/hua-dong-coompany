import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class
class Main extends JFrame {
    private JTextField firstNameField, lastNameField, ssnField;
    private JComboBox<String> roleBox;
    private JTextArea displayArea;
    private JButton addButton, displayButton;
    private ArrayList<Employee> employees;

    public Main() {
        setTitle("Employee Payment Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        employees = new ArrayList<>();

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("SSN:"));
        ssnField = new JTextField();
        inputPanel.add(ssnField);

        // Removed base pay field as salaries are fixed per role

        inputPanel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{
                "Intern", "Developer", "Junior Developer", "Senior Developer"
        });
        inputPanel.add(roleBox);

        addButton = new JButton("Add Employee");
        inputPanel.add(addButton);

        displayButton = new JButton("Display Annual Payment Info");
        inputPanel.add(displayButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add employee button
        addButton.addActionListener(e -> addEmployee());

        // Display all employees
        displayButton.addActionListener(e -> displayAllEmployees());
    }

    private void addEmployee() {
        try {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String ssn = ssnField.getText().trim();
            String role = (String) roleBox.getSelectedItem();
            if (firstName.isEmpty() || lastName.isEmpty() || ssn.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "All fields must be filled.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Employee emp;
            int ssnNum = Integer.parseInt(ssn);
            
            switch (role) {
                case "Senior Developer":
                    emp = new SeniorDeveloper(lastName, firstName, ssnNum);
                    break;
                case "Junior Developer":
                    emp = new JuniorDeveloper(lastName, firstName, ssnNum);
                    break;
                case "Developer":
                    emp = new NewDeveloper(lastName, firstName, ssnNum);
                    break;
                default:
                    emp = new Employee(lastName, firstName, ssnNum);
            }
            
            employees.add(emp);
            JOptionPane.showMessageDialog(this,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Clear inputs
            firstNameField.setText("");
            lastNameField.setText("");
            ssnField.setText("");
            roleBox.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Base pay must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllEmployees() {
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No employees added yet.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Annual Payment Information ===\n\n");

        for (Employee emp : employees) {
            String last4 = String.valueOf(emp.social_security_number % 10000);
            sb.append("Name: ").append(emp.first_name).append(" ").append(emp.last_name).append("\n");
            sb.append("SSN (Last 4): ").append(last4).append("\n");
            
            if (emp instanceof SeniorDeveloper) {
                SeniorDeveloper senior = (SeniorDeveloper) emp;
                sb.append("Role: Senior Developer\n");
                sb.append(String.format("Annual Salary: $%.2f\n", senior.salary));
                senior.receiveBonus(); // This will add both bonus and stock options
                sb.append(String.format("Salary after 1%% Bonus: $%.2f\n", senior.salary));
                sb.append(String.format("Stock Options: %d shares\n", senior.stockOptions));
                sb.append(String.format("Monthly Payment: $%.2f\n", senior.monthlyPayment));
            } else if (emp instanceof JuniorDeveloper) {
                JuniorDeveloper junior = (JuniorDeveloper) emp;
                sb.append("Role: Junior Developer\n");
                sb.append(String.format("Annual Salary: $%.2f\n", junior.salary));
                junior.receiveBonus(); // This will add the 1% bonus
                sb.append(String.format("Salary after 1%% Bonus: $%.2f\n", junior.salary));
                sb.append(String.format("Monthly Payment: $%.2f\n", junior.monthlyPayment));
            } else if (emp instanceof NewDeveloper) {
                NewDeveloper developer = (NewDeveloper) emp;
                sb.append("Role: Developer\n");
                sb.append(String.format("Annual Salary: $%.2f\n", developer.salary));
                developer.receiveBonus(); // This will add the fixed bonus
                sb.append(String.format("Salary after $5000 Bonus: $%.2f\n", developer.salary));
                sb.append(String.format("Monthly Payment: $%.2f\n", developer.monthlyPayment));
            } else {
                sb.append("Role: Employee\n");
                sb.append("Base Employee - No salary information available\n");
            }
            
            sb.append("------------------------------\n\n");
        }

        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}