package com.smarthome.commands.light;

import com.smarthome.core.Command;
import com.smarthome.devices.Light;

public class SetLightBrightness implements Command {
	private final Light light;
	private final int brightness;
	public SetLightBrightness(Light light, int brightness) {
		this.light = light;
		this.brightness = brightness;
	}
	public String getName() { return "LIGHT_SET_BRIGHTNESS_" + brightness; }
	public void execute() { light.setBrightness(brightness); }
}


