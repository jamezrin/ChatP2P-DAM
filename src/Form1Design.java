import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Form1Design {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Chat UDP");
		
		JPanel panel = new JPanel();
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
		//panel.setPreferredSize(new Dimension(500, 300));

		JButton button1 = new JButton("Ser anfritrion");
		button1.setPreferredSize(new Dimension(200, 200));
		
		JButton button2 = new JButton("Conectarse a alguien");
		button2.setPreferredSize(new Dimension(200, 200));
		
		panel.add(button1);
		panel.add(button2);
		
		frame.setContentPane(panel);
		frame.pack();
	}
}
