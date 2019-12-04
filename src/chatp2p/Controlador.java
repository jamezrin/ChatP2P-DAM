package chatp2p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Controlador {
	protected Vista vista;
	
	protected int puertoEntrada; // por donde recibimos
	protected String hostSalida;
	protected int puertoSalida; // a donde enviamos
	protected String nombreUsuario;
	
	public Controlador(Vista vista) {
		this.vista = vista;
		
		puertoEntrada = preguntarPuertoEntrada();
		
		hostSalida = hacerPregunta(
				"Dime la ip o nombre de host del otro usuario",
				"Dirección del otro usuario",
				"localhost"
		);
		
		do {
			puertoSalida = preguntarPuertoSalida();
			
			if (puertoSalida == puertoEntrada) {
				JOptionPane.showMessageDialog(
						vista.frame, 
						"Los puertos de entrada y salida no pueden ser el mismo",
						"Puerto de salida",
						JOptionPane.ERROR_MESSAGE
				);
			}
		} while (puertoSalida == puertoEntrada);
		
		nombreUsuario = hacerPregunta(
				"Dime el nombre de usuario que quieres usar", 
				"Tu nombre de usuario",
				"Jamezrin"
		);
		
		vista.frame.setTitle(String.format("%s recibiendo en %d, enviando a %s:%d", 
				nombreUsuario,
				puertoEntrada,
				hostSalida, puertoSalida
		));
		
		HiloRecibirMensaje hiloRecibirMensaje = new HiloRecibirMensaje(this);
		hiloRecibirMensaje.start();
		
		EnviarListener listener = new EnviarListener();
		vista.enviarButton.addActionListener(listener);
		vista.mensajeTextField.addActionListener(listener);
	}
	
	public class EnviarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String mensaje = vista.mensajeTextField.getText();
			if (!mensaje.isEmpty() && !mensaje.trim().equals("")) {
				vista.mensajeTextField.setText(null);
				enviarMensaje(mensaje);
			}
		}
	}
	
	public void enviarMensaje(String mensaje) {
		String mensajeFormateado = hacerMensajeFormateado(nombreUsuario, mensaje);
		byte[] mensajeBytes = mensajeFormateado.getBytes();
		
		if (mensajeBytes.length <= 512) {
			vista.mensajesModel.addElement(mensajeFormateado);
			HiloEnviarMensaje hiloEnviarMensaje = new HiloEnviarMensaje(
					Controlador.this,
					mensajeBytes
			);
			hiloEnviarMensaje.start();
		} else {
			JOptionPane.showMessageDialog(
					vista.frame, 
					"El mensaje que has introducido es demasiado largo",
					"Mensaje demasiado largo",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}
	
	public String hacerMensajeFormateado(String usuario, String mensaje) {
		LocalDateTime now = LocalDateTime.now();
		return String.format(
			"%s [%02d:%02d:%02d]: %s", 
			usuario, 
			now.getHour(), 
			now.getMinute(), 
			now.getSecond(), 
			mensaje
		);
	}
	
	private String hacerPregunta(String mensaje, String titulo, String valorInicial) {
		while (true) {
			String respuesta = (String) JOptionPane.showInputDialog(
					vista.frame, 
					mensaje, 
					titulo,
					JOptionPane.QUESTION_MESSAGE,
					null, null, 
					valorInicial
			);
			
			if (respuesta == null ||respuesta.isEmpty() || respuesta.trim().equals("")) {
				JOptionPane.showMessageDialog(
						vista.frame, 
						"No puedes dejar este campo vacio",
						titulo,
						JOptionPane.ERROR_MESSAGE
				);
				continue;
			}
			
			return respuesta;
		}
	}
	
	private int preguntarPuerto(String mensaje, String title, String valorInicial) {
		while (true) {
			String respuesta = hacerPregunta(
					mensaje, 
					title,
					valorInicial
			);
			
			try {
				int puerto = Integer.parseInt(respuesta);
				
				if (puerto < 1000) {
					JOptionPane.showMessageDialog(
							vista.frame, 
							"El puerto tiene que ser un mayor o igual a 1000",
							title,
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				
				if (puerto > Short.MAX_VALUE) {
					JOptionPane.showMessageDialog(
							vista.frame, 
							"El puerto tiene que ser menor que " + Short.MAX_VALUE,
							title,
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				
				return puerto;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(
						vista.frame, 
						"El puerto tiene que ser un numero",
						title,
						JOptionPane.ERROR_MESSAGE
				);
				continue;
			}
		}
	}
	
	private int preguntarPuertoEntrada() {
		while (true) {
			int puerto = preguntarPuerto(
					"Dime el puerto por donde quieres que se conecte el otro usuario", 
					"Introduce puerto de entrada",
					"9000"
			);
			
			try {
				DatagramSocket udpSocket = new DatagramSocket(puerto);
				udpSocket.close();
			} catch (SocketException e) {
				JOptionPane.showMessageDialog(
						vista.frame, 
						"El puerto de entrada que has introducido está siendo utilizado por otra aplicación",
						"Puerto de entrada usado",
						JOptionPane.ERROR_MESSAGE
				);
				continue;
			}
			return puerto;
		}
	}
	
	private int preguntarPuertoSalida() {
		while (true) {
			int puerto = preguntarPuerto(
					"Dime el puerto por donde quieres conectarte al otro usuario", 
					"Introduce puerto de salida",
					"9001"
			);
			return puerto;
		}
	}
}