package com.aequilibrium.springboot.validation;

import com.aequilibrium.springboot.constants.TransformerConstant;
import com.aequilibrium.springboot.element.Transformer;

public class TransformerValidation {
	
	private boolean transformerValid;
	private String message = "";
	private Transformer transformer;
	
	public TransformerValidation(Transformer transformer) {
		super();
		this.transformer = transformer;
		checkParameterValid(transformer);
	}

	public void checkParameterValid(Transformer transformer) {
		validName();
		validStrength();
		validIntelligence();
		validSpeed();
		validEndurance();
		validRank();
		validCourage();
		validFirePower();
		validSkill();
	}
	
	private void validName() {
		if (transformer.getName().isEmpty() || transformer.getName() == null) {
			transformerValid = false;
			message.concat(TransformerConstant.NAME_NULL_EMPTY);
		}
	}
	
	private void validStrength() {
		if (transformer.getStrenght() <= 0 || transformer.getStrenght() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.STRENGTH_ONE_TEN).concat(" "));
		}
	}
	
	private void validIntelligence() {
		if (transformer.getIntelligence() <= 0 || transformer.getIntelligence() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.INTELLIGENCE_ONE_TEN).concat(" "));
		}
	}
	
	private void validSpeed() {
		if (transformer.getSpeed() <= 0 || transformer.getSpeed() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.SPEED_ONE_TEN).concat(" "));
		}
	}
	
	private void validEndurance() {
		if (transformer.getEndurance() <= 0 || transformer.getEndurance() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.ENDURANCE_ONE_TEN).concat(" "));
		}
	}
	
	private void validRank() {
		if (transformer.getRank() <= 0 || transformer.getRank() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.RANK_ONE_TEN).concat(" "));
		}
	}
	
	private void validCourage() {
		if (transformer.getCourage() <= 0 || transformer.getCourage() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.COURAGE_ONE_TEN).concat(" "));
		}
	}
	
	private void validFirePower() {
		if (transformer.getFirepower() <= 0 || transformer.getFirepower() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.FIREPOWER_ONE_TEN).concat(" "));
		}
	}
	
	private void validSkill() {
		if (transformer.getSkill() <= 0 || transformer.getSkill() > 10) {
			transformerValid = false;
			setMessage(getMessage().concat(TransformerConstant.SKILL_ONE_TEN).concat(" "));
		}
	}
	
	public boolean isTransformerValid() {
		return transformerValid;
	}

	public void setTransformerValid(boolean transformerValid) {
		this.transformerValid = transformerValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Transformer getTransformer() {
		return transformer;
	}

	public void setTransformer(Transformer transformer) {
		this.transformer = transformer;
	} 
}