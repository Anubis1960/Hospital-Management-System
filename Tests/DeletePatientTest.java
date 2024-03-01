package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import Project.DeletePatient;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;

class DeletePatientTest {

        @Test
        public void testPatientWithValidData() {
            JFrame parent = new JFrame();
            DeletePatient deletePatient = new DeletePatient(parent);
            deletePatient.textField1.setText("118188");
            String idText = deletePatient.textField1.getText();
            deletePatient.deleteButton.doClick();
            assertFalse(isPatientDeleted(idText));
        }

        public boolean isPatientDeleted(String idText){
            try{
                Connection con = ConnectionProvider.getCon();
                PreparedStatement st = con.prepareStatement("select * from patient where id_patient = '"+idText+"'");
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