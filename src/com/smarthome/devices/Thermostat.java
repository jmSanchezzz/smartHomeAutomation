package com.smarthome.devices;

public class Thermostat implements Switchable {
	private boolean on;
	private int temperatureCelsius; // simple int for demo

	public Thermostat(int initialTempCelsius) {
		this.on = false;
		this.temperatureCelsius = initialTempCelsius;
	}

	public void turnOn() {
		this.on = true;
		System.out.println("Thermostat: ON at " + temperatureCelsius + "°C");
	}

	public void turnOff() {
		this.on = false;
		System.out.println("Thermostat: OFF");
	}

	public void increaseTemperature() {
		this.temperatureCelsius++;
		System.out.println("Thermostat: temperature increased to " + temperatureCelsius + "°C");
	}

	public void decreaseTemperature() {
		this.temperatureCelsius--;
		System.out.println("Thermostat: temperature decreased to " + temperatureCelsius + "°C");
	}

	public boolean isOn() { return on; }
	public int getTemperatureCelsius() { return temperatureCelsius; }
}


