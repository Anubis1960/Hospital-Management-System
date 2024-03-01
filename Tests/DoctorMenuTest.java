package Tests;
import Project.DoctorMenu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

class DoctorMenuTest {
    @Test
    public void testTextField2DocumentListener() {
        DoctorMenu doctorMenu = new DoctorMenu();
        JTextField textField2 = doctorMenu.textField2;
        JTable table2 = doctorMenu.table2;
        textField2.setText("a");
        JButton button2 = doctorMenu.button2;
        button2.doClick();
        assertEquals(0, table2.getModel().getRowCount());
    }

    @Test
    public void testTextField1DocumentListener() {
        DoctorMenu doctorMenu = new DoctorMenu();
        JTextField textField1 = doctorMenu.textField1;
        JTable table1 = doctorMenu.table1;
        textField1.setText("");
        JButton button2 = doctorMenu.button1;
        button2.doClick();
        assertEquals(0, table1.getModel().getRowCount());
    }

}