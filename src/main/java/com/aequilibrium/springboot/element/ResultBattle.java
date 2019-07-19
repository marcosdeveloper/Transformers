package com.aequilibrium.springboot.element;

/**
 * Class responsible for delivery the required response
 */

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResultBattle {
	
	private int numberOfBattles;
	private String winningTeam;
	private List<Transformer> listOfWinningTeam;
	private String losingTeam;
	private List<Transformer> listOfSurvivalLosingTeam;
	
	public int getNumberOfBattles() {
		return numberOfBattles;
	}
	public void setNumberOfBattles(int numberOfBattles) {
		this.numberOfBattles = numberOfBattles;
	}
	public String getWinningTeam() {
		return winningTeam;
	}
	public void setWinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}
	public List<Transformer> getListOfWinningTeam() {
		return listOfWinningTeam;
	}
	public void setListOfWinningTeam(List<Transformer> listOfWinningTeam) {
		this.listOfWinningTeam = listOfWinningTeam;
	}
	public String getLosingTeam() {
		return losingTeam;
	}
	public void setLosingTeam(String losingTeam) {
		this.losingTeam = losingTeam;
	}
	public List<Transformer> getListOfSurvivalLosingTeam() {
		return listOfSurvivalLosingTeam;
	}
	public void setListOfSurvivalLosingTeam(List<Transformer> listOfSurvivalLosingTeam) {
		this.listOfSurvivalLosingTeam = listOfSurvivalLosingTeam;
	}
}
