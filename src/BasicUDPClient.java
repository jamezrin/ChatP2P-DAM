import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BasicUDPClient {
	private final static String message = "el cliente envia el mensaje ";

	public static void main(String[] args) throws Exception {
		DatagramSocket udpSocket = new DatagramSocket();
		for (int i = 0; i < 10; i++) {
			String messageSent = message + i;
			byte[] datos = messageSent.getBytes();
			DatagramPacket datagram = new DatagramPacket(datos, datos.length, InetAddress.getByName("localhost"), 2345);
			udpSocket.send(datagram);
			System.out.println(">>>>>" + messageSent);
			datagram = new DatagramPacket(new byte[512], 512);
			udpSocket.receive(datagram);
			String messageReceived = new String(datagram.getData(), 0, datagram.getLength());
			System.out.println("<<<<<" + messageReceived);
			Thread.sleep(1000);
		}
	}
}