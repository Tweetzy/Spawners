package ca.tweetzy.spawners.guis;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.Gui;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * Date Created: May 16 2022
 * Time Created: 2:14 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelSelectorGUI extends PagedGUI<Level> {

	private final Consumer<Level> selected;

	public LevelSelectorGUI(Gui parent, @NonNull final LevelOption option, @NonNull final Consumer<Level> selected) {
		super(parent, "<GRADIENT:fc67fa>&lPresets</GRADIENT:f4c4f3> &8> &7Select Level", 6, Spawners.getLevelManager().getLevels(option));
		this.selected = selected;
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
						"",
						"&c&lPress 1 &8» &7To select level"
				)
				.make();
	}

	@Override
	protected void onClick(Level level, GuiClickEvent clickEvent) {
		this.selected.accept(level);
	}

	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
