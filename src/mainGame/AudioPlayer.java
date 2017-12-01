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
	public static Map<String, Music> level2Map = new HashMap<String, Music>();
	public static Map<String, Music> level3Map = new HashMap<String, Music>();
	public static Map<String, Music> wastedMap = new HashMap<String, Music>();
	public static Map<String, Music> transitionMap = new HashMap<String, Music>();

	public static void load() {
		try {
			soundMap.put("sound", new Sound("res/ClickSound.ogg"));
			musicMap.put("music", new Music("res/MainMenu.ogg"));
			narutoMap.put("naruto", new Music("res/Naruto.ogg"));
			level1Map.put("level1", new Music("res/level1.ogg"));
			whipMap.put("whip", new Music("res/whip.ogg"));
			level2Map.put("level2", new Music("res/level2.ogg"));
			wastedMap.put("wasted", new Music("res/wasted.ogg"));
			transitionMap.put("transition", new Music("res/transition.ogg"));
			level3Map.put("level3", new Music("res/level3.ogg"));
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

	public static Music getlevel2(String key) {
		return level2Map.get(key);
	}

	public static Music getlevel3(String key) {
		return level3Map.get(key);
	}

	public static Music getwasted(String key) {
		return wastedMap.get(key);
	}

	public static Music gettransition(String key) {
		return transitionMap.get(key);
	}
}
