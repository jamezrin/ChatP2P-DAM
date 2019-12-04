package chatp2p;

import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Vista vista = new Vista(frame);
		Controlador controlador = new Controlador(vista);
		vista.dominar();
	}
}
