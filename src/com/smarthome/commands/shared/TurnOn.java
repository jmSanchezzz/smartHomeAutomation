package com.smarthome.commands.shared;

import com.smarthome.core.Command;
import com.smarthome.devices.Switchable;

public class TurnOn implements Command {
	private final String name;
	private final Switchable device;

	public TurnOn(String name, Switchable device) {
		this.name = name;
		this.device = device;
	}

	public String getName() { return name; }

	public void execute() { device.turnOn(); }
}


