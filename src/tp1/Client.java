package tp1;

import java.net.DatagramSocket;

public class Client {
	

	public static void main(String[] args) throws Exception {

		DatagramSocket sc = new DatagramSocket();
		System.out.println("Client avec port "+sc.getPort());

		sendClient send = new sendClient(sc);
		send.start();
		

		ReceiveClient rcv = new ReceiveClient(sc);
		rcv.start();
	}

}
