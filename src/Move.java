//Move.java

import java.util.Scanner;
import java.io.*;
public class Move 
{
	private int monster[] = new int[9];
	private BattleClass battle = new BattleClass();
	
	public Move()
	{
		monster[0] = 0;
		monster[8] = 2;
		for(int i = 1; i <= 7; i++)
			monster[i] = 1;
	}
	
	public int[] makeMove(ObjectList list, int[] p, Hero hero) throws IOException
	{
		ObjectListNode pointer = list.getFirstNode();
		Scanner keyboard = new Scanner(System.in);
		boolean isValid = true;
		int answer, newRoom;
		
		for(int i = 0; i < 9; i++)
		{
			if(p[i] == 0)
				pointer = pointer.getNext();
			else
				break;
		}
		
		while(isValid)
		{
			System.out.print("Please enter a valid room number (1 - 9): ");
			answer = keyboard.nextInt();
			System.out.println("");
			
			//Checks for valid input
			while(answer < 1 || answer > 9)
			{
				System.out.println("Not a valid room number.");
				System.out.print("Please enter a valid room number (1 - 9): ");
				answer = keyboard.nextInt();
			}
			
			Object temp[] = pointer.getInfo();
			
			if((int)temp[answer - 1] == 1)
			{
				for(int j = 0; j <= 8; j++)
					p[j] = 0;
				
				p[answer - 1] = 1;
				isValid = false;
			}
			else
			{
				System.out.println("Invalid room number.");
			}
		}
		
		//Checks if new room is already visited, has a monster, or is the boss room
		for(newRoom = 0; newRoom < 9; newRoom++)
		{
			if(p[newRoom] == 1)
				break;
		}
		
		if(monster[newRoom] == 0)
		{
			System.out.println("Room already visited.");
			System.out.println("");
		}
		else if(monster[newRoom] == 1)
		{
			System.out.println("Warning: Monster!");
			System.out.println("");
			battle.engageMonster(hero);
			monster[newRoom] = 0;
		}
		else
		{
			System.out.println("Boss Warning: Charybdis Appeared!");
			System.out.println("");
			battle.engageBoss(hero);
		}
		
		return p;
	}
}
