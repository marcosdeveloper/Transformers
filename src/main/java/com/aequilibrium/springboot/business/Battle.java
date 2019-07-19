package com.aequilibrium.springboot.business;

/**
 * Class responsible for executing the battles and their logics
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.aequilibrium.springboot.constants.TransformerConstant;
import com.aequilibrium.springboot.element.ResultBattle;
import com.aequilibrium.springboot.element.Transformer;

public class Battle {
	
	List<Transformer> listAutobot;
	List<Transformer> listDecepticon;
	Boolean alreadyOptimus = Boolean.FALSE;
	Boolean alreadyPredaking = Boolean.FALSE;
	int numberBattles;
	int numberAutobotWinning;
	int numberDecepticonWinning;
	ResultBattle resultBattle;
	Boolean continueBattle = Boolean.TRUE;
	
	/*
	 * Start the settup action
	 */
	public void action(HashMap<String, Transformer> mapAutobot, HashMap<String, Transformer> mapDecepticon) {
		listAutobot = new ArrayList<Transformer>(mapAutobot.values());
		listDecepticon = new ArrayList<Transformer>(mapDecepticon.values());
		sortArmy();
		startBattle();
		resultsOfBattle();
	}
	
	/*
	 * Sort each army depending of Overall criteria
	 */
	public void sortArmy() {
		listAutobot.sort(Comparator.comparing(Transformer::getOverallRating).reversed());
		listDecepticon.sort(Comparator.comparing(Transformer::getOverallRating).reversed());
	}
	
	/*
	 * Executes the battle
	 */
	public void startBattle() {
		listAutobot.forEach(autobot -> {
			listDecepticon.forEach(decepticon -> applyRules(autobot, decepticon));
		});
	}
	
	/**
	 * apply rules
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void applyRules(Transformer autobot, Transformer decepticon) {
		if (!autobot.isAlreadyInTheBattle() && !decepticon.isAlreadyInTheBattle()) {
			autobot.setAlreadyInTheBattle(Boolean.TRUE);
			decepticon.setAlreadyInTheBattle(Boolean.TRUE);
			numberBattles++;
			ruleOptimusPredaking(autobot, decepticon);
			ruleCourageAndStrenght(autobot, decepticon);
			ruleSkill(autobot, decepticon);
			ruleHighestOverallRating(autobot, decepticon);
			continueBattle = Boolean.TRUE;
		}
	}
	
	/**
	 * apply rule name Optimus and Predaking
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void ruleOptimusPredaking(Transformer autobot, Transformer decepticon) {
		if ((autobot.getName().equals(TransformerConstant.OPTIMUS_PRIME) && decepticon.getName().equals(TransformerConstant.PREDAKING)) || alreadyOptimus || alreadyPredaking) {
			listAutobot = new ArrayList<Transformer>();
			listDecepticon = new ArrayList<Transformer>();
			continueBattle = Boolean.FALSE;
		}
		else if (autobot.getName().equals(TransformerConstant.OPTIMUS_PRIME)) {
			setAutobotWinnerAndDecpticonLoser(autobot, decepticon);
			alreadyOptimus = Boolean.TRUE;
			continueBattle = Boolean.FALSE;
		} else if (decepticon.getName().equals(TransformerConstant.PREDAKING)) {
			setAutobotWinnerAndDecpticonLoser(decepticon, autobot);
			alreadyPredaking = Boolean.TRUE;
			continueBattle = Boolean.FALSE;
		}
	}

	/**
	 * apply rule courage and Strenght
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void ruleCourageAndStrenght(Transformer autobot, Transformer decepticon) {
		if (continueBattle) {
			if (autobot.getCourage() - decepticon.getCourage() <= -4 && autobot.getStrenght() - decepticon.getStrenght() <= -3) {
				setAutobotLoserAndDecpticonWinner(autobot, decepticon);
				continueBattle = Boolean.FALSE;
			} else if (decepticon.getCourage() - autobot.getCourage() <= -4 && decepticon.getStrenght() - autobot.getStrenght() <= -3) {
				setAutobotLoserAndDecpticonWinner(decepticon, autobot);
				continueBattle = Boolean.FALSE;
			}
		}
	}
	
	/**
	 * apply rule Skill
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void ruleSkill(Transformer autobot, Transformer decepticon) {
		if (continueBattle) {
			if (autobot.getSkill() - decepticon.getSkill() <= -3) {
				setAutobotLoserAndDecpticonWinner(autobot, decepticon);
				continueBattle = Boolean.FALSE;
			} else if (decepticon.getSkill() - autobot.getSkill() <= -3) {
				setAutobotLoserAndDecpticonWinner(decepticon, autobot);
				continueBattle = Boolean.FALSE;
			}
		}
	}

	/**
	 * apply rule overall rating
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void ruleHighestOverallRating(Transformer autobot, Transformer decepticon) {
		if (continueBattle) {
			if (autobot.getOverallRating() < decepticon.getOverallRating()) {
				setAutobotLoserAndDecpticonWinner(autobot, decepticon);
				continueBattle = Boolean.FALSE;
			} else if (decepticon.getOverallRating() < autobot.getOverallRating()) {
				setAutobotLoserAndDecpticonWinner(decepticon, autobot);
				continueBattle = Boolean.FALSE;
			} else {
				decepticon.setLoserOrWinning(TransformerConstant.LOSER);
				autobot.setLoserOrWinning(TransformerConstant.LOSER);
				continueBattle = Boolean.FALSE;
			}
		}
	}
	
	/**
	 * After battle listing the results
	 * @param Transformer autobot
	 * @param Transformer decepticon
	 */
	public void resultsOfBattle () {
		resultBattle = new ResultBattle();
		resultBattle.setNumberOfBattles(numberBattles);
		countWinning();
		
		if (numberAutobotWinning > numberDecepticonWinning) {
			resultBattle.setWinningTeam(TransformerConstant.AUTOBOTS);
			resultBattle.setListOfWinningTeam(listWinning(listAutobot));
			resultBattle.setLosingTeam(TransformerConstant.DECEPTICONS);
			resultBattle.setListOfSurvivalLosingTeam(listSurvivorLosing(listDecepticon));
		} else if (numberDecepticonWinning > numberAutobotWinning) {
			resultBattle.setWinningTeam(TransformerConstant.DECEPTICONS);
			resultBattle.setListOfWinningTeam(listWinning(listDecepticon));
			resultBattle.setLosingTeam(TransformerConstant.AUTOBOTS);
			resultBattle.setListOfSurvivalLosingTeam(listSurvivorLosing(listAutobot));
		}
	}
	
	/**
	 * Counting winning
	 */
	public void countWinning() {
		listAutobot.forEach(autobot -> {
			if (TransformerConstant.WINNER.equals(autobot.getLoserOrWinning())) {
				autobotWinning();
			}
		});;
		
		listDecepticon.forEach(decepticon -> {
			if (TransformerConstant.WINNER.equals(decepticon.getLoserOrWinning())) {
				decepticontWinning();
			}
		});;
	}
	
	/**
	 * Listing winning
	 */
	public List<Transformer> listWinning(List<Transformer> listTransformer) {
		List<Transformer> listWinner = new ArrayList<Transformer>();
		
		listTransformer.forEach(transformer -> {
			if (TransformerConstant.WINNER.equals(transformer.getLoserOrWinning())) {
				listWinner.add(transformer);
			}
		});;
		
		return listWinner;
	}
	
	/**
	 * Listing losing
	 */
	public List<Transformer> listSurvivorLosing(List<Transformer> listTransformer) {
		List<Transformer> listSurvivorLosing = new ArrayList<Transformer>();
		
		listTransformer.forEach(transformer -> {
			if (TransformerConstant.WINNER.equals(transformer.getLoserOrWinning()) || "".equals(transformer.getLoserOrWinning()) || transformer.getLoserOrWinning() == null) {
				listSurvivorLosing.add(transformer);
			}
		});;
		
		return listSurvivorLosing;
	}
	
	public void autobotWinning() {
		numberAutobotWinning++;
	}
	
	public void decepticontWinning() {
		numberDecepticonWinning++;
	}
	
	private void setAutobotWinnerAndDecpticonLoser(Transformer autobot, Transformer decepticon) {
		autobot.setLoserOrWinning(TransformerConstant.WINNER);
		decepticon.setLoserOrWinning(TransformerConstant.LOSER);
	}
	
	private void setAutobotLoserAndDecpticonWinner(Transformer autobot, Transformer decepticon) {
		autobot.setLoserOrWinning(TransformerConstant.LOSER);
		decepticon.setLoserOrWinning(TransformerConstant.WINNER);
	}

	public ResultBattle getResultBattle() {
		return resultBattle;
	}
}
