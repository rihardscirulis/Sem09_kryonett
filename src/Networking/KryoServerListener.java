package Networking;

import java.sql.SQLException;
import java.util.ArrayList;
import com.esotericsoftware.kryonet.Listener;
import java.util.Random;
import com.esotericsoftware.kryonet.Connection;

public class KryoServerListener extends Listener{
	public static ArrayList<Connection> connections=new ArrayList<>();
	ArrayList<Integer> winningNumbers=new ArrayList<>();
	
	public KryoServerListener()  {
		Random random=new Random();
		int temp;
		while(winningNumbers.size()<5) {
			temp=random.nextInt(35)+1;
			if(!(winningNumbers.contains(Integer.valueOf(temp)))) {
				winningNumbers.add(Integer.valueOf(temp));
			}
		}
	}
	
	@Override
	public void connected(Connection connect) {
		System.out.println("=== >> BINGO serveris << ===");
		System.out.println("== Kads ir pievienojies serverim ==");
		System.out.println("");
		//Izvada generetus laimigos skaitlus
		System.out.print("Laimigie skaitli: ");
		for(Integer number : winningNumbers) {
			System.out.print(" "+number.intValue());
		}
		System.out.println();
		connections.add(connect);
	}
	
	@Override
	public void disconnected(Connection disconnect) {
		System.out.println("");
		System.out.println("== Kads ir atvienojies no servera ==");
		connections.remove(disconnect);
	}
	
	@Override
	public void received(Connection connect, Object obj) {
		if(obj instanceof Packet.Packet01Message) {
			Packet.Packet01Message p=(Packet.Packet01Message) obj;
			System.out.println("[CLIENT] >> "+p.message);
		}
		//Iegust variaciju
		if(obj instanceof Variation) {
			Variation userVariation=(Variation) obj;
			int correctNumbers=0;
			for(Integer number : winningNumbers) {
				if(userVariation.getSelectedNumbers().contains(number)) {
					correctNumbers++;
				}
			}
			userVariation.setCorrectNumbers(correctNumbers);
			System.out.println("");
			System.out.println("Loterijas dalibnieka e-pasts: "+userVariation.getEmail());
			System.out.println("Variacijas Nr.: "+userVariation.getVariationNr());
			for(int i=0; i<userVariation.getSelectedNumbers().size(); i++) {
				System.out.print(userVariation.getSelectedNumbers().get(i)+" ");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Laimigie skaitli: "+userVariation.getCorrectNumbers());
			
			/*ActionsWithDB actionWithDatabase=new ActionsWithDB();
			try {
				actionWithDatabase.createConnection();
				actionWithDatabase.createTableForVariations();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				actionWithDatabase.insertNewRecord(userVariation);
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
	}
}
