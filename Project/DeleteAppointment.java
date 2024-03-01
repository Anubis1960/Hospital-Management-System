package Project;
import Database.ConnectionProvider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * This class is used to delete an appointment.
 * It extends JFrame and implements ActionListener.
 */
public class DeleteAppointment extends JFrame{
    public JTextField idField;
    public JButton deleteButton;
    private JButton backButton;
    private JPanel panel1;
    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public DeleteAppointment(JFrame parent){
        this.setTitle("HealthTrack");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        /**
         * This action listener is used to go back to the doctor menu.
         */
        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });
        /**
         * This action listener is used to delete an appointment.
         * It checks if the ID field is filled or not.
         * If the ID field is filled, it deletes the appointment.
         * Else, it shows a message dialog to fill the ID field.
         */
        deleteButton.addActionListener(e -> {
            String id = idField.getText();
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the ID of the appointment you want to delete!");
            } else {
                int idInt = Integer.parseInt(id);
                if (idInt < 0){
                    JOptionPane.showMessageDialog(null, "Please enter a positive integer!");
                } else {
                    try{
                        Connection con = ConnectionProvider.getCon();
                        PreparedStatement st = con.prepareStatement("delete from appointment where id_appointment = ?");
                        st.setInt(1, idInt);
                        st.executeUpdate();
                        st.close();
                        con.close();
                        JOptionPane.showMessageDialog(null, "Appointment successfully deleted!");
                        parent.setVisible(true);
                        this.dispose();
                    }
                    catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "Please enter a valid ID!");
                    }
                }
            }
        });
    }
}
