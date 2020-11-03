package swingmvclab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel{

    /**
     *
     */
    private static final long serialVersionUID = 3447886449961442248L;
    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat. NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch(columnIndex) {
            case 0: return student.getName();
            case 1: return student.getNeptun();
            case 2: return student.hasSignature();
            default: return student.getGrade();
        }        
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = 
        {"Név",
         "Neptun", 
         "Aláírás", 
         "Jegy"};
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?>[] columnClasses = {String.class, String.class, Boolean.class, Integer.class};
        return columnClasses[columnIndex];
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        ArrayList<Integer> editableColumns = new ArrayList<Integer>(Arrays.asList(new Integer[] {2,3}));
        if (editableColumns.contains(columnIndex)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        students.get(rowIndex);
        switch (columnIndex){
            case 2: students.get(rowIndex).setSignature((Boolean)aValue);
                break;
        
            default: students.get(rowIndex).setGrade((Integer)aValue);;
                break;
        }
    }

    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, false, 0));
    }
}
