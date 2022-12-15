package tp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveEtudiant extends Thread {
	
	DatagramSocket sc ;

	public ReceiveEtudiant(DatagramSocket sc) {
		super();
		this.sc = sc;
		}

	public void run() {
		
		try {
			
			byte[] dataR = new byte [1024] ;
			DatagramPacket pkReceive = new DatagramPacket(dataR,dataR.length);
			sc.receive(pkReceive);
			System.out.println("Message de Serveur "+ new String(pkReceive.getData(),0,pkReceive.getLength()));
			
		}catch (IOException e) {
			
			e.printStackTrace();
}
	}

}
