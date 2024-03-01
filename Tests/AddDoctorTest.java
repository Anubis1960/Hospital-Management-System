package Tests;
import Database.ConnectionProvider;
import Utilities.Encrypt;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.AddDoctor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddDoctorTest {
    @Test
    public void testAddDoctorWithValidData() {

        JFrame parent = new JFrame();
        AddDoctor addDoctor = new AddDoctor(parent);

        JTextField idField = addDoctor.idField;
        idField.setText("118188");
        String idText = idField.getText();
        JTextField fnField = addDoctor.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = addDoctor.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        JTextField specField = addDoctor.specField;
        specField.setText("Cardiology");
        String specText = specField.getText();
        JTextField emailField = addDoctor.emailField;
        emailField.setText("test@mail.com");
        String emailText = emailField.getText();
        JPasswordField passwordField1 = addDoctor.passwordField1;
        passwordField1.setText("test");
        String passwordText = passwordField1.getText();
        JTextField phoneField = addDoctor.phoneField;
        phoneField.setText("1234567890");
        String phoneText = phoneField.getText();
        JTextField salaryField = addDoctor.salaryField;
        salaryField.setText("10000");
        String salaryText = salaryField.getText();
        JTextField bonusField = addDoctor.bonusField;
        bonusField.setText("1000");
        String bonusText = bonusField.getText();

        addDoctor.saveButton.doClick();
        assertTrue(isDoctorAdded(idText, fnText, lnText, specText, emailText, passwordText, phoneText, salaryText, bonusText));
    }

    @Test
    public void testAddDoctorWithMissingData() {
        JFrame parent = new JFrame();
        AddDoctor addDoctor = new AddDoctor(parent);
        JTextField idField = addDoctor.idField;
        idField.setText("163");
        String idText = idField.getText();
        JTextField fnField = addDoctor.fnField;
        fnField.setText("John");
        String fnText = fnField.getText();
        JTextField lnField = addDoctor.lnField;
        lnField.setText("Doe");
        String lnText = lnField.getText();
        String specText = "";
        String emailText = "";
        String passwordText = "";
        String phoneText = "";
        String salaryText = "";
        String bonusText = "";

        addDoctor.saveButton.doClick();
        assertFalse(isDoctorAdded(idText, fnText, lnText, specText, emailText, passwordText, phoneText, salaryText, bonusText));
    }

    public boolean isDoctorAdded(String id, String fn, String ln, String spec, String email, String pwd, String phone, String salary, String bonus) {
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from medic where id_medic = ? and first_name = ? and last_name = ? and specialization = ? and email = ? and password = ? and phone_number = ? and salary = ? and bonus = ?");
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