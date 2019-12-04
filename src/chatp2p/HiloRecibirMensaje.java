package chatp2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HiloRecibirMensaje extends Thread {
	private final Controlador controlador;
	
	public HiloRecibirMensaje(Controlador controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		try {
			DatagramSocket udpSocket = new DatagramSocket(controlador.puertoEntrada);
			DatagramPacket datagramReceived = new DatagramPacket(new byte[512], 512);
			
			while (true) {
				udpSocket.receive(datagramReceived);
				
				String messageReceived = new String(
						datagramReceived.getData(), 0, 
						datagramReceived.getLength());
				
				controlador.vista.mensajesModel.addElement(messageReceived);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
