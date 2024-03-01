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
 * This class deletes a patient from the database.
 * It is only accessible by the admin.
 * It asks the user to enter the ID of the doctor they want to delete.
 * If the ID is valid, it deletes the patient from the database.
 * If the ID is invalid, it asks the user to enter a valid ID.
 * If the user does not enter an ID, it asks the user to enter an ID.
 * If the user confirms the deletion, it deletes the doctor from the database.
 * If the user cancels the deletion, it does not delete the doctor from the database.
 */
public class DeleteDoctor extends JFrame{
    public JTextField textField1;
    public JButton deleteButton;
    private JButton backButton;
    private JPanel panel1;
    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public DeleteDoctor(JFrame parent){
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
         * This action listener is used to delete a doctor.
         * It checks if the ID field is filled or not.
         * If the ID field is filled, it deletes the doctor.
         * Else, it shows a message dialog to fill the ID field.
         */
        deleteButton.addActionListener(e -> {
            String id = textField1.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the ID of the doctor you want to delete!");
            }
            else{
                int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this doctor?", "Select", JOptionPane.YES_NO_OPTION);
                if(a == 0){
                    try{
                        Connection con = ConnectionProvider.getCon();
                        PreparedStatement st = con.prepareStatement("delete from medic where id_medic = ?");
                        st.setString(1, id);
                        st.executeUpdate();
                        st.close();
                        con.close();
                        JOptionPane.showMessageDialog(null, "Doctor deleted successfully!");
                        parent.setVisible(true);
                        this.dispose();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
    }
}
