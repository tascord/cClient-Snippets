package ai.tascord.client.gui.hud.elements;

import ai.tascord.client.Client;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.Gui;

@Getter
public class ClientToggle extends Element {

    @Setter private int x;
    @Setter private int y;
    @Setter private int width;
    @Setter private int height;
    @Setter private boolean position;

    private int anim;

    public ClientToggle(int x, int y, int width, int height, boolean position) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.position = position;

        this.anim = 0;

    }

    @Override
    public boolean mouseOverlap(int mouseX, int mouseY) {

        if(mouseX >= x - (width / 2) && mouseX <= x + (width / 2)) {
            if(mouseY >= y - (height / 2) && mouseY <= y + (height / 2)) {
                return true;
            }
        }

        return false;

    }

    @Override
    public void draw() {

        if(position && anim < width) anim++;
        else if(!position && anim > 0) anim -= 3;

        Gui.drawRect(
                x - (width / 2),
                y - (height / 2),
                x - (width / 2) + anim,
                y + (height / 2),
                Client.getInstance().getColour()
        );

        int toggleX = anim;

        Gui.drawRect(x - (width / 2) - (width / 10) + toggleX, y - (height / 2) - (width / 10), x - 10 + toggleX, y + (height / 2) + (width / 10), -1);

        drawHollowRect(x - (width / 2), y - (height / 2), width, height, -1);

    }

    @Override
    public void interact(int mouseX, int mouseY) {
        position = !position;
    }

    @Override
    public boolean getBool() {
        return position;
    }
}
