package tryOne;

import java.net.InetAddress;

import java.net.UnknownHostException;
import main.Server;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      	try {
			System.out.println(String.valueOf(InetAddress.getLocalHost().getHostAddress()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR WITH IP ADDRESS");
			e.printStackTrace();
		}
      	
		Server.main();
		System.exit(0);
	}
	
}
