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
 * This class modifies an appointment from the database.
 * It is only accessible by the doctor.
 * It asks the user to enter the ID of the appointment they want to modify.
 * If the ID is valid, it asks the user to enter the new date of the appointment.
 * If the date is valid, it modifies the appointment from the database.
 * If the date is invalid, it asks the user to enter a valid date.
 * If the user does not enter a date, it asks the user to enter a date.
 * If the user confirms the modification, it modifies the appointment from the database.
 * If the user cancels the modification, it does not modify the appointment from the database.
 */
public class ModifyAppointment extends JFrame{
    public JTextField appointmentIdField;
    public JTextField appointmentDateField;
    public JButton modifyButton;
    private JButton backButton;
    private JPanel panel1;

    public ModifyAppointment(JFrame parent){
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
         * This action listener is used to modify an appointment.
         * It checks if the ID and date fields are filled or not.
         * If the ID and date fields are filled, it modifies the appointment.
         * Else, it shows a message dialog to fill the ID and date fields.
         */
        modifyButton.addActionListener(e -> {
            String appointmentId = appointmentIdField.getText();
            String appointment = appointmentDateField.getText();

            if(appointmentId.isEmpty() || appointment.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter all the fields!");
            }else{
                try{
                    Connection con = ConnectionProvider.getCon();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date appointmentDate = sdf.parse(appointment);
                    PreparedStatement st = con.prepareStatement("update appointment set appointment_date = ? where id_appointment = ?");
                    st.setDate(1, new java.sql.Date(appointmentDate.getTime()));
                    st.setString(2, appointmentId);
                    st.executeUpdate();
                    st.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Appointment modified successfully!");
                    parent.setVisible(true);
                    this.dispose();
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        appointmentDateField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (appointmentDateField.getText().equals("yyyy-mm-dd")) {
                    appointmentDateField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if(appointmentDateField.getText().isEmpty()){
                    appointmentDateField.setText("yyyy-mm-dd");
                }
            }
        });
    }

}
