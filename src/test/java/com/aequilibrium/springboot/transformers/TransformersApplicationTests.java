package com.aequilibrium.springboot.transformers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aequilibrium.springboot.business.Battle;
import com.aequilibrium.springboot.element.Transformer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformersApplicationTests {
	
	Transformer transformerOptimusPrime;
	Transformer transformerPredaking;
	Transformer transformerRobotMoreCorageousAndStrenghtness;
	Transformer transformerRobotLessCorageousAndStrenghtness;
	Transformer transformerRobotMoreSkill;
	Transformer transformerRobotLesskill;
	Transformer transformerLowOverallRating;
	Transformer transformerHighOverallRating;
	Transformer transformerMediumOverallRating1;
	Transformer transformerMediumOverallRating2;
	Battle battle;
	
	@BeforeClass
	public void setup() {
		transformerOptimusPrime = new Transformer("Optimus Prime", "A",6,7,5,9,10,3,5,10, true);
		transformerPredaking = new Transformer("Predaking", "A",6,7,5,9,10,3,5,10, true);
		transformerRobotMoreCorageousAndStrenghtness = new Transformer("Robot2", "B",6,5,5,4,8,8,2,10, true);
		transformerRobotLessCorageousAndStrenghtness = new Transformer("Robot3", "A",2,5,4,6,9,3,1,5, true);
		transformerRobotMoreSkill = new Transformer("More Skill", "B",6,5,5,4,8,8,2,10, true);
		transformerRobotLesskill = new Transformer("Less Skill", "B",6,5,5,4,8,8,2,1, true);
		transformerLowOverallRating = new Transformer("Low OverallRating", "B",1,1,1,1,1,1,1,1, true);
		transformerHighOverallRating = new Transformer("Low HighRating", "B",10,10,10,10,10,10,10,10, true);
		transformerMediumOverallRating1 = new Transformer("Medium OverallRating1", "B",5,5,5,5,5,5,5,5, true);
		transformerMediumOverallRating2 = new Transformer("Medium OverallRating2", "B",5,5,5,5,5,5,5,5, true);
		battle = new Battle();
	}
	
	@Test
	public void ruleTOptimusPrimeVersusOtherOne() {
		battle.ruleOptimusPredaking(transformerOptimusPrime, transformerRobotMoreCorageousAndStrenghtness);
		assertThat(transformerOptimusPrime.getLoserOrWinning()).isEqualTo("W");
	}
	
	@Test
	public void rulePredakingVersusOtherOne() {
		battle.ruleOptimusPredaking(transformerRobotMoreCorageousAndStrenghtness, transformerPredaking);
		assertThat(transformerPredaking.getLoserOrWinning()).isEqualTo("W");
	}
	
	@Test
	public void ruleMoreCourageousAndStrenghtness() {
		battle.ruleCourageAndStrenght(transformerRobotMoreCorageousAndStrenghtness, transformerRobotLessCorageousAndStrenghtness);
		assertThat(transformerRobotMoreCorageousAndStrenghtness.getLoserOrWinning()).isEqualTo("W");
	}
	
	@Test
	public void ruleLessCourageousAndStrenghtness() {
		battle.ruleCourageAndStrenght(transformerRobotMoreCorageousAndStrenghtness, transformerRobotLessCorageousAndStrenghtness);
		assertThat(transformerRobotLessCorageousAndStrenghtness.getLoserOrWinning()).isEqualTo("L");
	}
	
	@Test
	public void ruleMoreSkill() {
		battle.ruleSkill(transformerRobotMoreSkill, transformerRobotLesskill);
		assertThat(transformerRobotMoreSkill.getLoserOrWinning()).isEqualTo("W");
	}
	
	@Test
	public void ruleLessSkill() {
		battle.ruleSkill(transformerRobotMoreSkill, transformerRobotLesskill);
		assertThat(transformerRobotLesskill.getLoserOrWinning()).isEqualTo("L");
	}
	
	@Test
	public void ruleHighestOverallRating() {
		battle.ruleHighestOverallRating(transformerLowOverallRating, transformerHighOverallRating);
		assertThat(transformerHighOverallRating.getLoserOrWinning()).isEqualTo("W");
	}
	
	@Test
	public void ruleLowtOverallRating() {
		battle.ruleHighestOverallRating(transformerLowOverallRating, transformerHighOverallRating);
		assertThat(transformerLowOverallRating.getLoserOrWinning()).isEqualTo("L");
	}
	
	@Test
	public void ruleBothLose() {
		battle.ruleHighestOverallRating(transformerMediumOverallRating1, transformerMediumOverallRating2);
		assertThat(transformerMediumOverallRating1.getLoserOrWinning()).isEqualTo("L");
		assertThat(transformerMediumOverallRating2.getLoserOrWinning()).isEqualTo("L");
	}
}
