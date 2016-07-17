//Monster.java

import java.util.Scanner;
import java.io.*;

public class Monster 
{
	Queue monsterPattern = new Queue();
	
	int health = 100, attack = 25, maxDefense = 15;
	int defense;
	
	public Monster() throws IOException
	{
		defense = maxDefense;
		
		Scanner file = new Scanner(new File("monsterqueue.txt"));
		
		while(file.hasNext())
		{
			String string = file.nextLine();
			monsterPattern.insert(Integer.parseInt(string));
		}
	}
	
	public void resetValues()
	{
		defense = maxDefense;
	}
}
