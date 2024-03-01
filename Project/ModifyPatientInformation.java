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
 * This class is responsible for modifying the patient's information.
 * It is only accessible by the doctor.
 * It extends the JFrame class.
 */
public class ModifyPatientInformation extends JFrame{

    public JTextField idField;
    public JTextField fnField;
    public JTextField lnField;
    public JTextField phoneField;
    public JTextField ageField;
    public JButton updateButton;
    private JButton backButton;
    public JTextField allergiesField;
    private JPanel panel1;
    private JTextField adressField;

    /**
     * This constructor calls the parent constructor of the JFrame class.
     * It also sets the title of the frame, sets the content pane, sets the default close operation, packs the frame and sets it to be visible.
     * It also adds action listeners to the back button and update button.
     */
    public ModifyPatientInformation(JFrame parent){
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
         * This action listener is responsible for updating the patient's information.
         * It first checks if all the fields are filled.
         * If they are, it tries to connect to the database and update the patient's information.
         * If it succeeds, it displays a message and closes the connection.
         * If it fails, it displays an error message.
         */
        updateButton.addActionListener(e -> {
            String id = idField.getText();
            String fn = fnField.getText();
            String ln = lnField.getText();
            String phone = phoneField.getText();
            String adress = adressField.getText();
            String allergies = allergiesField.getText();
            if(id.isEmpty() || fn.isEmpty() || ln.isEmpty() || phone.isEmpty() || adress.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the fields!");
            }
            else{
                try{
                    Connection con = ConnectionProvider.getCon();
                    if(allergies.isEmpty()){
                        allergies = null;
                    }
                    PreparedStatement st = con.prepareStatement("update patient set first_name = ?, last_name = ?, Adress = ?, phone_number = ?, allergies = ? where id_patient = ?");
                    st.setString(1, fn);
                    st.setString(2, ln);
                    st.setString(3, adress);
                    st.setString(4, phone);
                    st.setString(5, allergies);
                    st.setString(6, id);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully updated!");
                    st.close();
                    con.close();
                    this.dispose();
                    parent.setVisible(true);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

}
