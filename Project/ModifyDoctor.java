package Project;
import Utilities.Encrypt;
import Database.ConnectionProvider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * This class modifies a doctor from the database.
 * It is only accessible by the admin.
 * It asks the user to enter the ID of the doctor they want to modify.
 * If the ID is valid, it asks the user to enter the new information of the doctor.
 * If the information is valid, it modifies the doctor from the database.
 * If the information is invalid, it asks the user to enter a valid data.
 * If the user does not enter an information, it asks the user to enter an information.
 * If the user confirms the modification, it modifies the doctor from the database.
 * If the user cancels the modification, it does not modify the doctor from the database.
 */
public class ModifyDoctor extends JFrame{
    public JTextField idField;
    public JTextField fnField;
    public JTextField lnField;
    public JTextField specField;
    public JTextField emailField;
    public JTextField phoneField;
    public JTextField salaryField;
    public JTextField bonusField;
    public JButton modifyButton;
    private JButton backButton;
    private JPanel panel1;
    public JPasswordField passwordField1;

    public ModifyDoctor(JFrame parent){
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
         * This action listener is used to modify a doctor.
         * It checks if the ID and information fields are filled or not.
         * If the ID and information fields are filled, it modifies the doctor.
         * Else, it shows a message dialog to fill the ID and information fields.
         */
        modifyButton.addActionListener(e -> {
            String id = idField.getText();
            String fn = fnField.getText();
            String ln = lnField.getText();
            String spec = specField.getText();
            String email = emailField.getText();
            String pwd = passwordField1.getText();
            String phone = phoneField.getText();
            String salary = salaryField.getText();
            String bonus = bonusField.getText();
            if(id.isEmpty() || fn.isEmpty() || ln.isEmpty() || spec.isEmpty() || email.isEmpty() || pwd.isEmpty() || phone.isEmpty() || salary.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the fields!");
            }
            else{
                try{
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement st = con.prepareStatement("update medic set first_name = ?, last_name = ?, specialization = ?, email = ?, password = ?, phone_number = ?, salary = ?, bonus = ? where id_medic = ?");
                    st.setString(1, fn);
                    st.setString(2, ln);
                    st.setString(3, spec);
                    st.setString(4, email);
                    String encryptedPwd = Encrypt.toHexString(Encrypt.encrypt(pwd));
                    st.setString(5, encryptedPwd);
                    st.setString(6, phone);
                    st.setString(7, salary);
                    st.setString(8, bonus);
                    st.setString(9, id);
                    st.executeUpdate();
                    st.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Successfully updated!");
                    parent.setVisible(true);
                    this.dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

    }
}
