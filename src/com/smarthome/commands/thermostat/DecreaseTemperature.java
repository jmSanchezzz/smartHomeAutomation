package com.smarthome.commands.thermostat;

import com.smarthome.core.Command;
import com.smarthome.devices.Thermostat;

public class DecreaseTemperature implements Command {
	private final Thermostat thermostat;
	public DecreaseTemperature(Thermostat thermostat) { this.thermostat = thermostat; }
	public String getName() { return "THERMOSTAT_DECREASE_TEMPERATURE"; }
	public void execute() { thermostat.decreaseTemperature(); }
}


