package com.smarthome.commands.music;

import com.smarthome.core.Command;
import com.smarthome.devices.MusicPlayer;

public class IncreaseVolume implements Command {
	private final MusicPlayer player;
	public IncreaseVolume(MusicPlayer player) { this.player = player; }
	public String getName() { return "MUSIC_INCREASE_VOLUME"; }
	public void execute() { player.increaseVolume(); }
}


