package Networking;

import java.io.IOException;
import java.util.ArrayList;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import Networking.Packet.Packet01Message;

public class KryoClient {
	//Kryonet mainigie
		public Client client;
		private KryoClientListener kcl;
		
		public KryoClient() {
			//Inicialize Kryonet mainigos
			client=new Client();
			kcl=new KryoClientListener();
			
			//Pievieno
			kcl.initialize(client);
			client.addListener(kcl);
			registerPackets();
			
			//Palaiz klientu
			client.start();
			try {
				client.connect(5000, "127.0.0.1", 8001);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//Suta paketes
		private void registerPackets() {
			Kryo kryo=client.getKryo();
			kryo.register(Packet01Message.class);
			kryo.register(Packet.Packet02Variation.class);
			kryo.register(Variation.class);
			kryo.register(ArrayList.class);
		}
}
