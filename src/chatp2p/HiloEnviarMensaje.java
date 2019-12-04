package chatp2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloEnviarMensaje extends Thread {
	private final Controlador controlador;
	private final byte[] mensajeBytes;
	
	public HiloEnviarMensaje(Controlador controlador, byte[] mensajeBytes) {
		this.controlador = controlador;
		this.mensajeBytes = mensajeBytes;
	}
	
	@Override
	public void run() {
		DatagramSocket datagramSocket = null;
		
		try {
			datagramSocket = new DatagramSocket();
			DatagramPacket datagramPacket = new DatagramPacket(
					mensajeBytes, 
					mensajeBytes.length, 
					InetAddress.getByName(controlador.hostSalida), 
					controlador.puertoSalida
			);
			
			datagramSocket.send(datagramPacket);
			datagramSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (datagramSocket != null) {
				datagramSocket.close();
			}
		}
	}
}
