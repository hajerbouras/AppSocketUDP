package tp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerGestion extends Thread {
	
	DatagramSocket sc ;

	public ServerGestion(DatagramSocket sc) {
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
		
		String messageEtudiant = new String(pkReceive.getData(),0,pkReceive.getLength());

		InetAddress ipAddress =pkReceive.getAddress();
		int port = pkReceive.getPort();
		int pos = getIndexEt ( port );
		
		if (messageEtudiant.startsWith("##")) {
			String m1= messageEtudiant.substring(2);
			Boolean x1= false;
			for(Etudiant e:ServerUDP.le) {
				if(e.getPort()==port  ){
					x1=true;
					String m3 ="Attention ! vous etes deja connecte avec pseudo "+e.getLogin();
					DatagramPacket pkS3 = new DatagramPacket(m3.getBytes(),m3.length(),ipAddress,port);
					sc.send(pkS3);
				}
			}
			if(x1==false) {
				Etudiant e = new Etudiant();
				e.setAddr(pkReceive.getAddress());
				e.setLogin(m1);
				e.setIsConnected(true);
				e.setPort(pkReceive.getPort());
				ServerUDP.le.add(e);
				String m3 ="Etudiant cree et connecté";
				DatagramPacket pkS1 = new DatagramPacket(m3.getBytes(),m3.length(),ipAddress,port);
				sc.send(pkS1);
			}
			
		}
		
		
		else if(messageEtudiant.startsWith("#LIST_EDTS")) {
		
		for(Etudiant e : ServerUDP.le) {
			String et= e.getLogin()+"/ "+e.getPort();
			DatagramPacket pkS2 = new DatagramPacket(et.getBytes(),et.length(),ipAddress,port);
			sc.send(pkS2);

			}
		}
		else if (messageEtudiant.startsWith("#HISTO")) {
			for(String m6: ServerUDP.le.get(getIndexEt(port)).getListM())
			{
				
				DatagramPacket pkS6 = new DatagramPacket(m6.getBytes(),m6.length(),ipAddress,port);
				sc.send(pkS6);
			}
		}
		else if (messageEtudiant.startsWith("@#")) {
		String m9[]=messageEtudiant.split("@#");
		String loginSent= m9[1];
		Etudiant e1 =getEtudiantByPort(port);
		String Message = e1.getLogin()+":  "+m9[2];
		boolean x1 =false;
		for(Etudiant e:ServerUDP.le) {
			if(e.getLogin().equals(loginSent))
			{x1=true;
				DatagramPacket pkS9 = new DatagramPacket(Message.getBytes(),Message.length(),e.getAddr(),e.getPort());
				sc.send(pkS9);
				ServerUDP.le.get(pos).getListM().add(Message);

			}
		}
		if(x1==false) {
			String m10="User is not found ";
			DatagramPacket pkS10 = new DatagramPacket(m10.getBytes(),m10.length(),ipAddress,port);
			sc.send(pkS10);
		}
		}
		else if (messageEtudiant.startsWith("#GROUPS")) {
			int c=1;
			for(Group g : ServerUDP.lg) {
				String m12 ="le group n° "+c+" "+g.getTitre();
				c++;
				DatagramPacket pkS10 = new DatagramPacket(m12.getBytes(),m12.length()+1,ipAddress,port);
				sc.send(pkS10);

			}
		}
		else if(messageEtudiant.startsWith("#GROUP#")) {
			String m =messageEtudiant.substring(7);
			boolean x=false;
			for(Group g : ServerUDP.lg) {
				if(g.getTitre().equals(m)) {
					x=true;
					String m1="group"+g.getTitre()+" already exist";
					DatagramPacket pkS10 = new DatagramPacket(m1.getBytes(),m1.length(),ipAddress,port);
					sc.send(pkS10);
				}

			}
			if(x==false) {
				Group g = new Group();
				g.setTitre(m);
				ServerUDP.lg.add(g);
				DatagramPacket pkS10 = new DatagramPacket("Group Added!".getBytes(),"Group Added!".length(),ipAddress,port);
				sc.send(pkS10);
			}
		}
		else if (messageEtudiant.startsWith("#>")) {
			String m3= messageEtudiant.substring(2);
			boolean x= false;
			int c=0;
			for(Group g : ServerUDP.lg) {
				c++;
				if(m3.equals(g.getTitre())) {
					x=true;
					Etudiant e = getEtudiantByPort(port);
					ServerUDP.lg.get(c-1).getLe().add(e);
					DatagramPacket pkS10 = new DatagramPacket("you are Added to the group!".getBytes(),"you are Added to the group!".length(),ipAddress,port);
					sc.send(pkS10);
				}
			}
				if(x==false) {
					DatagramPacket pkS10 = new DatagramPacket("Sorry group doesn't exist".getBytes(),"Sorry group doesn't exist".length(),ipAddress,port);
					sc.send(pkS10);
				}
			
		}
		else if (messageEtudiant.startsWith("#ETDS#")) {
			String m= messageEtudiant.substring(6);
			boolean x=false;
			int c=0;
			for(Group g : ServerUDP.lg) {
				c++;
				if(m.equals(g.getTitre())) {
					x=true;
					for(Etudiant e : ServerUDP.lg.get(c-1).getLe()) {
						String m1=e.getLogin() + "/ "+e.getPort();
						DatagramPacket pkS10 = new DatagramPacket(m1.getBytes(),m1.length(),ipAddress,port);
						sc.send(pkS10);
					}
		}
			}
			if(x==false) {
				DatagramPacket pkS10 = new DatagramPacket("Sorry group doesn't exist".getBytes(),"Sorry group doesn't exist".length(),ipAddress,port);
				sc.send(pkS10);
			}
		}
		
		else if(messageEtudiant.startsWith("@>")) {
			int c=0;
			boolean x=false;
			String[] t= messageEtudiant.split("@>");
			String titreGroup= t[1];
			Etudiant e1 =getEtudiantByPort(port);
			String message=e1.getLogin()+":  "+t[2];
			for(Group g : ServerUDP.lg) {
				c++;
				if(titreGroup.equals(g.getTitre())) {
					x=true;
					for(Etudiant e : ServerUDP.lg.get(c-1).getLe()) {
						
						DatagramPacket pkS10 = new DatagramPacket(message.getBytes(),message.length(),e.getAddr(),e.getPort());
						sc.send(pkS10);
					}
				}
		}
			if(x==false) {
				DatagramPacket pkS10 = new DatagramPacket("Sorry group doesn't exist".getBytes(),"Sorry group doesn't exist".length(),ipAddress,port);
				sc.send(pkS10);
			}
		}
		else {
String m4 ="verifier votre commande !!!";
DatagramPacket pkS3 = new DatagramPacket(m4.getBytes(),m4.length(),ipAddress,port);
sc.send(pkS3);
		}
			}
		}catch(Exception e) {e.printStackTrace();
	
}
}
	
	
	public int getIndexEt (int port ) {
		int c= 0;
		for(Etudiant e :ServerUDP.le) {
			c++;
			if(e.getPort()==port) {
				return c-1;
			}
		}
		return -1;
	}
	public Etudiant  getEtudiantByPort(int port ) {
		for(Etudiant e : ServerUDP.le) {
			if(e.getPort()==port) {
				return  e;
			}
		}
		return null;
	}


}
