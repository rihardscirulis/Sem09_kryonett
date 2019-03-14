package Networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

public class KryoClientListener extends Listener{
	private Client client;
	
	public void initialize(Client client) {
		this.client=client;
	}
	
	@Override
	public void connected(Connection connect){
        System.out.println("[CLIENT] >> == Esat pievienojies serverim ==");
    }
	
	@Override
	public void disconnected(Connection disconnect){
        System.out.println("[CLIENT] >> == Esat atvienojies no servera! ==");
    }
	
	@Override
	public void received(Connection connect, Object obj){ 
        if(obj instanceof Packet.Packet01Message){
            Packet.Packet01Message p = (Packet.Packet01Message) obj;
            System.out.println("[SERVER] >> " + p.message);
        }
    }
}
