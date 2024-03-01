package Project;
import Utilities.CreateTable;
import Utilities.SearchTable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 * This class is responsible for viewing the doctors.
 * It is only accessible by the admin.
 * It extends the JFrame class.
 */
public class ViewDoctors extends JFrame{

    public JTable table1;
    private JButton addDoctorButton;
    private JButton deleteDoctorButton;
    private JButton modifyDoctorInformationButton;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel ViewDoctorsPanel;
    public JButton searchButton;

    public JTextField textField1;
    private JButton backButton;

    /**
     * This constructor calls the parent constructor of the JFrame class.
     * It also sets the title of the frame, sets the content pane, sets the default close operation, packs the frame and sets it to be visible.
     * It also adds action listeners to the search button, add doctor button, delete doctor button and modify doctor information button.
     */
    public ViewDoctors(JFrame parent){
        this.setTitle("HealthTrack");
        this.setContentPane(ViewDoctorsPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new CreateTable().CreateTableDoctor(table1);
        /**
         * This action listener is responsible for searching for a doctor.
         * It first checks if the id field is filled.
         * If it is, it tries to connect to the database and search for the doctor.
         * If it succeeds, it displays the doctor's information in the table.
         * If it fails, it displays an error message.
         */
        searchButton.addActionListener(e -> new SearchTable().searchTableDoctor(table1, textField1.getText()));

        addDoctorButton.addActionListener(e -> {
            new AddDoctor(this).setVisible(true);
            this.dispose();
        });

        deleteDoctorButton.addActionListener(e -> {
            new DeleteDoctor(this).setVisible(true);
            this.dispose();
        });

        modifyDoctorInformationButton.addActionListener(e -> {
            new ModifyDoctor(this).setVisible(true);
            this.dispose();
        });

        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
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
                    new CreateTable().CreateTableDoctor(table1);
                }
            }
        });

        textField1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(textField1.getText().equals("Enter Doctor ID"))
                    textField1.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(textField1.getText().isEmpty())
                    textField1.setText("Enter Doctor ID");
            }
        });
    }

}
