package Project;
import Utilities.CreateTable;
import Utilities.SearchTable;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * This class is used to display the doctor menu.
 * It is only accessible by the doctor.
 * It contains buttons that redirect to other pages.
 * It contains a table that displays the appointments and patients.
 * It contains a search bar that searches for appointments and patients.
 */
public class DoctorMenu extends JFrame{
    private JPanel DoctorPanel;
    private JButton deletePatientButton;
    private JButton modifyPatientInformationButton;
    private JButton modifyAppointmentButton;
    private JButton addPatientButton;
    private JButton createAppointmentButton;
    private JButton deleteAppointmentButton;
    public JTable table1;
    public JTable table2;
    public JButton button1;
    public JTextField textField1;
    public JButton button2;
    public JTextField textField2;
    private JPanel Panel2;
    private JPanel Panel1;
    private JPanel Panel3;
    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public DoctorMenu(){
        this.setTitle("HealthTrack");
        this.setContentPane(DoctorPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new CreateTable().CreateTableAppointment(table1);
        new CreateTable().CreateTablePatient(table2);

        addPatientButton.addActionListener(e -> {
            new AddPatient(this).setVisible(true);
            this.dispose();
        });

        deletePatientButton.addActionListener(e -> {
            new DeletePatient(this).setVisible(true);
            this.dispose();
        });

        modifyPatientInformationButton.addActionListener(e -> {
            new ModifyPatientInformation(this).setVisible(true);
            this.dispose();
        });

        createAppointmentButton.addActionListener(e -> {
            new CreateAppointment(this).setVisible(true);
            this.dispose();
        });

        modifyAppointmentButton.addActionListener(e -> {
            new ModifyAppointment(this).setVisible(true);
            this.dispose();
        });

        deleteAppointmentButton.addActionListener(e -> {
            new DeleteAppointment(this).setVisible(true);
            this.dispose();
        });
        /**
         * This action listener is used to search for a patient.
         * It checks if the ID field is filled or not.
         * If the ID field is filled, it searches for the patient.
         * Else, it shows a message dialog to fill the ID field.
         */
        button2.addActionListener(e -> new SearchTable().searchTablePatient(table2, textField2.getText()));
        /**
         * This action listener is used to search for an appointment.
         * It checks if the ID field is filled or not.
         * If the ID field is filled, it searches for the appointment.
         * Else, it shows a message dialog to fill the ID field.
         */
        button1.addActionListener(e -> new SearchTable().searchTableAppointment(table1, textField1.getText()));

        textField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {}

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}

            private void checkTextField() {
                String id = textField2.getText();
                if (id.isEmpty()) {
                    new CreateTable().CreateTablePatient(table2);
                }
            }
        });

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {}

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}

            private void checkTextField() {
                String id = textField1.getText();
                if (id.isEmpty()) {
                    new CreateTable().CreateTableAppointment(table1);
                }
            }
        });

        textField2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(textField2.getText().equals("Enter Patient ID"))
                    textField2.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(textField2.getText().isEmpty())
                    textField2.setText("Enter Patient ID");
            }
        });

        textField1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(textField1.getText().equals("Enter Appointment ID"))
                    textField1.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(textField1.getText().isEmpty())
                    textField1.setText("Enter Appointment ID");
            }
        });
    }

    public static void main(String[] args) {
        new DoctorMenu();
    }

}