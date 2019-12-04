import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class Form2Design {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Chat UDP");
		
		JPanel panel = new JPanel();
		
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setPreferredSize(new Dimension(500, 300));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		
		listModel.addElement("nigger");
		listModel.addElement("adadasdasdsa");
		
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
        
        JTextField textField = new JTextField("hola mundo");
        panel2.add(textField);
		
		JButton buttonEnviar = new JButton("Enviar");
        panel2.add(buttonEnviar);
        
		
		panel.add(panel2);
		
		frame.setContentPane(panel);
		frame.pack();
		
		frame.setVisible(true);
	}
}
