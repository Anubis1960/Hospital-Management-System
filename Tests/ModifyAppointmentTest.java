package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import Project.ModifyAppointment;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import static org.junit.jupiter.api.Assertions.*;


class ModifyAppointmentTest {

    @Test
    public void testAppointmentWithValidData() {
        JFrame parent = new JFrame();
        ModifyAppointment modifyAppointment = new ModifyAppointment(parent);
        JTextField appointmentIdField = modifyAppointment.appointmentIdField;
        appointmentIdField.setText("2");
        String appointmentId = appointmentIdField.getText();
        JTextField appointmentDateField = modifyAppointment.appointmentDateField;
        appointmentDateField.setText("2024-01-01");
        String appointmentDate = appointmentDateField.getText();
        modifyAppointment.modifyButton.doClick();
        assertTrue(isAppointmentModified(appointmentId, appointmentDate));
    }

    @Test
    public void testAppointmentWithInValidData() {
        JFrame parent = new JFrame();
        ModifyAppointment modifyAppointment = new ModifyAppointment(parent);
        JTextField appointmentIdField = modifyAppointment.appointmentIdField;
        appointmentIdField.setText("0");
        String appointmentId = appointmentIdField.getText();
        JTextField appointmentDateField = modifyAppointment.appointmentDateField;
        appointmentDateField.setText("2024-01-01");
        String appointmentDate = appointmentDateField.getText();
        modifyAppointment.modifyButton.doClick();
        assertFalse(isAppointmentModified(appointmentId, appointmentDate));
    }

    public boolean isAppointmentModified(String appointmentId, String appointmentDate){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from appointment where appointmentId = ? and appointmentDate = ?");
            st.setString(1, appointmentId);
            st.setString(2, appointmentDate);
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