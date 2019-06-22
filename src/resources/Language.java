package resources;

import java.util.HashMap;
import java.util.Map;

public enum Language {
	English("1"), Italian("2"), Japanese("3"), Mandarin("4"), French("5"), German("6");
	
	private final String id;
	
	private static final Map<String, Language> BY_NAME = new HashMap<>();
	
	static {
		for (Language e : values()) {
			BY_NAME.put(e.id, e);
		}
	}

	Language(String id) {
		this.id = id;
	}

	public static Language name(String id) {
		return BY_NAME.get(id);
	}

}
