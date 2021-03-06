package me.lucyy.profiles.command;

import me.lucyy.common.command.Subcommand;
import me.lucyy.profiles.ProFiles;
import me.lucyy.profiles.api.ProfileField;
import me.lucyy.profiles.api.ProfileManager;
import me.lucyy.profiles.api.SettableProfileField;
import me.lucyy.profiles.config.ConfigHandler;
import me.lucyy.profiles.field.SimpleProfileField;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SetSubcommand implements Subcommand {

	private final ProFiles plugin;

	public SetSubcommand(ProFiles plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getName() {
		return "set";
	}

	@Override
	public String getDescription() {
		return "Sets a field.";
	}

	@Override
	public String getUsage() {
		return "set <field> <value>";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		ConfigHandler cfg = plugin.getConfigHandler();
		if (!(target instanceof Player)) {
			sender.sendMessage(cfg.getPrefix().append(cfg.formatMain("This command can only be run by a player.")));
			return true;
		}

		ProfileManager manager = plugin.getProfileManager();

		if (args.length < 2) return false;

		SettableProfileField field;
		try {
			field = CommandUtils.getSettableField(manager, args[0]);
		} catch (AssertionError e) {
			sender.sendMessage(cfg.getPrefix().append(cfg.formatMain(e.getMessage())));
			return true;
		}
		StringBuilder value = new StringBuilder();
		for (int x = 1; x < args.length; x++) {
			value.append(args[x]).append(" ");
		}
		value.setLength(value.length() - 1);

		Player player = (Player) sender;

		String setValue;
		if (field instanceof SimpleProfileField && ((SimpleProfileField) field).allowsColour()) {
			setValue = value.toString();
		} else {
			setValue = PlainComponentSerializer.plain().serialize(cfg.formatAccent(value.toString()));
		}

		String result = field.setValue(player.getUniqueId(), setValue);
		if (result.equals("")) {
			sender.sendMessage(cfg.getPrefix()
					.append(cfg.formatMain("Set " + field.getDisplayName() + " to "))
					.append(CommandUtils.serialiseField(field, setValue, cfg))
					.append(cfg.formatMain("."))
			);
		}
		else sender.sendMessage(result);
		return true;
	}

	@Override
	public List<String> tabComplete(String[] args) {
		List<String> output = new ArrayList<>();
		if (args.length == 2) {
			return CommandUtils.tabCompleteSettable(plugin.getProfileManager().getFields(), args[1]);
		} else {
			ProfileField field = plugin.getProfileManager().getField(args[1]);

			String out = field instanceof SettableProfileField ? "<"
					+ field.getDisplayName().toLowerCase(Locale.ROOT) + ">" : "You can't set this field!";
			if (field instanceof SimpleProfileField && ((SimpleProfileField) field).allowsColour())
				out = "<this field supports colour>";
			output.add(out);
		}
		return output;
	}
}
