package Utilities;
import Database.ConnectionProvider;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class is responsible for creating the tables.
 * It is used in the view doctors, view patients and view appointments classes.
 */
public class CreateTable {
    /**
     * This method creates the table for the doctors.
     * It first tries to connect to the database and get the doctors.
     * If it succeeds, it displays the doctors in the table.
     * If it fails, it displays an error message.
     * @param table2 The table for the doctors.
     */
    public void CreateTablePatient(JTable table2){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from patient");
            ResultSet rs = st.executeQuery();
            table2.setModel(new DefaultTableModel(null, new String[]{"Patient ID", "First Name", "Last Name", "Adress", "Phone Number", "Blood Type", "Date Of Birth", "Allergies"}));
            while(rs.next()){
                String idPatient = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String adress = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String bloodType = rs.getString(6);
                Date dateOB = rs.getDate(7);
                String allergies = rs.getString(8);
                String[] tbData = {idPatient, firstName, lastName, adress, phoneNumber, bloodType, String.valueOf(dateOB), allergies};
                DefaultTableModel tbModel = (DefaultTableModel) table2.getModel();
                tbModel.addRow(tbData);
            }
            st.close();
            con.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * This method creates the table for the appointments.
     * It first tries to connect to the database and get the appointments.
     * If it succeeds, it displays the appointments in the table.
     * If it fails, it displays an error message.
     * @param table1 The table for the appointments.
     */
    public void CreateTableAppointment(JTable table1){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from appointment");
            ResultSet rs = st.executeQuery();
            table1.setModel(new DefaultTableModel(null, new String[]{"Appointment ID", "Doctor ID", "Patient ID", "Procedure type","Issue Date", "Appointment date", "Medication", "Diagnostic", "Price"}));
            while(rs.next()){
                String idAppointment = rs.getString(1);
                String idDoctor = rs.getString(2);
                String idPatient = rs.getString(3);
                String procedureType = rs.getString(4);
                Date issueDate = rs.getDate(5);
                Date appointmentDate = rs.getDate(6);
                String medication = rs.getString(7);
                String diagnostic = rs.getString(8);
                int price = rs.getInt(9);
                String issueDateStr;
                String appointmentDateStr;
                if(issueDate == null)
                    issueDateStr = null;
                else
                    issueDateStr = issueDate.toString();
                if(appointmentDate == null)
                    appointmentDateStr = null;
                else
                    appointmentDateStr = appointmentDate.toString();

                String[] tbData = {idAppointment, idDoctor, idPatient, procedureType, issueDateStr, appointmentDateStr, medication, diagnostic, Integer.toString(price)};
                DefaultTableModel tbModel = (DefaultTableModel) table1.getModel();
                tbModel.addRow(tbData);
            }
            st.close();
            con.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * This method creates the table for the doctors.
     * It first tries to connect to the database and get the doctors.
     * If it succeeds, it displays the doctors in the table.
     * If it fails, it displays an error message.
     * @param table1 The table for the doctors.
     */
    public void CreateTableDoctor(JTable table1){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from medic");
            ResultSet rs = st.executeQuery();
            table1.setModel(new DefaultTableModel(null, new String[]{"Doctor ID", "First Name", "Last Name", "Specialization", "Email","Phone_number", "Salary", "Bonus"}));
            while(rs.next()){
                String id = rs.getString("id_medic");
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                String spec = rs.getString("specialization");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String salary = rs.getString("salary");
                String bonus = rs.getString("bonus");
                DefaultTableModel tbModel = (DefaultTableModel) table1.getModel();
                String[] tbData = {id, fn, ln, spec, email, phone, salary, bonus};
                tbModel.addRow(tbData);
            }
            st.close();
            con.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
