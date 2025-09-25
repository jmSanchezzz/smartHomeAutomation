package com.smarthome.core;

/**
 * Represents a high-level action that can be executed by the central hub.
 * Implementations should be stateless where possible; state resides in device receivers.
 */
public interface Command {
	/** Human-readable unique name used by the hub/app to invoke this command. */
	String getName();

	/** Executes the command. */
	void execute();
}



