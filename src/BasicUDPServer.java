import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BasicUDPServer {
	public static void main(String[] args) throws IOException {
		DatagramSocket udpSocket = new DatagramSocket(2345);
		DatagramPacket datagramReceived = new DatagramPacket(new byte[512], 512);
		while (true) {
			udpSocket.receive(datagramReceived);
			String messageReceived = new String(datagramReceived.getData(), 0, datagramReceived.getLength());
			byte[] answer = messageReceived.getBytes();
			DatagramPacket datagramSent = new DatagramPacket(answer, answer.length, datagramReceived.getAddress(),
					datagramReceived.getPort());
			udpSocket.send(datagramSent);
		}
	}
}