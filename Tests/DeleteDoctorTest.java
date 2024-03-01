package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import Project.DeleteDoctor;

import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class DeleteDoctorTest {

    @Test
    public void testDeleteDoctorWithValidData() {
        JFrame parent = new JFrame();
        DeleteDoctor deleteDoctor = new DeleteDoctor(parent);
        deleteDoctor.textField1.setText("11888");
        String idText = deleteDoctor.textField1.getText();
        deleteDoctor.deleteButton.doClick();
        assertFalse(isDoctorDeleted(idText));
    }

    public boolean isDoctorDeleted(String idText){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from medic where id_medic = '"+idText+"'");
            ResultSet rs = st.executeQuery();
            st.close();
            con.close();
            if(rs.next()){
                rs.close();
                return false;
            }
            else{
                rs.close();
                return true;
            }
        }
        catch(Exception e){
            return false;
        }
    }

}