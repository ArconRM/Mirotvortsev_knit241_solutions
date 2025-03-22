package org.knit.solutions.task13;

public class TVOffCommand implements Command {
    private final TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        if (tv.isOn()) {
            tv.setOn(false);
        } else {
            System.out.println("Телевизор и так выключен");
        }
    }

    @Override
    public void undo() {
        if (!tv.isOn()) {
            tv.setOn(true);
        } else {
            System.out.println("Телевизор и так включен");
        }
    }
}
