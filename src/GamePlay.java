//GamePlay.java

import java.util.Scanner;
import java.io.*;

public class GamePlay 
{
	private ObjectList list = new ObjectList();

	/*
	*Constructor method
	*Creates map
	*/
	public GamePlay() throws IOException
	{
		Scanner file = new Scanner(new File("map.txt"));

		String[] tokens;
		
		while(file.hasNext())
		{
			Object connect[] = new Object[9];
			String string = file.nextLine();
			tokens = string.split("[ ]+");
			for(int i = 0; i < 9; i++)
			{
				connect[i] = Integer.parseInt(tokens[i]);
			}
			
			ObjectListNode node = new ObjectListNode();
			
			
			node.setInfo(connect);
			list.addLast(node);
		}	
	}
	
	//Reads the introduction, which gives instructions on how to play the game
	private void introduction() throws IOException
	{
		Scanner introFile = new Scanner(new File("introduction.txt"));
		
		while(introFile.hasNext())
			System.out.println(introFile.nextLine());
	}
	
	//Displays the map and the current room the player is in
	private void displayMap(int[] p)
	{
		int count;
		
		for(count = 0; count <= 8; count++)
		{
			if(p[count] == 1)
				break;
		}
		
		System.out.println(" _ _ _ _ _ _");
		System.out.println("|_1_|_2_|_3_|");
		System.out.println("|_4_|_5_|_6_|");
		System.out.println("|_7_|_8_|_9_|");
		System.out.println("Current room: " +(count + 1));
	}
	
	//Main function for the game
	public void mainGameFunction() throws IOException
	{
		int position[] = {1, 0, 0, 0, 0, 0, 0, 0, 0};
		Move turn = new Move();
		Hero hero = new Hero();
		boolean game = true;
		
		introduction();
		
		while(game)
		{
			displayMap(position);
			position = turn.makeMove(list, position, hero);
			
			if(hero.currentHealth <= 0)
			{
				System.out.println("");
				System.out.println("Game Over!");
				game = false;
			}
			if(position[8] == 1)
				game = false;
		}
	}
}
