//BattleClass.java

import java.util.Scanner;
import java.io.*;

public class BattleClass 
{
	public void engageMonster(Hero hero) throws IOException
	{
		Monster monster = new Monster();
		BonusStats bonus = new BonusStats();
		hero.resetValuesAtStart();
		int answer;
		Scanner input = new Scanner(System.in);
		boolean battle = true;
		
		while(battle)
		{
			System.out.println("HEALTH: " +hero.currentHealth+ " MONSTER HEALTH: " +monster.health);
			System.out.println("");
			System.out.println("1. Attack");
			System.out.println("2. Defend");
			System.out.println("3. Special Attack");
			System.out.println("4. Heal");
			System.out.print("Enter action: ");
			answer = input.nextInt();
			System.out.println("");
			
			switch(answer)
			{
				case 1: 
					heroAttack(hero, monster);
					break;
				case 2:
					heroDefend(hero);
					break;
				case 3:
					heroSpecial(hero, monster);
					break;
				case 4:
					heroHeal(hero);
					break;
				default:
					System.out.println("Incorrect input. Turn is skipped.");					
			}
			
			if(monster.health <= 0)
			{
				System.out.println("The monster died!");
				bonus.addBonusStats(hero);
				break;
			}
			
			monster.resetValues();
			
			if(monster.monsterPattern.query() == 1)
				monsterAttack(hero, monster);
			else
				monsterDefend(monster);
			
			if(hero.currentHealth <= 0)
			{
				System.out.println("You died!");
				break;
			}
			
			System.out.println("");
			
			hero.resetValuesInBattle();
			hero.specialAttack++;
		}
	}
	
	public void engageBoss(Hero hero) throws IOException
	{
		Boss boss = new Boss();
		BonusStats bonus = new BonusStats();
		hero.resetValuesAtStart();
		int answer;
		Scanner input = new Scanner(System.in);
		boolean battle = true;
		
		while(battle)
		{
			System.out.println("HEALTH: " +hero.currentHealth+ " MONSTER HEALTH: " +boss.health);
			System.out.println("");
			System.out.println("1. Attack");
			System.out.println("2. Defend");
			System.out.println("3. Special Attack");
			System.out.println("4. Heal");
			System.out.print("Enter action: ");
			answer = input.nextInt();
			System.out.println("");
			
			switch(answer)
			{
				case 1: 
					heroAttack(hero, boss);
					break;
				case 2:
					heroDefend(hero);
					break;
				case 3:
					heroSpecial(hero, boss);
					break;
				case 4:
					heroHeal(hero);
					break;
				default:
					System.out.println("Incorrect input. Turn is skipped.");					
			}
			
			if(boss.health <= 0)
			{
				System.out.println("Charybdis died!");
				System.out.println("");
				System.out.println("You win!");
				bonus.addBonusStats(hero);
				break;
			}
			
			boss.resetValues();
			
			if(boss.bossPattern.query() == 1)
				monsterAttack(hero, boss);
			else if(boss.bossPattern.query() == 2)
				monsterDefend(boss);
			else 
				bossSpecial(hero, boss);
			
			if(hero.currentHealth <= 0)
			{
				System.out.println("You died!");
				break;
			}
			
			System.out.println("");
			
			hero.resetValuesInBattle();
			hero.specialAttack++;
		}
	}
	
	//Hero attacks monster
	private void heroAttack(Hero hero, Monster monster)
	{
		System.out.println("You do " +(hero.currentAttack - monster.defense)+ " damage!");
		monster.health = monster.health - (hero.currentAttack - monster.defense);
	}
	
	//Hero attacks boss
	private void heroAttack(Hero hero, Boss boss)
	{
		System.out.println("You do " +(hero.currentAttack - boss.defense)+ " damage!");
		boss.health = boss.health - (hero.currentAttack - boss.defense);
	}
	
	private void heroDefend(Hero hero)
	{
		System.out.println("Your defense doubles!");
		hero.currentDefense = hero.currentDefense * 2;
	}
	
	//Hero uses special on monster
	private void heroSpecial(Hero hero, Monster monster)
	{
		if(hero.specialAttack >= 2)
		{
			System.out.println("OMEGA SLASH!");
			monster.health = monster.health - ((hero.currentAttack * hero.specialAttack) - monster.defense);
			System.out.println("You do " +((hero.currentAttack * hero.specialAttack) - monster.defense)+ " damage!");
			hero.specialAttack = 0;
		}
		else
			System.out.println("Cannot do special. Your turn is skipped!");		
	}
	
	//Hero uses special on boss
	private void heroSpecial(Hero hero, Boss boss)
	{
		if(hero.specialAttack >= 2)
		{
			System.out.println("OMEGA SLASH!");
			boss.health = boss.health - ((hero.currentAttack * hero.specialAttack) - boss.defense);
			System.out.println("You do " +((hero.currentAttack * hero.specialAttack) - boss.defense)+ " damage!");
			hero.specialAttack = 0;
		}
		else
			System.out.println("Cannot do special. Your turn is skipped!");		
	}
	
	//Hero heals
	private void heroHeal(Hero hero)
	{
		System.out.println("You healed yourself by " +(hero.currentDefense * 3)+ " health!");
		hero.currentHealth = hero.currentHealth +(hero.currentDefense * 3);
		
		if(hero.currentHealth > hero.maxHealth)
			hero.currentHealth = hero.maxHealth;
	}
	
	//Monster attacks hero
	private void monsterAttack(Hero hero, Monster monster)
	{
		if(monster.attack > hero.currentDefense)
		{
			System.out.println("Monster does " +(monster.attack - hero.currentDefense)+ " damage!");
			hero.currentHealth = hero.currentHealth - (monster.attack - hero.currentDefense);
		}
		else 
		{
			System.out.println("Monster does 1 damage!");
			hero.currentHealth = hero.currentHealth - 1;
		}	
		
		monster.monsterPattern.insert(monster.monsterPattern.remove());
	}
	
	//Boss attacks hero
	private void monsterAttack(Hero hero, Boss boss)
	{
		if(boss.attack > hero.currentDefense)
		{
			System.out.println("Charybdis does " +(boss.attack - hero.currentDefense)+ " damage!");
			hero.currentHealth = hero.currentHealth - (boss.attack - hero.currentDefense);
		}
		else 
		{
			System.out.println("Charybdis does 1 damage!");
			hero.currentHealth = hero.currentHealth - 1;
		}		
		
		boss.bossPattern.insert(boss.bossPattern.remove());
	}
	
	//Monster defends
	private void monsterDefend(Monster monster)
	{
		System.out.println("The monster's defense doubles!");
		monster.defense = monster.defense * 2;
		monster.monsterPattern.insert(monster.monsterPattern.remove());
	}
	
	//Boss defends
	private void monsterDefend(Boss boss)
	{
		System.out.println("The boss's defense doubles!");
		boss.defense = boss.defense * 2;
		boss.bossPattern.insert(boss.bossPattern.remove());
	}
	
	private void bossSpecial(Hero hero, Boss boss)
	{
		if(boss.bossPattern.query() == 3)
		{
			System.out.println("Charybdis unleashes COCYTUS ZERO!");
			System.out.println("Charybdis does " +((boss.attack * 3) - hero.currentDefense)+ " damage!");
			hero.currentHealth = hero.currentHealth - (boss.attack * 3 - hero.currentDefense);
		}
		else 
		{
			System.out.println("Charybdis activates Regeneration.");
			System.out.println("Regeneration heals " +(boss.defense * 2)+ " health!");
			boss.health = boss.health + (boss.defense * 10);
		}
		
		boss.bossPattern.insert(boss.bossPattern.remove());
	}
}
