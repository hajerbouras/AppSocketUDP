package tp1;

import java.net.DatagramSocket;

public class ServerReceiv extends Thread {
	
	DatagramSocket sc ;

	public ServerReceiv(DatagramSocket sc) {
		super();
		this.sc = sc;
		}

}
