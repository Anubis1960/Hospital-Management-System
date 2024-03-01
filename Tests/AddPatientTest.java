package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Project.AddPatient;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

class AddPatientTest {

    @Test
    public void testPatientWithValidData() {
        JFrame parent = new JFrame();
        AddPatient addPatient = new AddPatient(parent);
        JTextField idField = addPatient.idField;
        idField.setText("118188");
        String idText = idField.getText();
        JTextField fnField = addPatient.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = addPatient.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField phoneField = addPatient.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JComboBox comboBox1 = addPatient.comboBox1;
        comboBox1.setSelectedItem("A+");
        String bloodText = (String) comboBox1.getSelectedItem();
        JTextField ageField = addPatient.ageField;
        ageField.setText("20");
        String ageText = ageField.getText();
        JTextField allergiesField = addPatient.allergiesField;
        allergiesField.setText("None");
        String allergiesText = allergiesField.getText();
        addPatient.saveButton.doClick();
        assertTrue(isPatientAdded(idText, fnText, lnText, phoneText, bloodText, ageText, allergiesText));
    }
    @Test
    public void testPatientWithMissingData() {
        JFrame parent = new JFrame();
        AddPatient addPatient = new AddPatient(parent);
        JTextField idField = addPatient.idField;
        idField.setText("");
        String idText = idField.getText();
        JTextField fnField = addPatient.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = addPatient.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField phoneField = addPatient.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JComboBox comboBox1 = addPatient.comboBox1;
        comboBox1.setSelectedItem("A+");
        String bloodText = (String) comboBox1.getSelectedItem();
        JTextField ageField = addPatient.ageField;
        ageField.setText("20");
        String ageText = ageField.getText();
        JTextField allergiesField = addPatient.allergiesField;
        allergiesField.setText("None");
        String allergiesText = allergiesField.getText();
        addPatient.saveButton.doClick();
        assertFalse(isPatientAdded(idText, fnText, lnText, phoneText, bloodText, ageText, allergiesText));
    }

    public boolean isPatientAdded(String idPatient, String fn, String ln, String phone, String blood, String age, String allergies){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from patient where id_patient = ? and first_name = ? and last_name = ? and phone_number = ? and blood_type = ? and age = ? and allergies = ?");
            st.setString(1, idPatient);
            st.setString(2, fn);
            st.setString(3, ln);
            st.setString(4, phone);
            st.setString(5, blood);
            st.setString(6, age);
            st.setString(7, allergies);
            ResultSet rs = st.executeQuery();
            st.close();
            con.close();
            if(rs.next()){
                rs.close();
                return true;
            }
            else{
                rs.close();
                return false;
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }

}