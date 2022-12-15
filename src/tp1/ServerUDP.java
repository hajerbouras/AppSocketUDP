package tp1;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerUDP {
	
	public static List<Etudiant> le = new ArrayList<Etudiant>();
	public static List<Group> lg = new ArrayList<Group>();
	public static void main(String[] args) throws Exception{
	String host="127.0.0.1";
	int portServ=3000;
	DatagramSocket sc = new DatagramSocket(portServ);
	ServerGestion ser = new ServerGestion(sc);
	ser.start();
}

}
