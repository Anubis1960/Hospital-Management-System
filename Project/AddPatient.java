package Project;
import Database.ConnectionProvider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used to add a patient to the database.
 * It extends JFrame and implements ActionListener.
 */
public class AddPatient extends JFrame{

    private JPanel panel1;
    public JTextField idField;
    public JTextField allergiesField;
    public JTextField phoneField;
    public JTextField lnField;
    public JTextField fnField;
    public JTextField ageField;
    public JComboBox comboBox1;
    public JButton saveButton;
    private JButton closeButton;
    private JTextField adressField1;

    /**
     * This constructor is used to initialize the components and buttons.
     * It also contains the action listeners for the buttons.
     */
    public AddPatient(JFrame parent){
        this.setTitle("HealthTrack");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.comboBox1.addItem("A+");
        this.comboBox1.addItem("A-");
        this.comboBox1.addItem("B+");
        this.comboBox1.addItem("B-");
        this.comboBox1.addItem("AB+");
        this.comboBox1.addItem("AB-");
        this.comboBox1.addItem("0+");
        this.comboBox1.addItem("0-");
        /**
         * This action listener is used to go back to the doctor menu.
         */
        closeButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });
        /**
         * This action listener is used to save the patient details to the database.
         * It checks if all the fields are filled or not.
         * If all the fields are filled, it saves the details to the database.
         * Else, it shows a message dialog to fill all the fields.
         */
        saveButton.addActionListener(e -> {
            String id = idField.getText();
            String allergies = allergiesField.getText();
            String phone = phoneField.getText();
            String adress = adressField1.getText();
            String ln = lnField.getText();
            String fn = fnField.getText();
            String age = ageField.getText();
            String blood = comboBox1.getSelectedItem().toString();
            if(id.isEmpty() || phone.isEmpty() || ln.isEmpty() || fn.isEmpty() || age.isEmpty() || blood.isEmpty() || adress.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the fields!");
            }
            else{
                try{
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement st = con.prepareStatement("insert into patient(id_patient, first_name, last_name, Adress, phone_number, blood_type, date_of_birth, allergies) values(?, ? , ?, ?, ?, ?, ?, ?)");
                    st.setString(1, id);
                    st.setString(2, fn);
                    st.setString(3, ln);
                    st.setString(4, adress);
                    st.setString(5, phone);
                    st.setString(6, blood);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateOb = sdf.parse(age);
                    st.setDate(7, new java.sql.Date(dateOb.getTime()));
                    if(allergies.isEmpty())
                        st.setString(8, null);
                    else
                        st.setString(8, allergies);

                    st.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Successfully added!");
                    st.close();
                    con.close();
                    this.dispose();
                    parent.setVisible(true);

                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter valid data!");
                }
            }
        });

        ageField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(ageField.getText().isEmpty()){
                    ageField.setText("yyyy-mm-dd");
                }
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(ageField.getText().equals("yyyy-mm-dd")){
                    ageField.setText("");
                }
            }
        });
    }
}
