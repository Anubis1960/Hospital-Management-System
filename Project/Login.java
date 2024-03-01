package Project;
import Database.ConnectionProvider;
import Utilities.Encrypt;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * This class is used to display the login page.
 * It is the first page that is displayed when the program is run.
 * It asks the user to enter their email and password.
 * If the email and password are valid, it redirects the user to the doctor menu.
 * If the email and password are invalid, it shows a message dialog to enter a valid email and password.
 */
public class Login extends JFrame {
    private JTextField textField1;
    private JButton loginButton;
    private JLabel Email;
    private JLabel Password;
    private JPanel LoginPanel;
    private JPasswordField passwordField1;

    public Login(){
        this.setTitle("HeathTrack");
        this.setContentPane(LoginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        loginButton.addActionListener(e -> {
            String email = textField1.getText();
            String password = passwordField1.getText();
            try {
                Connection con = ConnectionProvider.getCon();
                String encryptedPassword = Encrypt.toHexString(Encrypt.encrypt(password));
                PreparedStatement st = con.prepareStatement("select * from medic where email = ? and password = ?");
                st.setString(1, email);
                st.setString(2, encryptedPassword);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    this.dispose();
                    new DoctorMenu().setVisible(true);
                } else {
                    st.close();
                    rs.close();

                    st = con.prepareStatement("select * from admin where username = ? and password = ?");
                    st.setString(1, email);
                    st.setString(2, encryptedPassword);
                    rs = st.executeQuery();

                    if (rs.next()) {
                        this.dispose();
                        new AdminMenu().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect email or password");
                    }

                    st.close();
                    rs.close();
                    con.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
    }
    public static void main(String[] args) {
        new Login();
    }
}
