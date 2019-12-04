package chatp2p;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Vista {
	protected final JFrame frame;
	protected final JPanel panel;
	
	protected DefaultListModel<String> mensajesModel;
	private JList<String> mensajesList;
	protected JScrollPane mensajesScrollPane;
	
	private JPanel mensajePanel;
	protected JTextField mensajeTextField;
	protected JButton enviarButton;
	
	public Vista(JFrame frame) {
		this.frame = frame;
		this.panel = new JPanel();
		crearComponentes();
		colocarComponentes();
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void dominar() {
		frame.setContentPane(panel);
		frame.pack();
	}
	
	public void crearComponentes() {
		mensajesModel = new DefaultListModel<>();
		mensajesList = new JList<>(mensajesModel);
		mensajesScrollPane = new JScrollPane(mensajesList);
		
		mensajePanel = new JPanel();
		mensajeTextField = new JTextField();
		enviarButton = new JButton("Enviar");
	}
	
	public void colocarComponentes() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		mensajePanel.setLayout(new GridLayout());
		mensajesScrollPane.setPreferredSize(new Dimension(500, 300));
		
		mensajePanel.add(mensajeTextField);
		mensajePanel.add(enviarButton);
		
		panel.add(mensajesScrollPane);
		panel.add(mensajePanel);
	}
}