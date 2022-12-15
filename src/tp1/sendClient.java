package tp1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class sendClient extends Thread {
	
	DatagramSocket sc ;

	public sendClient(DatagramSocket sc) {
		super();
		this.sc = sc;
		}

	public void run(){
		while(true) {

		try {
			String host="127.0.0.1";
			int portServ=3000;
			BufferedReader inC = new BufferedReader(new InputStreamReader(System.in));
			InetAddress ipServeur = InetAddress.getByName(host);
			String m = inC.readLine();
			DatagramPacket pkS = new DatagramPacket(m.getBytes(),m.length(),ipServeur,portServ);
			sc.send(pkS);
		}
		catch(Exception e) {e.printStackTrace();}
	}
}

}
