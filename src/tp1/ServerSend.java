package tp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSend extends Thread {
	
	DatagramSocket sc ;

	public ServerSend(DatagramSocket sc) {
		super();
		this.sc = sc;
		}
	
	public void run() {
		try {
			while(true) {
		byte[] dataSend = new byte [1024] ;
		byte[] dataR = new byte [1024] ;
		DatagramPacket pkReceive = new DatagramPacket(dataR,dataR.length);
		sc.receive(pkReceive);
		String msj = new String(pkReceive.getData(),0,pkReceive.getLength());
		System.out.println("Message from Client "+pkReceive.getPort()+":  " +msj);
		String messageClient = new String(pkReceive.getData(),0,pkReceive.getLength());
		InetAddress ipAddress =pkReceive.getAddress();
		int port = pkReceive.getPort();
		String M2 = messageClient.toUpperCase();
		DatagramPacket pkS = new DatagramPacket(M2.getBytes(),M2.length(),ipAddress,port);
	sc.send(pkS);
			}
		}
		catch(Exception e) {e.printStackTrace();
	}
}

}
