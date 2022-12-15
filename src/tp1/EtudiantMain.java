package tp1;

import java.net.DatagramSocket;

public class EtudiantMain {
	
	public static void main(String[] args) throws Exception {

		DatagramSocket sc = new DatagramSocket();

		sendEtudiant send = new sendEtudiant(sc);
		send.start();
		

		ReceiveEtudiant rcv = new ReceiveEtudiant(sc);
		rcv.start();
	}


}
