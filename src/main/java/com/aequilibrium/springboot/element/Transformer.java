package com.aequilibrium.springboot.element;

/**
 * Class represents the object Transformer
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Transformer {
	
	private String name;
	private String teamMember;
	private int strenght;
	private int intelligence;
	private int speed;
	private int endurance;
	private int rank;
	private int courage;
	private int firepower;
	private int skill;
	private boolean alreadyInTheBattle;
	private String loserOrWinning;
	
	public Transformer() {
		super();
	}
	
	public Transformer(String name, String teamMember, int strenght, int intelligence, int speed, int endurance,
			int rank, int courage, int firepower, int skill, boolean alreadyInTheBattle) {
		super();
		this.name = name;
		this.teamMember = teamMember;
		this.strenght = strenght;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
		this.alreadyInTheBattle = alreadyInTheBattle;
	}
	
	@ApiModelProperty(position = 1, required = true, value = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ApiModelProperty(position = 2, required = true, value = "Team member can contain A or D")
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	@ApiModelProperty(position = 3, required = true, value = "Strenght")
	public int getStrenght() {
		return strenght;
	}
	public void setStrenght(short strenght) {
		this.strenght = strenght;
	}
	@ApiModelProperty(position = 4, required = true, value = "Intelligence")
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(short intelligence) {
		this.intelligence = intelligence;
	}
	@ApiModelProperty(position = 5, required = true, value = "Speed")
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(short speed) {
		this.speed = speed;
	}
	@ApiModelProperty(position = 6, required = true, value = "Endurance")
	public int getEndurance() {
		return endurance;
	}
	public void setEndurance(short endurance) {
		this.endurance = endurance;
	}
	@ApiModelProperty(position = 7, required = true, value = "Rank")
	public int getRank() {
		return rank;
	}
	public void setRank(short rank) {
		this.rank = rank;
	}
	@ApiModelProperty(position = 8, required = true, value = "Courage")
	public int getCourage() {
		return courage;
	}
	public void setCourage(short courage) {
		this.courage = courage;
	}
	@ApiModelProperty(position = 9, required = true, value = "Fire Power")
	public int getFirepower() {
		return firepower;
	}
	public void setFirepower(short firepower) {
		this.firepower = firepower;
	}
	@ApiModelProperty(position = 10, required = true, value = "Skill")
	public int getSkill() {
		return skill;
	}
	public void setSkill(short skill) {
		this.skill = skill;
	}
	@ApiModelProperty(position = 11, required = false, value = "this field will be calculated during the process")
	public int getOverallRating() {
		return this.strenght + this.intelligence + this.speed + this.endurance + this.firepower;
	}
	@ApiModelProperty(position = 12, required = false, value = "this field will be used during the process")
	public boolean isAlreadyInTheBattle() {
		return alreadyInTheBattle;
	}
	public void setAlreadyInTheBattle(boolean alreadyInTheBattle) {
		this.alreadyInTheBattle = alreadyInTheBattle;
	}
	@ApiModelProperty(position = 13, required = false, value = "this field will be calculated during the process")
	public String getLoserOrWinning() {
		return loserOrWinning;
	}
	public void setLoserOrWinning(String loserOrWinning) {
		this.loserOrWinning = loserOrWinning;
	}
}