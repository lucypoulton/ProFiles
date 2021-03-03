package me.lucyy.profiles.api;

import java.util.UUID;

/**
 * A profile field with a settable value.
 * @author lucy
 */
public interface SettableProfileField extends ProfileField {
    /**
     * Sets the value of this field.
     * @param player the player to set this field for
     * @param value the value to set
     */
    void setValue(UUID player, String value);
}
