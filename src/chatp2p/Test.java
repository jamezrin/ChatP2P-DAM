package chatp2p;

import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		VistaPrincipal vista = new VistaPrincipal(frame);
		ControladorPrincipal controlador = new ControladorPrincipal(vista);
		vista.dominar();
	}
}
