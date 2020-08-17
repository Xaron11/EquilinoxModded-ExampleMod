package com.xaron.examplemod;


import org.lwjgl.input.Keyboard;

import com.xaron.equilinoxmodded.framework.Blueprint;
import com.xaron.equilinoxmodded.framework.GameManager;
import com.xaron.equilinoxmodded.framework.GameRegistry;
import com.xaron.equilinoxmodded.framework.GameText;
import com.xaron.equilinoxmodded.framework.Input;
import com.xaron.equilinoxmodded.framework.Mod;
import com.xaron.equilinoxmodded.framework.blueprintgen.BlueprintGenerator;
import com.xaron.equilinoxmodded.framework.events.EntityDiedEvent;
import com.xaron.equilinoxmodded.framework.events.EventHandler;
import com.xaron.equilinoxmodded.framework.events.GameUpdateEvent;
import com.xaron.equilinoxmodded.framework.events.InitializationEvent;
import com.xaron.equilinoxmodded.framework.events.SessionLoadedEvent;
import com.xaron.equilinoxmodded.utils.Logger;

import mainGuis.GuiRepository;
import mainGuis.GuiSounds;

// Main mod class with @Mod annotation with MODNAME property.

@Mod(MODNAME = "ExampleMod")
public class ExampleMod {

	// Logger for the mod.
	private static final Logger LOGGER = new Logger("ExampleMod");
	
	// Game Texts used for entity name and description in InformationComponent.
	public static final GameText ENTITY_NAME = GameRegistry.addGameText("Entity");
	public static final GameText ENTITY_DESC = GameRegistry.addGameText("Entity Desc.");
	
	// Main method for generating entity file (res/entities/entity.txt) from: 
	// * model files (res/models/final.obj, res/models/final.mtl)
	// * EntityBlueprint class
	public static void main(String[] args) {
		try {
			BlueprintGenerator.generate("entity", new EntityBlueprint());
		} catch (Exception e) {
			LOGGER.stacktrace(e);
		}
	}
	
	// Main mod class constructor initialized when mod is loaded.
	public ExampleMod() {
		LOGGER.info("Load!");
	}
	
	// EventHandler for InitialiationEvent
	@EventHandler
	public void initialization(InitializationEvent event) {
		LOGGER.info("Init!");

		// Registering Entity from res/entities/entity.txt file
		GameRegistry.registerBlueprint("Entity", () -> new Blueprint("entity"));
		//Registering ShopItem for Entity
		GameRegistry.registerShopItem(GameRegistry.Shop.Plant, "Entity");
		
		//Registering CustomTask
		GameRegistry.registerTask("CustomTask", CustomTask::new);
	}
	
	// EventHandler for EntityDiedEvent
	@EventHandler
	public void entityDied(EntityDiedEvent e) {
		LOGGER.info("Died!");
	}
	
	// EventHandler for SessionLoadedEvent
	@EventHandler
	public void sessionLoaded(SessionLoadedEvent e) {
		// Logging current session's world seed
		LOGGER.info("Seed: " + String.valueOf(e.session.getWorld().getSeed()));
	}
	
	// EventHandler for GameUpdateEvent
	@EventHandler
	public void update(GameUpdateEvent e) {
		// Checking input for F key
		if (Input.keyDown(Keyboard.KEY_F)) {
			// Showing notification
			GameManager.notify("Cheat", "Dp increased.", GuiRepository.CONFIRM, GuiSounds.CASH);
			// Increasing Dp value.
			GameManager.getSession().increaseDp(100);
		}
	}
}
