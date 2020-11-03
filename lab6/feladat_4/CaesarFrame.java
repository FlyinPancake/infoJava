import javax.swing.JFrame;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CaesarFrame extends JFrame {
    
    private JTextField upperJTextField;
    private JTextField lowerJTextField;
    private JComboBox offsetJComboBox;


    CaesarFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SwingLab");
        setSize(400, 110);
        setResizable(false);

        JPanel upperJPanel = new JPanel();
        String[] cbOptions= new String[26];
        for (int i = 65; i < 91; i++) {
            cbOptions[i-65] = ""+(char)i;
        }
        offsetJComboBox = new JComboBox(cbOptions);
        upperJPanel.add(offsetJComboBox);

        upperJTextField = new JTextField(20);
        upperJPanel.add(upperJTextField);

        JButton encodeJButton = new JButton("Code!");
        encodeJButton.addActionListener(new OKButtonActionListener());
        upperJPanel.add(encodeJButton);

        add(upperJPanel, BorderLayout.NORTH);

        JPanel lowerJPanel = new JPanel();

        JLabel outputJLabel = new JLabel("Output: ");
        lowerJPanel.add(outputJLabel);

        lowerJTextField = new JTextField(20);
        lowerJTextField.setEditable(false);

        lowerJPanel.add(lowerJTextField);

        add(lowerJPanel, BorderLayout.SOUTH);

    }
    class OKButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            lowerJTextField.setText(App.caesarCode(upperJTextField.getText(), offsetJComboBox.getSelectedItem().toString().charAt(0)));
        }
    }
}

