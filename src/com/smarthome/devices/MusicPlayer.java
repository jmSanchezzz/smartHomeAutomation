package com.smarthome.devices;

public class MusicPlayer implements Switchable {
	private boolean on;
	private int volume; // 0-100

	public MusicPlayer() {
		this.on = false;
		this.volume = 50;
	}

	public void turnOn() {
		this.on = true;
		System.out.println("MusicPlayer: ON, playing default playlist at volume " + volume);
	}

	public void turnOff() {
		this.on = false;
		System.out.println("MusicPlayer: OFF");
	}

	public void increaseVolume() {
		if (volume < 100) volume++;
		System.out.println("MusicPlayer: volume increased to " + volume);
	}

	public void decreaseVolume() {
		if (volume > 0) volume--;
		System.out.println("MusicPlayer: volume decreased to " + volume);
	}

	public boolean isOn() { return on; }
	public int getVolume() { return volume; }
}


