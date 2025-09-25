# Smart Home Automation System (Java)

A flexible, extensible smart home system using the Command pattern. The `CentralHub` registers and executes high-level commands without knowing device internals.

## Run

Tested with Java 8+.

- Windows (PowerShell, from project root):

```powershell
$sources = Get-ChildItem -Recurse -Filter *.java -Path .\src | ForEach-Object { $_.FullName }
javac -d .\out\production\LabAct2 $sources
java -cp .\out\production\LabAct2 com.smarthome.Main
```

- macOS/Linux (bash):

```bash
javac -d out $(find src -name "*.java")
java -cp out com.smarthome.Main
```

## Interactive Menu

On run, the main menu lists devices:

- Light
- Thermostat
- Music Player

Each submenu allows actions like turning on/off, setting brightness, changing temperature, or volume.

## Architecture Highlights

- **Command pattern**: `com.smarthome.core.Command` is executed by `CentralHub` via string names.
- **Shared On/Off**: `com.smarthome.commands.shared.TurnOn` / `TurnOff` work with any device implementing `com.smarthome.devices.Switchable`.
- **Separated commands by domain**:
  - Light: `com.smarthome.commands.light.SetLightBrightness`
  - Thermostat: `com.smarthome.commands.thermostat.IncreaseTemperature`, `DecreaseTemperature`
  - Music: `com.smarthome.commands.music.IncreaseVolume`, `DecreaseVolume`
- **Device abstraction**: `Light`, `Thermostat`, `MusicPlayer` implement `Switchable` (for on/off) and expose their own domain ops.
- **Hub remains device-agnostic**: The hub only maps names to `Command` instances.

## Adding a New Device

1) Implement the device (receiver), ideally `implements Switchable` if it supports on/off.

```java
public class Sprinkler implements com.smarthome.devices.Switchable {
    public void turnOn() { /* ... */ }
    public void turnOff() { /* ... */ }
    public void setDuration(int minutes) { /* ... */ }
}
```

2) Create commands (one class per action) and/or reuse shared `TurnOn`/`TurnOff`:

```java
public class SetSprinklerDuration implements Command {
    private final Sprinkler sprinkler; private final int minutes;
    public SetSprinklerDuration(Sprinkler s, int m){ this.sprinkler=s; this.minutes=m; }
    public String getName(){ return "SPRINKLER_SET_DURATION_" + minutes; }
    public void execute(){ sprinkler.setDuration(minutes); }
}
```

3) Register with the hub:

```java
hub.registerCommand(new com.smarthome.commands.shared.TurnOn("SPRINKLER_TURN_ON", sprinkler));
hub.registerCommand(new com.smarthome.commands.shared.TurnOff("SPRINKLER_TURN_OFF", sprinkler));
hub.registerCommand(new SetSprinklerDuration(sprinkler, 15));
```

## Notes on Recent Refactor

- Replaced centralized `SimpleCommands` with one-class-per-command under `commands/light`, `commands/thermostat`, `commands/music`.
- Introduced `devices/Switchable` and shared commands `commands/shared/TurnOn` and `TurnOff` used by all devices.
- `Main` registers these commands; menu strings (e.g., `LIGHT_TURN_ON`) remain unchanged.
