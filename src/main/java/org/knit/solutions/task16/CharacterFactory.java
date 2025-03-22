package org.knit.solutions.task16;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
    private final Map<Integer, Character> characters = new HashMap<>();

    public Character getCharacter(Integer code) {
        if (!characters.containsKey(code)) {
            characters.put(code, new ConcreteCharacter(code));
        }
        return characters.get(code);
    }
}
