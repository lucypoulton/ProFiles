package me.lucyy.profiles.field;

import me.lucyy.profiles.api.SettableProfileField;
import me.lucyy.pronouns.api.PronounHandler;
import me.lucyy.pronouns.api.set.PronounSet;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;

public class ProNounsProfileField extends SettableProfileField {

	private final PronounHandler pronounHandler;

	public ProNounsProfileField(String key, String displayName, int order, PronounHandler handler) {
		super(key, displayName, order);
		this.pronounHandler = handler;
	}

	@Override
	public String getValue(UUID player) {
		if (pronounHandler == null) return "ProNouns is not installed!";
		return PronounSet.friendlyPrintSet(pronounHandler.getUserPronouns(player));
	}

	@Override
	public String setValue(UUID player, String value) {
		if (pronounHandler == null) return "ProNouns is not installed!";
		Bukkit.dispatchCommand(Objects.requireNonNull(Bukkit.getPlayer(player)), "pronouns set " + value);
		return " ";
	}

	@Override
	public String clearValue(UUID player) {
		if (pronounHandler == null) return "ProNouns is not installed!";
		Bukkit.dispatchCommand(Objects.requireNonNull(Bukkit.getPlayer(player)), "pronouns clear");
		return " ";
	}
}