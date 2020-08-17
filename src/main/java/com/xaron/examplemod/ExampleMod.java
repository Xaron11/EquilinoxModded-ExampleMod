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

@Mod(MODNAME = "ExampleMod")
public class ExampleMod {

	private static final Logger LOGGER = new Logger("ExampleMod");
	
	public static final GameText ENTITY_NAME = GameRegistry.addGameText("Entity");
	public static final GameText ENTITY_DESC = GameRegistry.addGameText("Entity Desc.");
	
	public static void main(String[] args) {
		try {
			BlueprintGenerator.generate("entity", new EntityBlueprint());
		} catch (Exception e) {
			LOGGER.stacktrace(e);
		}
	}
	
	public ExampleMod() {
		LOGGER.info("Load!");
	}
	
	@EventHandler
	public void initialization(InitializationEvent event) {
		LOGGER.info("Init!");
		
		GameRegistry.registerBlueprint("Entity", () -> new Blueprint("entity"));
		GameRegistry.registerShopItem(GameRegistry.Shop.Plant, "Entity");
		
		GameRegistry.registerTask("Custom Task", CustomTask::new);
	}
	
	@EventHandler
	public void entityDied(EntityDiedEvent e) {
		LOGGER.info("Died!");
	}
	
	@EventHandler
	public void sessionLoaded(SessionLoadedEvent e) {
		LOGGER.info("Seed: " + String.valueOf(e.session.getWorldSeed()));
	}
	
	@EventHandler
	public void update(GameUpdateEvent e) {
		if (Input.keyDown(Keyboard.KEY_F)) {
			GameManager.notify("Cheat", "Dp increased.", GuiRepository.CONFIRM, GuiSounds.CASH);
			GameManager.getSession().increaseDp(100);
		}
	}
}
