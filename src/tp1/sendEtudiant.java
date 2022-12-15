package tp1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class sendEtudiant extends Thread {
	
	DatagramSocket sc ;
	int portServ=3000;
	String host="172.0.0.1";
	

	public sendEtudiant(DatagramSocket sc) {
		super();
		this.sc = sc;
	}

	public void run(){
		BufferedReader inC = new BufferedReader(new InputStreamReader(System.in));
		String m="" ;
		try {
			InetAddress ipServeur = InetAddress.getByName(host);
			do {
				System.out.println("Entrer votre cordonne avec ## ");
				m = inC.readLine();

				}
			while(!m.startsWith("##"));
				DatagramPacket pkS = new DatagramPacket(m.getBytes(),m.length(),ipServeur,portServ);
				sc.send(pkS);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			while(true) {

				try {
					InetAddress ipServeur = InetAddress.getByName(host);

					String m2= inC.readLine();
					
					DatagramPacket pkS1 = new DatagramPacket(m2.getBytes(),m2.length(),ipServeur,portServ);
					sc.send(pkS1);
				}
				catch(Exception e) {e.printStackTrace();}
			}
	}

}
