package Utilities;
import Database.ConnectionProvider;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * This class is responsible for grouping together the methods that search the tables.
 * It is used in the view doctors, view patients and view appointments classes.
 */
public class SearchTable {
    /**
     * This method searches the table for the doctors.
     * It first tries to connect to the database and get the doctors.
     * If it succeeds, it displays the doctors in the table.
     * If it fails, it displays an error message.
     * @param table1 The table for the doctors.
     * @param id The ID of the doctor.
     */
    public void searchTablePatient(JTable table1, String id){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from appointment where id_appointment = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            table1.setModel(new DefaultTableModel(null, new String[]{"Appointment ID", "Doctor ID", "Patient ID", "Procedure type", "Issue Date", "Appointment Date", "Medication", "Diagnostic", "Price"}));
            while(rs.next()){
                String idAppointment = rs.getString(1);
                String idDoctor = rs.getString(2);
                String idPatient = rs.getString(3);
                String procedureType = rs.getString(4);
                Date issueDate = rs.getDate(5);
                String appointmentDate = rs.getDate(6).toString();
                String medication = rs.getString(7);
                String diagnostic = rs.getString(8);
                int price = rs.getInt(9);
                String[] tbData = {idAppointment, idDoctor, idPatient, procedureType, issueDate.toString(), appointmentDate, medication, diagnostic, Integer.toString(price)};
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
     * This method searches the table for the patients.
     * It first tries to connect to the database and get the patients.
     * If it succeeds, it displays the patients in the table.
     * If it fails, it displays an error message.
     * @param table2 The table for the patients.
     * @param id The ID of the patient.
     */
    public void searchTableAppointment(JTable table2, String id){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from patient where id_patient = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            table2.setModel(new DefaultTableModel(null, new String[]{"Patient ID", "First Name", "Last Name", "Address", "Phone Number", "Blood Type", "Date Of Birth", "Allergies"}));
            while(rs.next()){
                String idPatient = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String address = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String bloodType = rs.getString(6);
                String dateOB = rs.getString(7);
                String allergies = rs.getString(8);
                String[] tbData = {idPatient, firstName, lastName, address, phoneNumber, bloodType, dateOB, allergies};
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
     * This method searches the table for the doctors.
     * It first tries to connect to the database and get the doctors.
     * If it succeeds, it displays the doctors in the table.
     * If it fails, it displays an error message.
     * @param table1 The table for the doctors.
     * @param id The ID of the doctor.
     */
    public void searchTableDoctor(JTable table1, String id){
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("select * from medic where id_medic = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            table1.setModel(new DefaultTableModel(null, new String[]{"Doctor ID", "First Name", "Last Name", "Specialization", "Email","Phone_number", "Salary", "Bonus"}));
            while(rs.next()){
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
