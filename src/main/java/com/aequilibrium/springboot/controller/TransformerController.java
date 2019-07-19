package com.aequilibrium.springboot.controller;

/**
 * Class REST
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aequilibrium.springboot.business.Battle;
import com.aequilibrium.springboot.constants.TransformerConstant;
import com.aequilibrium.springboot.element.ResultBattle;
import com.aequilibrium.springboot.element.Transformer;
import com.aequilibrium.springboot.exception.IncorrectParametersException;
import com.aequilibrium.springboot.validation.TransformerValidation;

import io.swagger.annotations.ApiOperation;

@RestController
public class TransformerController {
	
	private HashMap<String, Transformer> mapAutobot = new HashMap<String, Transformer>();
	private HashMap<String, Transformer> mapDecepticon = new HashMap<String, Transformer>();
	
	TransformerValidation transformerValidation;
	
	@ApiOperation(value = "Create a transformer")
	@PostMapping("/transformer")
	public Transformer createTransformer(@RequestBody Transformer transformer) {
		transformerValidation = new TransformerValidation(transformer);
		if (transformerValidation.isTransformerValid()) {
			if (transformer.getTeamMember().toString().equals(TransformerConstant.AUTOBOT)) {
				mapAutobot.put(transformer.getName(), transformer);
			} else {
				mapDecepticon.put(transformer.getName(), transformer);
			}
			return transformer;
		} else {
			throw new IncorrectParametersException(transformerValidation.getMessage());
		}
	}
	
	@ApiOperation(value = "Update a transformer")
	@PutMapping("/transformer/{name}")
	public Transformer updateTransformer(@RequestBody Transformer transformer, @PathVariable String name) {
		Transformer updatedTransformer = new Transformer();
		if (transformerValidation.isTransformerValid()) {
			if (mapAutobot.containsKey(name)) {
				mapAutobot.put(name, transformer);
				updatedTransformer = mapAutobot.get(name);
			} else if (mapDecepticon.containsKey(name)) {
				mapDecepticon.put(name, transformer);
				updatedTransformer = mapDecepticon.get(name);
			}
			return updatedTransformer;
		} else {
			throw new IncorrectParametersException(transformerValidation.getMessage());
		}
	}
	
	@ApiOperation(value = "Delete a transformer")
	@DeleteMapping("/transformer/{name}")
	public Transformer deleteTransformer (@PathVariable String name) {
		Transformer deletedTransformer = new Transformer();
		if (mapAutobot.containsKey(name)) {
			deletedTransformer = mapAutobot.get(name);
			mapAutobot.remove(name);
		} else if (mapDecepticon.containsKey(name)) {
			deletedTransformer = mapDecepticon.get(name);
			mapDecepticon.remove(name);
		}
		
		return deletedTransformer;
	}
	
	
	@ApiOperation(value = "Retrieve a list with 2 armies")
	@GetMapping("/transformer")
	public List<Transformer> getListTransformer () {
		List<Transformer> listAutobot = new ArrayList<Transformer>(mapAutobot.values());
		List<Transformer> listDecepticon = new ArrayList<Transformer>(mapDecepticon.values());
		
		List<Transformer> newList = new ArrayList<Transformer>(listAutobot);
		newList.addAll(listDecepticon);
		
		return newList;
	}
	
	@ApiOperation(value = "Execute the battle between 2 armies")
	@GetMapping("/battle")
	public ResultBattle goToBattle () {
		Battle battle = new Battle();
		ResultBattle resultBattle = new ResultBattle();
		battle.action(mapAutobot, mapDecepticon);
		resultBattle = battle.getResultBattle();
		
		return resultBattle;
	}
}
