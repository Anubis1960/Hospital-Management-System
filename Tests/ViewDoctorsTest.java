package Tests;

import Project.ViewDoctors;
import org.junit.jupiter.api.Test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.*;

class ViewDoctorsTest {

    @Test
    public void testTextField1DocumentListener() {
        JFrame parent = new JFrame();
        ViewDoctors viewDoctorsMenu = new ViewDoctors(parent);
        JTextField textField1 = viewDoctorsMenu.textField1;
        JTable table1 = viewDoctorsMenu.table1;
        textField1.setText("");
        JButton button2 = viewDoctorsMenu.searchButton;
        button2.doClick();
        assertEquals(0, table1.getModel().getRowCount());
    }

}