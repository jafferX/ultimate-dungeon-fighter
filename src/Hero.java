//Hero.java

public class Hero
{
	int maxHealth = 300, maxAttack = 25, maxDefense = 15, specialAttack = 0;
	int currentHealth, currentAttack, currentDefense;

	//Constructor
	public Hero()
	{
		currentHealth = maxHealth;
		currentAttack = maxAttack;
		currentDefense = maxDefense;
	}

	//Resets health values and special attack at the start of each battle
	public void resetValuesAtStart()
	{
		currentHealth = maxHealth;
		currentAttack = maxAttack;
		currentDefense = maxDefense;
		specialAttack = 0;
	}
	
	public void resetValuesInBattle()
	{
		currentAttack = maxAttack;
		currentDefense = maxDefense;
	}

	//Increments the special attack up by one after each turn
	public void incrementSpecialAttack()
	{
		specialAttack++;
	}
}