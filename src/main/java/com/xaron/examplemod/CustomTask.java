package com.xaron.examplemod;

import java.util.ArrayList;
import java.util.List;

import com.xaron.equilinoxmodded.framework.GameManager;
import com.xaron.equilinoxmodded.framework.GameRepository;
import com.xaron.equilinoxmodded.framework.Task;

import tasks.Reward;
import tasks.ShopItemUnlockReward;
import tasks.TaskRequirement;
import tasks.TaskUnlockReward;

// CustomTask class inherited from com.xaron.equilinoxmodded.framework.Task;
public class CustomTask extends Task {

	public CustomTask() {
		super("Custom Task", "Custom task description.", false);
	}

	@Override
	protected List<TaskRequirement> getRequirements() {
		List<TaskRequirement> reqs = new ArrayList<TaskRequirement>();
		//reqs.add(new FullGrownCountReq(GameRepository.getClassification(GameRepository.Classification.Grass.get(20)), 3));
		return reqs;
	}


	@Override
	protected List<Reward> getRewards() {
		List<Reward> rewards = new ArrayList<Reward>();
		//rewards.add(new CashReward(15000));
		rewards.add(new ShopItemUnlockReward(GameManager.getShopItem(GameRepository.getBlueprintId("Sheep"))));
		rewards.add(new TaskUnlockReward(new int[] { GameRepository.getTaskId("FlowerPower") }));
		return rewards;
	}
}
