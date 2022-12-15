package tp1;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
	
	private String login;
	private int niveau,port;
	private Boolean isConnected;
	private List<String> listM;

	public Boolean getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}
	public Etudiant() {
		super();
		listM=new ArrayList<String>();
	}
	public Etudiant(String nom, String login, int niveau, int port, InetAddress addr,List<String> listM) {
		super();
		this.login = login;
		this.niveau = niveau;
		this.port = port;
		this.addr = addr;
		listM= new ArrayList<String>();
	}
	public List<String> getListM() {
		return listM;
	}
	public void setListM(List<String> listM) {
		this.listM = listM;
	}
	@Override
	public String toString() {
		return "Etudiant [ login=" + login + ", niveau=" + niveau + ", port=" + port + ", addr=" + addr
				+ "]";
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public InetAddress getAddr() {
		return addr;
	}
	public void setAddr(InetAddress addr) {
		this.addr = addr;
	}
	private InetAddress addr;

}
