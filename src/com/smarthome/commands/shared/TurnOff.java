package com.smarthome.commands.shared;

import com.smarthome.core.Command;
import com.smarthome.devices.Switchable;

public class TurnOff implements Command {
	private final String name;
	private final Switchable device;

	public TurnOff(String name, Switchable device) {
		this.name = name;
		this.device = device;
	}

	public String getName() { return name; }

	public void execute() { device.turnOff(); }
}


