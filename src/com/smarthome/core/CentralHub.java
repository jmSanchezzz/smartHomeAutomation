package com.smarthome.core;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Centralized controller that registers and executes high-level commands.
 * New devices register their commands here without changing the hub itself.
 */
public class CentralHub {

	private final Map<String, Command> nameToCommand;

	public CentralHub() {
		this.nameToCommand = new LinkedHashMap<>();
	}

	/** Registers or replaces a command by its unique name. */
	public void registerCommand(Command command) {
		if (command == null || command.getName() == null || command.getName().isEmpty()) {
			throw new IllegalArgumentException("Command and command name must be non-null/non-empty");
		}
		nameToCommand.put(command.getName(), command);
	}

	/** Registers multiple commands at once. Later entries override earlier ones on name collision. */
	public void registerCommands(Iterable<Command> commands) {
		for (Command c : commands) {
			registerCommand(c);
		}
	}

	/** Executes a command by name. */
	public void execute(String commandName) {
		Command command = nameToCommand.get(commandName);
		if (command == null) {
			throw new IllegalArgumentException("No command registered with name: " + commandName);
		}
		command.execute();
	}

	/** Immutable view of registered command names. */
	public Map<String, Command> getRegisteredCommands() {
		return Collections.unmodifiableMap(nameToCommand);
	}
}


