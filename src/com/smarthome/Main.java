package com.smarthome;

import com.smarthome.core.CentralHub;
import com.smarthome.devices.Light;
import com.smarthome.devices.MusicPlayer;
import com.smarthome.devices.Thermostat;
import com.smarthome.commands.shared.TurnOn;
import com.smarthome.commands.shared.TurnOff;
import com.smarthome.commands.light.SetLightBrightness;
import com.smarthome.commands.thermostat.IncreaseTemperature;
import com.smarthome.commands.thermostat.DecreaseTemperature;
import com.smarthome.commands.music.IncreaseVolume;
import com.smarthome.commands.music.DecreaseVolume;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		CentralHub hub = new CentralHub();

		// Devices
		Light livingRoomLight = new Light();
		Thermostat thermostat = new Thermostat(22);
		MusicPlayer musicPlayer = new MusicPlayer();

		// Register baseline commands (hub remains device-agnostic)
		hub.registerCommand(new TurnOn("LIGHT_TURN_ON", livingRoomLight));
		hub.registerCommand(new TurnOff("LIGHT_TURN_OFF", livingRoomLight));
		hub.registerCommand(new TurnOn("THERMOSTAT_TURN_ON", thermostat));
		hub.registerCommand(new TurnOff("THERMOSTAT_TURN_OFF", thermostat));
		hub.registerCommand(new IncreaseTemperature(thermostat));
		hub.registerCommand(new DecreaseTemperature(thermostat));
		hub.registerCommand(new TurnOn("MUSIC_TURN_ON", musicPlayer));
		hub.registerCommand(new TurnOff("MUSIC_TURN_OFF", musicPlayer));
		hub.registerCommand(new IncreaseVolume(musicPlayer));
		hub.registerCommand(new DecreaseVolume(musicPlayer));

		printIntro();
		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true;
			while (running) {
				System.out.println();
				System.out.println("=== Main Menu ===");
				System.out.println("1) Light");
				System.out.println("2) Thermostat");
				System.out.println("3) Music Player");
				System.out.println("0) Exit");
				int choice = readInt(scanner, "Select a device: ");
				switch (choice) {
					case 1:
						lightMenu(scanner, hub, livingRoomLight);
						break;
					case 2:
						thermostatMenu(scanner, hub, thermostat);
						break;
					case 3:
						musicMenu(scanner, hub, musicPlayer);
						break;
					case 0:
						System.out.println("Goodbye!");
						running = false;
						break;
					default:
						System.out.println("Invalid choice.");
				}
			}
		}
	}

	private static void printIntro() {
		System.out.println("Welcome to the Smart Home Automation System!");
		System.out.println("Control your devices via a simple menu.\n");
	}

	private static int readInt(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			String line = scanner.nextLine();
			try {
				return Integer.parseInt(line.trim());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	private static void lightMenu(Scanner scanner, CentralHub hub, Light light) {
		boolean back = false;
		while (!back) {
			System.out.println();
			System.out.println("--- Light Menu ---");
			if (light.isOn()) {
				System.out.println("Status: ON (brightness " + light.getBrightness() + "%)");
			} else {
				System.out.println("Status: OFF");
			}
			System.out.println("1) Turn On");
			System.out.println("2) Turn Off");
			System.out.println("3) Set Brightness (0-100)");
			System.out.println("0) Back");
			int choice = readInt(scanner, "Choose: ");
			switch (choice) {
				case 1:
					hub.execute("LIGHT_TURN_ON");
					break;
				case 2:
					hub.execute("LIGHT_TURN_OFF");
					break;
				case 3:
					int b = readInt(scanner, "Enter brightness (0-100): ");
					// Dynamically create and execute a parameterized command without altering hub logic
					String name = "LIGHT_SET_BRIGHTNESS_" + b;
					hub.registerCommand(new SetLightBrightness(light, b));
					hub.execute(name);
					break;
				case 0:
					back = true;
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}

	private static void thermostatMenu(Scanner scanner, CentralHub hub, Thermostat thermostat) {
		boolean back = false;
		while (!back) {
			System.out.println();
			System.out.println("--- Thermostat Menu ---");
			if (thermostat.isOn()) {
				System.out.println("Status: ON (" + thermostat.getTemperatureCelsius() + "°C)");
			} else {
				System.out.println("Status: OFF");
			}
			System.out.println("1) Turn On");
			System.out.println("2) Turn Off");
			System.out.println("3) Increase Temperature");
			System.out.println("4) Decrease Temperature");
			System.out.println("0) Back");
			int choice = readInt(scanner, "Choose: ");
			switch (choice) {
				case 1:
					hub.execute("THERMOSTAT_TURN_ON");
					break;
				case 2:
					hub.execute("THERMOSTAT_TURN_OFF");
					break;
				case 3:
					hub.execute("THERMOSTAT_INCREASE_TEMPERATURE");
					break;
				case 4:
					hub.execute("THERMOSTAT_DECREASE_TEMPERATURE");
					break;
				case 0:
					back = true;
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}

	private static void musicMenu(Scanner scanner, CentralHub hub, MusicPlayer player) {
		boolean back = false;
		while (!back) {
			System.out.println();
			System.out.println("--- Music Player Menu ---");
			if (player.isOn()) {
				System.out.println("Status: ON (volume " + player.getVolume() + ")");
			} else {
				System.out.println("Status: OFF");
			}
			System.out.println("1) Turn On");
			System.out.println("2) Turn Off");
			System.out.println("3) Increase Volume");
			System.out.println("4) Decrease Volume");
			System.out.println("0) Back");
			int choice = readInt(scanner, "Choose: ");
			switch (choice) {
				case 1:
					hub.execute("MUSIC_TURN_ON");
					break;
				case 2:
					hub.execute("MUSIC_TURN_OFF");
					break;
				case 3:
					hub.execute("MUSIC_INCREASE_VOLUME");
					break;
				case 4:
					hub.execute("MUSIC_DECREASE_VOLUME");
					break;
				case 0:
					back = true;
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}
}


