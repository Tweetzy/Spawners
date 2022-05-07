package ca.tweetzy.spawners.guis;

import ca.tweetzy.rose.comp.NBTEditor;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.Gui;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.MobBehaviour;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Date Created: May 06 2022
 * Time Created: 1:53 p.m.
 *
 * @author Kiran Hart
 */
public final class EntitySelectorGUI extends PagedGUI<SpawnerMob> {

	private final Gui parent;
	private final Consumer<EntityType> selected;
	private final EntityViewMode entityViewMode;

	public EntitySelectorGUI(Gui parent, @NonNull final EntityViewMode entityViewMode, @NonNull final Consumer<EntityType> selected) {
		super(parent, Translation.GUI_ENTITY_SELECTOR_TITLE.getString(), 6,
				entityViewMode == EntityViewMode.ALL ?
						Arrays.stream(SpawnerMob.values()).sorted(Comparator.comparing(SpawnerMob::getMobName)).collect(Collectors.toList())
						:
						Arrays.stream(SpawnerMob.values()).filter(mob -> mob.getBehaviour() == MobBehaviour.valueOf(entityViewMode.name())).sorted(Comparator.comparing(SpawnerMob::getMobName)).collect(Collectors.toList())
		);

		this.parent = parent;
		this.selected = selected;
		this.entityViewMode = entityViewMode;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(SpawnerMob spawnerMob) {
		return QuickItem
				.of(NBTEditor.getHead(spawnerMob.getHeadTexture()))
				.name(Translation.GUI_ENTITY_SELECTOR_ITEMS_ENTITY_NAME.getString("entity_name", spawnerMob.getMobName()))
				.lore(Translation.GUI_ENTITY_SELECTOR_ITEMS_ENTITY_LORE.getList())
				.make();
	}

	@Override
	protected void onClick(SpawnerMob spawnerMob, GuiClickEvent clickEvent) {
		this.selected.accept(spawnerMob.getEntityType());
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem.of(CompMaterial.REPEATER)
				.name(Translation.GUI_ENTITY_SELECTOR_ITEMS_MODE_NAME.getString())
				.lore(Translation.GUI_ENTITY_SELECTOR_ITEMS_MODE_LORE.getList("entity_behaviour", StringUtils.capitalize(this.entityViewMode.name().toLowerCase())))
				.make(), click -> click.manager.showGUI(click.player, new EntitySelectorGUI(this.parent, this.entityViewMode.next(), this.selected)));
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}

	public enum EntityViewMode {
		PASSIVE, HOSTILE, NEUTRAL, ALL;

		private static final EntityViewMode[] val = values();

		public EntityViewMode next() {
			return val[(this.ordinal() + 1) % val.length];
		}
	}
}
