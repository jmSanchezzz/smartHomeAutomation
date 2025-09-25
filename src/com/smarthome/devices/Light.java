package com.smarthome.devices;

public class Light implements Switchable {
	private boolean on;
	private int brightness; // 0-100

	public Light() {
		this.on = false;
		this.brightness = 100;
	}

	public void turnOn() {
		this.on = true;
		System.out.println("Light: ON at brightness " + brightness + "%");
	}

	public void turnOff() {
		this.on = false;
		System.out.println("Light: OFF");
	}

	public void setBrightness(int value) {
		if (value < 0) value = 0;
		if (value > 100) value = 100;
		this.brightness = value;
		if (on) {
			System.out.println("Light: brightness set to " + brightness + "%");
		}
	}

	public boolean isOn() { return on; }
	public int getBrightness() { return brightness; }
}


