package Project;
import Database.ConnectionProvider;
import Utilities.Encrypt;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * This class is used to add a doctor to the database.
 * It extends JFrame and implements ActionListener.
 */
public class AddDoctor extends JFrame{
    public JTextField idField;
    public JTextField fnField;
    public JTextField lnField;
    public JTextField specField;
    public JTextField emailField;
    public JTextField phoneField;
    public JTextField salaryField;
    public JTextField bonusField;
    public JButton saveButton;
    private JButton backButton;
    private JPanel panel1;
    public JPasswordField passwordField1;

    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public AddDoctor(JFrame parent){
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
         * This action listener is used to save the doctor details to the database.
         * It checks if all the fields are filled or not.
         * If all the fields are filled, it saves the details to the database.
         * Else, it shows a message dialog to fill all the fields.
         */
        saveButton.addActionListener(e -> {
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
                    PreparedStatement st = con.prepareStatement("insert into medic(id_medic, first_name, last_name, specialization, email, password, phone_number, salary, bonus) values(?, ? , ?, ?, ?, ?, ?, ?, ?)");
                    st.setString(1, id);
                    st.setString(2, fn);
                    st.setString(3, ln);
                    st.setString(4, spec);
                    st.setString(5, email);
                    String encryptedPwd = Encrypt.toHexString(Encrypt.encrypt(pwd));
                    st.setString(6, encryptedPwd);
                    st.setString(7, phone);
                    st.setString(8, salary);
                    st.setString(9, bonus);
                    st.executeUpdate();
                    st.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Successfully added!");
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
