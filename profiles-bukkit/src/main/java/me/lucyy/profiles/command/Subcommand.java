/*
 * Copyright (C) 2021 Lucy Poulton https://lucyy.me
 * This file is part of ProFiles.
 *
 * ProFiles is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProFiles is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ProFiles.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.lucyy.profiles.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Subcommand {
	String getName();

	String getDescription();

	String getUsage();

	String getPermission();

	boolean execute(CommandSender sender, CommandSender target, String[] args);

	default List<String> tabComplete(String[] args) {
		return tabComplete();
	}

	default List<String> tabComplete() {
		return null;
	}
}
