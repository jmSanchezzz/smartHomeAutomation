package com.smarthome.commands.thermostat;

import com.smarthome.core.Command;
import com.smarthome.devices.Thermostat;

public class IncreaseTemperature implements Command {
	private final Thermostat thermostat;
	public IncreaseTemperature(Thermostat thermostat) { this.thermostat = thermostat; }
	public String getName() { return "THERMOSTAT_INCREASE_TEMPERATURE"; }
	public void execute() { thermostat.increaseTemperature(); }
}


