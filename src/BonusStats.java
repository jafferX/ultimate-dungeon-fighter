//BonusStats.java

public class BonusStats 
{
	int bonusHealth, bonusAttack, bonusDefense;
	
	//Constructor
	public BonusStats()
	{
		bonusHealth = 120;
		bonusAttack = 50;
		bonusDefense = 25;
	}
	
	public void addBonusStats(Hero hero)
	{
		hero.maxHealth = hero.maxHealth + bonusHealth;
		hero.maxAttack = hero.maxAttack + bonusAttack;
		hero.maxDefense = hero.maxDefense + bonusDefense;
	}
}
