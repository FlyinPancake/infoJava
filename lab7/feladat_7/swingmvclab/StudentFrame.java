package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendő ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 3769575128566519194L;
    /*
     * Ebben az objektumban vannak a hallgatói adatok. A program indulás után
     * betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;


    private JTextField neptunField;
    private JTextField nameField;

    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különböző komponenseket
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        //table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Integer.class, new StudentTableCellRenderer(table.getDefaultRenderer(Integer.class)));
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane,BorderLayout.CENTER);

        JPanel addRowPanel = new JPanel();
        nameField = new JTextField(20);
        addRowPanel.add(new JLabel("Név: "));
        addRowPanel.add(nameField);
        neptunField = new JTextField(6);
        addRowPanel.add(new JLabel("Neptun"));
        addRowPanel.add(neptunField);

        JButton addRowButton = new JButton("Felvesz");
        addRowButton.addActionListener(new AddButtonActionListener());
        addRowPanel.add(addRowButton);

        this.add(addRowPanel,BorderLayout.SOUTH);

    }
    class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            data.addStudent(nameField.getText(), neptunField.getText());
        }
    }

    class StudentTableCellRenderer implements TableCellRenderer {
        private TableCellRenderer renderer;

        public StudentTableCellRenderer(TableCellRenderer defRenderer) {
                this.renderer = defRenderer;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Kikeressük az éppen megjelenítendő hallgatót a külső,
            // StudentFrame osztály data tagváltozójából,
            // megállapítjuk, hogy bukásra áll-e vagy sem,
            // és ez alapján átállítjuk a komponens háttérszínét:
            // component.setBackground(...)
            
            Student student = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
            if (student.hasSignature() && student.getGrade() >= 2) {
                component.setBackground(Color.GREEN);
            } else {
                component.setBackground(Color.RED);
            }
            return component;
        }
    }
    

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
