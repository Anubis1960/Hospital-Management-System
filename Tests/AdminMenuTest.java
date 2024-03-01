package Tests;
import Project.AdminMenu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

class AdminMenuTest {
    @Test
    public void testTextField2DocumentListener() {
        AdminMenu adminMenu = new AdminMenu();
        JTextField textField2 = adminMenu.textField2;
        JTable table2 = adminMenu.table2;
        textField2.setText("a");
        JButton button2 = adminMenu.button2;
        button2.doClick();
        assertEquals(0, table2.getModel().getRowCount());
    }

    @Test
    public void testTextField1DocumentListener() {
        AdminMenu adminMenu = new AdminMenu();
        JTextField textField1 = adminMenu.textField1;
        JTable table1 = adminMenu.table1;
        textField1.setText("");
        JButton button2 = adminMenu.button1;
        button2.doClick();
        assertEquals(0, table1.getModel().getRowCount());
    }

}