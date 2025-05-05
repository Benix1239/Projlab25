package swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JatekPanel extends JPanel {
    
    private MainFrame mainFrame;
    private InfoFrame infoFrame;
    private JButton mentes;
    private JButton kilepes;
    private JTextField jelenlegiJatekos;
    private JTextField visszajelzes;
    private JComboBox<String> elsoComboBox;
    private JComboBox<String> masodikComboBox;
    private JPanel palyaPanel;
    private ArrayList<JButton> tektonGombok;
    private Command command;

}
