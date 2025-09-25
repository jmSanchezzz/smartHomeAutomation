package com.smarthome.commands.music;

import com.smarthome.core.Command;
import com.smarthome.devices.MusicPlayer;

public class DecreaseVolume implements Command {
	private final MusicPlayer player;
	public DecreaseVolume(MusicPlayer player) { this.player = player; }
	public String getName() { return "MUSIC_DECREASE_VOLUME"; }
	public void execute() { player.decreaseVolume(); }
}


