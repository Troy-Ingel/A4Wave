package mainGame;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	public static Map<String, Music> narutoMap = new HashMap<String, Music>();
	public static Map<String, Music> level1Map = new HashMap<String, Music>();
	public static Map<String, Music> whipMap = new HashMap<String, Music>();

	public static void load() {
		try {
			soundMap.put("sound", new Sound("res/ClickSound.ogg"));
			musicMap.put("music", new Music("res/MainMenu.ogg"));
			narutoMap.put("naruto", new Music("res/Naruto.ogg"));
			level1Map.put("level1", new Music("res/level1.ogg"));
			whipMap.put("whip", new Music("res/whip.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static Music getMusic(String key) {
		return musicMap.get(key);

	}

	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

	public static Music getNaruto(String key) {
		return narutoMap.get(key);
	}

	public static Music getlevel1(String key) {
		return level1Map.get(key);
	}

	public static Music getwhip(String key) {
		return whipMap.get(key);
	}

}
