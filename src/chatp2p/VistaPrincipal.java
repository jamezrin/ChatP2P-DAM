package chatp2p;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaPrincipal {
	protected final JFrame frame;
	protected final JPanel panel;
	
	public VistaPrincipal(JFrame frame) {
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
		
	}
	
	public void colocarComponentes() {
		
	}
}