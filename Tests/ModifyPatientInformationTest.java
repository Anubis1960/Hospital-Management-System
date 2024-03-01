package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Project.ModifyPatientInformation;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTextField;

class ModifyPatientInformationTest {
    @Test
    public void testPatientWithValidData() {
        JFrame parent = new JFrame();
        ModifyPatientInformation modifyPatientInformation = new ModifyPatientInformation(parent);
        JTextField idField = modifyPatientInformation.idField;
        idField.setText("118188");
        String idText = idField.getText();
        JTextField fnField = modifyPatientInformation.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = modifyPatientInformation.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField phoneField = modifyPatientInformation.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JTextField ageField = modifyPatientInformation.ageField;
        ageField.setText("20");
        String ageText = ageField.getText();
        JTextField allergiesField = modifyPatientInformation.allergiesField;
        allergiesField.setText("None");
        String allergiesText = allergiesField.getText();
        modifyPatientInformation.updateButton.doClick();
        assertTrue(isPatientModified(idText, fnText, lnText, phoneText, ageText, allergiesText));
    }

    @Test
    public void testPatientWithMissingData() {
        JFrame parent = new JFrame();
        ModifyPatientInformation modifyPatientInformation = new ModifyPatientInformation(parent);
        JTextField idField = modifyPatientInformation.idField;
        idField.setText("");
        String idText = idField.getText();
        JTextField fnField = modifyPatientInformation.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = modifyPatientInformation.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField phoneField = modifyPatientInformation.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JTextField ageField = modifyPatientInformation.ageField;
        ageField.setText("20");
        String ageText = ageField.getText();
        JTextField allergiesField = modifyPatientInformation.allergiesField;
        allergiesField.setText("None");
        String allergiesText = allergiesField.getText();
        modifyPatientInformation.updateButton.doClick();
        assertFalse(isPatientModified(idText, fnText, lnText, phoneText, ageText, allergiesText));
    }

    public boolean isPatientModified(String id, String fn, String ln, String phone, String age, String allergies){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from patient where id_patient = ? and first_name = ? and last_name = ? and phone_number = ? and age = ? and allergies = ?");
            st.setString(1, id);
            st.setString(2, fn);
            st.setString(3, ln);
            st.setString(4, phone);
            st.setString(5, age);
            st.setString(6, allergies);
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
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}