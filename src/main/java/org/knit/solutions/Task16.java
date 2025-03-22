package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.task16.Character;
import org.knit.solutions.task16.CharacterExternalState;
import org.knit.solutions.task16.CharacterFactory;

@TaskDescription(taskNumber = 16, taskDescription = "Задача 16 на паттерн Приспособленец (Flyweight)")
public class Task16 implements Solution {

    @Override
    public void execute() {
        CharacterFactory factory = new CharacterFactory();

        Character character1 = factory.getCharacter(1);
        Character character2 = factory.getCharacter(2);
        Character character3 = factory.getCharacter(3);

        character1.render(new CharacterExternalState(0, 0, "Arial"));
        character2.render(new CharacterExternalState(1, 2, "Times New Roman"));
        character3.render(new CharacterExternalState(0, 0, "Arial"));
    }
}
