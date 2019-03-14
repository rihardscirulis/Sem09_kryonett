package Networking;

import java.io.IOException;
import java.util.ArrayList;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

public class KryoServer {
	//Kryonet "Server" objekti
	Server server;
	KryoServerListener ksl;
		
	public KryoServer() throws IOException {
		server=new Server();
		ksl=new KryoServerListener();
		server.addListener(ksl);
		try {
			server.bind(8001);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			registerPackets();
			server.start();
		}
		
		//Suta paketes
		private void registerPackets(){
	        Kryo kryo = server.getKryo();
	        kryo.register(Packet.Packet01Message.class);    
	        kryo.register(Packet.Packet02Variation.class);
	        kryo.register(Variation.class);
	        kryo.register(ArrayList.class);
	    }
}
