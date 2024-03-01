package Tests;
import Database.ConnectionProvider;
import Utilities.Encrypt;
import org.junit.jupiter.api.Test;
import Project.ModifyDoctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ModifyDoctorTest {

    @Test
    public void testDoctorWithValidData(){
        JFrame parent = new JFrame();
        ModifyDoctor modifyDoctor = new ModifyDoctor(parent);
        JTextField idField = modifyDoctor.idField;
        idField.setText("118188");
        String idText = idField.getText();
        JTextField fnField = modifyDoctor.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = modifyDoctor.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField specField = modifyDoctor.specField;
        specField.setText("Cardiologist");
        String specText = specField.getText();
        JTextField emailField = modifyDoctor.emailField;
        emailField.setText("test");
        String emailText = emailField.getText();
        JPasswordField passwordField1 = modifyDoctor.passwordField1;
        passwordField1.setText("test");
        String pwdText = passwordField1.getText();
        JTextField phoneField = modifyDoctor.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JTextField salaryField = modifyDoctor.salaryField;
        salaryField.setText("1000");
        String salaryText = salaryField.getText();
        JTextField bonusField = modifyDoctor.bonusField;
        bonusField.setText("100");
        String bonusText = bonusField.getText();
        modifyDoctor.modifyButton.doClick();
        assertTrue(isDoctorModified(idText, fnText, lnText, specText, emailText,pwdText, phoneText, salaryText, bonusText));


    }

    @Test
    public void testDoctorWithInValidData(){
        JFrame parent = new JFrame();
        ModifyDoctor modifyDoctor = new ModifyDoctor(parent);
        JTextField idField = modifyDoctor.idField;
        idField.setText("-1");
        String idText = idField.getText();
        JTextField fnField = modifyDoctor.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = modifyDoctor.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField specField = modifyDoctor.specField;
        specField.setText("Cardiologist");
        String specText = specField.getText();
        JTextField emailField = modifyDoctor.emailField;
        emailField.setText("test");
        String emailText = emailField.getText();
        JPasswordField passwordField1 = modifyDoctor.passwordField1;
        passwordField1.setText("test");
        String pwdText = passwordField1.getText();
        JTextField phoneField = modifyDoctor.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JTextField salaryField = modifyDoctor.salaryField;
        salaryField.setText("1000");
        String salaryText = salaryField.getText();
        JTextField bonusField = modifyDoctor.bonusField;
        bonusField.setText("100");
        String bonusText = bonusField.getText();
        modifyDoctor.modifyButton.doClick();
        assertFalse(isDoctorModified(idText, fnText, lnText, specText, emailText, pwdText, phoneText, salaryText, bonusText));


    }

    private boolean isDoctorModified(String idText, String fnText, String lnText, String specText, String emailText, String pwdText, String phoneText, String salaryText, String bonusText) {
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from doctor where id=? and fn=? and ln=? and spec=? and email=? and password = ? and phone=? and salary=? and bonus=?");
            st.setString(1, idText);
            st.setString(2, fnText);
            st.setString(3, lnText);
            st.setString(4, specText);
            st.setString(5, emailText);
            String encryptedPwd = Encrypt.toHexString(Encrypt.encrypt(pwdText));
            st.setString(6, encryptedPwd);
            st.setString(7, phoneText);
            st.setString(8, salaryText);
            st.setString(9, bonusText);
            ResultSet rs = st.executeQuery();
            st.close();
            con.close();
            if(rs.next()){
                rs.close();
                return true;
            }
            else {
                rs.close();
                return false;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
}