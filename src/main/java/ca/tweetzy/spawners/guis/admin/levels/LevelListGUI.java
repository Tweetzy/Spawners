package ca.tweetzy.spawners.guis.admin.levels;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.model.LevelFactory;
import lombok.NonNull;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: May 13 2022
 * Time Created: 2:07 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelListGUI extends PagedGUI<Level> {

	private final LevelOption levelOption;

	public LevelListGUI(@NonNull final LevelOption levelOption) {
		super(new LevelOptionSelectGUI(), "<GRADIENT:fc67fa>&LLevels</GRADIENT:f4c4f3> &8> " + ChatUtil.capitalizeFully(levelOption.name()), 6, Spawners.getLevelManager().getLevels(levelOption));
		this.levelOption = levelOption;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Level level) {
		return QuickItem
				.of(CompMaterial.PAPER)
				.name(Replacer.replaceVariables("<GRADIENT:fc67fa>&LLevel %level%</GRADIENT:f4c4f3>", "level", level.getLevelNumber()))
				.lore(
						"&7Level #&f: &e" + level.getLevelNumber(),
						"&7Value&f: &e" + level.getValue(),
						"&7Cost: &a$" + String.format("%,.2f", level.getCost()),
						"",
						"&e&lLeft Click &8» &7To edit level",
						"&c&lPress 1 &8» &7To delete level"
				)
				.make();
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem
				.of(CompMaterial.SLIME_BALL)
				.name("&a&lCreate Level")
				.lore(
						"&7Used to create a new level for",
						"&7the " + levelOption.name() + " option",
						"",
						"&e&lClick &8» &7Create new level"
				)
				.make(), click -> Spawners.getLevelManager().createLevel(
				LevelFactory.build(this.levelOption, Spawners.getLevelManager().getHighestLevel(this.levelOption) + 1, LevelFactory.getDefaultValue(this.levelOption), 1000D), (created, createdLevel) -> {
					if (created)
						click.manager.showGUI(click.player, new LevelListGUI(this.levelOption));
				}));
	}

	@Override
	protected void onClick(Level level, GuiClickEvent clickEvent) {
		if (clickEvent.clickType == ClickType.LEFT)
			clickEvent.manager.showGUI(clickEvent.player, new LevelEditGUI(level));

		if (clickEvent.clickType == ClickType.NUMBER_KEY)
			Spawners.getLevelManager().deleteLevel(level, (success) -> {
				if (success)
					clickEvent.manager.showGUI(clickEvent.player, new LevelListGUI(this.levelOption));
			});
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
