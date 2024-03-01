package Tests;
import Database.ConnectionProvider;
import org.junit.jupiter.api.Test;
import Project.CreateAppointment;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class CreateAppointmentTest {

    @Test
    public void testCreateAppointmentWithValidData() {
        JFrame parent = new JFrame();
        CreateAppointment createAppointment = new CreateAppointment(parent);
        JTextField idMedicField = createAppointment.idMedicField;
        idMedicField.setText("11888");
        String idMedicText = idMedicField.getText();
        JTextField patientIdField = createAppointment.patientIdField;
        patientIdField.setText("118188");
        String patientIdText = patientIdField.getText();
        JTextField procedureField = createAppointment.procedureField;
        procedureField.setText("Test");
        String procedureText = procedureField.getText();
        JTextField issueField = createAppointment.issueField;
        issueField.setText("2023-01-01");
        String issueText = issueField.getText();
        JTextField appointmentField = createAppointment.appointmentField;
        appointmentField.setText("2024-01-01");
        String appointmentText = appointmentField.getText();
        JTextField medicationField = createAppointment.medicationField;
        medicationField.setText("Test");
        String medicationText = medicationField.getText();
        JTextField diagnosticField = createAppointment.diagnosticField;
        diagnosticField.setText("Test");
        String diagnosticText = diagnosticField.getText();
        JTextField priceField = createAppointment.priceField;
        priceField.setText("100");
        String priceText = priceField.getText();
        createAppointment.createButton.doClick();
        assertFalse(isAppointmentCreated(idMedicText, patientIdText, procedureText, issueText, appointmentText, medicationText, diagnosticText, priceText));
    }

    @Test
    public void testPatientWithMissingData() {
        JFrame parent = new JFrame();
        CreateAppointment createAppointment = new CreateAppointment(parent);
        JTextField idMedicField = createAppointment.idMedicField;
        idMedicField.setText("118188");
        String idMedicText = idMedicField.getText();
        JTextField patientIdField = createAppointment.patientIdField;
        patientIdField.setText("118188");
        String patientIdText = patientIdField.getText();
        String procedureText = "";
        String issueText = "";
        String appointmentText = "";
        String medicationText = "";
        String diagnosticText = "";
        String priceText = "";
        createAppointment.createButton.doClick();
        assertFalse(isAppointmentCreated(idMedicText, patientIdText, procedureText, issueText, appointmentText, medicationText, diagnosticText, priceText));
    }

    private boolean isAppointmentCreated(String idMedicText, String patientIdText, String procedureText, String issueText, String appointmentText, String medicationText, String diagnosticText, String priceText) {
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from appointment where id_medic = ? and id_patient = ? and procedure_type = ? and date_format(issue_date, '%y-%m-%d') = ? and date_format(appointment_date, '%y-%m-%d') = ? and medication = ? and diagnostic = ? and price = ?");
            st.setString(1, idMedicText);
            st.setString(2, patientIdText);
            st.setString(3, procedureText);
            st.setString(4, issueText);
            st.setString(5, appointmentText);
            st.setString(6, medicationText);
            st.setString(7, diagnosticText);
            st.setString(8, priceText);
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
            e.printStackTrace();
        }
        return false;
    }

}