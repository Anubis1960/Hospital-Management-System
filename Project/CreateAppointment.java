package Project;
import Database.ConnectionProvider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class is used to create an appointment.
 * It extends JFrame and implements ActionListener.
 */
public class CreateAppointment extends JFrame{
    public JTextField idMedicField;
    public JTextField patientIdField;
    public JTextField procedureField;
    public JTextField issueField;
    public JTextField appointmentField;
    public JTextField medicationField;
    public JTextField diagnosticField;
    public JTextField priceField;
    public JButton createButton;
    private JButton backButton;
    private JPanel panel1;
    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public CreateAppointment(JFrame parent){
        this.setTitle("HealthTrack");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });
        /**
         * This action listener is used to create an appointment.
         * It checks if all the fields are filled or not.
         * If all the fields are filled, it creates the appointment.
         * Else, it shows a message dialog to fill all the fields.
         */
        createButton.addActionListener(e -> {
            String idMedic = idMedicField.getText();
            String patientId = patientIdField.getText();
            String procedure = procedureField.getText();
            String issue = issueField.getText();
            String appointment = appointmentField.getText();
            String medication = medicationField.getText();
            String diagnostic = diagnosticField.getText();
            String price = priceField.getText();
            System.out.println(issue);
            System.out.println(appointment);
            if(idMedic.isEmpty() || patientId.isEmpty() || issue.isEmpty() || price.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the required fields!");
            }
            else{
                try{
                    int intPrice = Integer.parseInt(priceField.getText());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date issueDate = sdf.parse(issue);
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement st = con.prepareStatement("insert into appointment(id_medic, id_patient, procedure_type, issue_date, appointment_date, medication, diagnostic, price) values(?, ? , ?, ?, ?, ?, ?, ?)");
                    st.setString(1, idMedic);
                    st.setString(2, patientId);

                    if(procedure.isEmpty())
                        st.setString(3, null);
                    else
                        st.setString(3, procedure);

                    st.setDate(4, new java.sql.Date(issueDate.getTime()));


                    if(appointment.equals("yyyy-mm-dd"))
                        st.setDate(5, null);
                    else {
                        Date appointmentDate = sdf.parse(appointment);
                        st.setDate(5, new java.sql.Date(appointmentDate.getTime()));
                    }

                    if(medication.isEmpty())
                        st.setString(6, null);
                    else
                        st.setString(6, medication);

                    if(diagnostic.isEmpty())
                        st.setString(7, null);
                    else
                        st.setString(7, diagnostic);

                    st.setInt(8, intPrice);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully added!");
                    st.close();
                    con.close();
                    this.dispose();
                    parent.setVisible(true);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter data in correct format!");
                }
            }
        });
        /**
         * This focus listener is used to set the text of the issue date field to "yyyy-mm-dd" when the field is not focused.
         * It also sets the text of the issue date field to "" when the field is focused.
         */
        issueField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                   if (issueField.getText().equals("yyyy-mm-dd")) {
                        issueField.setText("");
                    }
            }

            public void focusLost(FocusEvent e) {
                if (issueField.getText().isEmpty()) {
                    issueField.setText("yyyy-mm-dd");
                }
            }
        });
        /**
         * This focus listener is used to set the text of the appointment date field to "yyyy-mm-dd" when the field is not focused.
         * It also sets the text of the appointment date field to "" when the field is focused.
         */
        appointmentField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (appointmentField.getText().equals("yyyy-mm-dd")) {
                    appointmentField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (appointmentField.getText().isEmpty()) {
                    appointmentField.setText("yyyy-mm-dd");
                }
            }
        });
    }
}
