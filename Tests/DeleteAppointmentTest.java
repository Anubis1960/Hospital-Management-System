package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import Project.DeleteAppointment;

class DeleteAppointmentTest {
    @Test
    public void testDeleteAppointmentWithValidData() {
        JFrame parent = new JFrame();
        DeleteAppointment deleteAppointment = new DeleteAppointment(parent);
        JTextField idField = deleteAppointment.idField;
        idField.setText("1");
        String idText = idField.getText();
        deleteAppointment.deleteButton.doClick();
        assertFalse(isAppointmentDeleted(idText));
    }

    public boolean isAppointmentDeleted(String id){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from appointment where id_appointment = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            st.close();
            con.close();
            if(rs.next()){
                rs.close();
                return false;
            }
            else {
                rs.close();
                return true;
            }
        } catch (Exception e){
            return false;
        }
    }
}