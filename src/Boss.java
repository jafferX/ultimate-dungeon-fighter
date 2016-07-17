import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Boss.java

import java.util.Scanner;
import java.io.*;

public class Boss 
{
	Queue bossPattern = new Queue();
	
	int health = 2500, maxAttack = 150, maxDefense = 45;
	int attack, defense;
	
	public Boss() throws IOException
	{
		defense = maxDefense;
		
		Scanner file = new Scanner(new File("bossqueue.txt"));
		
		while(file.hasNext())
		{
			String string = file.nextLine();
			bossPattern.insert(Integer.parseInt(string));
		}
	}
	
	public void resetValues()
	{
		attack = maxAttack;
		defense = maxDefense;
	}
}
