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
 * It is only accessible by the doctor.
 * It asks the user to enter the ID of the patient they want to delete.
 * If the ID is valid, it deletes the patient from the database.
 * If the ID is invalid, it asks the user to enter a valid ID.
 * If the user does not enter an ID, it asks the user to enter an ID.
 * If the user confirms the deletion, it deletes the patient from the database.
 * If the user cancels the deletion, it does not delete the patient from the database.
 */
public class DeletePatient extends JFrame{
    public JTextField textField1;
    public JButton deleteButton;
    private JButton backButton;
    private JPanel panel1;

    public DeletePatient(JFrame parent){
        this.setTitle("HealthTrack");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        deleteButton.addActionListener(e -> {
            String id = textField1.getText();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the ID of the patient you want to delete!");
            }else{
                int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this patient?", "Select", JOptionPane.YES_NO_OPTION);
                if(a == 0){
                    try{
                        Connection con = new ConnectionProvider().getCon();
                        PreparedStatement st = con.prepareStatement("delete from patient where id_patient = ?");
                        st.setString(1, id);
                        st.executeUpdate();
                        st.close();
                        con.close();
                        JOptionPane.showMessageDialog(null, "Patient deleted successfully!");
                        this.dispose();
                        parent.setVisible(true);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Please enter a valid ID!");
                    }
                }
            }
        });

        backButton.addActionListener(e -> {
            this.dispose();
            parent.setVisible(true);
        });
    }
}
