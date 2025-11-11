import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel displayPanel;
    private JTextField firstNameField, lastNameField, ssnField;
    private JTextArea displayArea;
    private ArrayList<Employee> employees;

    public Main() {
        setTitle("Employee Payment Lookup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        employees = new ArrayList<>();

        employees.add(new SeniorDeveloper("John", "Doe", 123456789));
        employees.add(new JuniorDeveloper("Jane", "Smith", 345678912));
        employees.add(new NewDeveloper("Chris", "Brown", 901256789));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createInputPanel();
        createDisplayPanel();

        mainPanel.add(inputPanel, "Input");
        mainPanel.add(displayPanel, "Display");

        add(mainPanel);
        cardLayout.show(mainPanel, "Input");
    }

    private void createInputPanel() {
        inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Full SSN (9 digits):"));
        ssnField = new JTextField();
        inputPanel.add(ssnField);

        JButton searchButton = new JButton("View Employee Info");
        inputPanel.add(new JLabel());
        inputPanel.add(searchButton);

        searchButton.addActionListener(e -> viewEmployeeInfo());
    }

    private void createDisplayPanel() {
        displayPanel = new JPanel(new BorderLayout(10, 10));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> {
            firstNameField.setText("");
            lastNameField.setText("");
            ssnField.setText("");
            cardLayout.show(mainPanel, "Input");
        });
        displayPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void viewEmployeeInfo() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String ssnInput = ssnField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || ssnInput.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter first name, last name, and full SSN.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ssnInput.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(this,
                    "SSN must be exactly 9 digits (numbers only).",
                    "Invalid SSN",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee found = null;
        for (Employee e : employees) {
            if (e.firstName.equalsIgnoreCase(firstName)
                    && e.lastName.equalsIgnoreCase(lastName)
                    && String.valueOf(e.ssn).equals(ssnInput)) {
                found = e;
                break;
            }
        }

        if (found == null) {
            JOptionPane.showMessageDialog(this,
                    "Employee not found. Check spelling or SSN.",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Display Employee Info
        StringBuilder sb = new StringBuilder();
        sb.append("=== Employee Information ===\n\n");
        sb.append("First Name: ").append(found.firstName).append("\n");
        sb.append("Last Name: ").append(found.lastName).append("\n");
        sb.append("SSN: ").append(found.ssn).append("\n");

        if (found instanceof SeniorDeveloper) {
            SeniorDeveloper s = (SeniorDeveloper) found;
            double baseAnnual = s.monthly_salary * 12;
            double onePercentBonus = baseAnnual * 0.01;

            sb.append("Role: Senior Developer\n\n");

            sb.append(String.format("Base Annual Salary: $%.2f\n", baseAnnual));
            sb.append(String.format("1%% Additional Payment: $%.2f\n", onePercentBonus));
            sb.append(String.format("Extra Bonus: $.2f", s.bonus));
            sb.append(String.format("Stock Options: %d shares\n", s.stockOptions));
            sb.append(String.format("Total Annual Payment: $%.2f\n", s.calculateAnnualPayment()));

        } else if (found instanceof JuniorDeveloper) {
            JuniorDeveloper j = (JuniorDeveloper) found;
            double baseAnnual = j.monthly_salary * 12;
            double onePercentBonus = baseAnnual * 0.01;

            sb.append("Role: Junior Developer\n\n");

            sb.append(String.format("Base Annual Salary: $%.2f\n", baseAnnual));
            sb.append(String.format("1%% Additional Payment: $%.2f\n", onePercentBonus));
            sb.append(String.format("Extra Bonus: $%.2f\n", j.bonus));
            sb.append(String.format("Total Annual Payment: $%.2f\n", j.calculateAnnualPayment()));

        } else if (found instanceof NewDeveloper) {
            NewDeveloper d = (NewDeveloper) found;
            double baseAnnual = d.monthly_salary * 12;

            sb.append("Role: New Developer\n\n");

            sb.append(String.format("Base Annual Salary: $%.2f\n", baseAnnual));
            sb.append(String.format("Extra Bonus: $.2f", d.bonus));
            sb.append(String.format("Total Annual Payment: $%.2f\n", d.calculateAnnualPayment()));

        } else if (found instanceof Employee) {
            Employee e = (Employee) found;

            sb.append("Role: Employee\n\n");

            sb.append("No additional payment information available.\n");
        }


        displayArea.setText(sb.toString());
        cardLayout.show(mainPanel, "Display");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
