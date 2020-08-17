package com.xaron.examplemod;

import java.io.IOException;

import com.xaron.equilinoxmodded.framework.GameRepository;
import com.xaron.equilinoxmodded.framework.blueprintgen.*;
import com.xaron.equilinoxmodded.framework.blueprintgen.components.ComponentGen;
import com.xaron.equilinoxmodded.framework.blueprintgen.components.InformationComponentGen;

//EntityBlueprint class inherited from import com.xaron.equilinoxmodded.framework.blueprintgen.BlueprintGen
public class EntityBlueprint extends BlueprintGen {
	
	// Setting entity properties
	public EntityBlueprint() {
		this.size = 3.5f;
		this.classification = GameRepository.Classification.Grass.get();
		this.randomizeModelStages = true;
		this.overrideMainModelIndex = 0;
	}
	
	// Adding components
	@Override
	protected ComponentGen[] getComponents() {
		return new ComponentGen[] { new InformationComponentGen(ExampleMod.ENTITY_NAME.getId(), ExampleMod.ENTITY_DESC.getId(), 100, 50, 2, "thud") };
	}

	// Generating models
	@Override
	protected SubBlueprintGen[] getSubBlueprints() throws IOException {
		return new SubBlueprintGen[] { ModelGenerator.generate("final") }; 
	}
}