package org.knit.solutions.task16;

public class ConcreteCharacter implements Character {
    private final int code;

    public ConcreteCharacter(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public void render(CharacterExternalState externalState) {
        System.out.println("Символ с кодом " + code + " и внешним состоянием: " + externalState.toString());
    }
}
