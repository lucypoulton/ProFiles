package me.lucyy.profiles.field;

import me.lucyy.profiles.ProfileManagerImpl;
import me.lucyy.profiles.api.SettableProfileField;

import java.util.UUID;

public class SimpleProfileField extends SettableProfileField {
	private final ProfileManagerImpl manager;
	private final boolean allowColour;

	public SimpleProfileField(ProfileManagerImpl manager, String key, String displayName, int order, boolean allowColour) {
		super(key, displayName, order);
		this.manager = manager;
		this.allowColour = allowColour;
	}

	@Override
	public String getValue(UUID player) {
		return manager.getStorage().getField(player, getKey());
	}

	@Override
	public String setValue(UUID player, String value) {
		manager.getStorage().setField(player, getKey(), value);
		return "";
	}

	@Override
	public String clearValue(UUID player) {
		manager.getStorage().clearField(player, getKey());
		return "";
	}

	public boolean allowsColour() {
		return allowColour;
	}
}