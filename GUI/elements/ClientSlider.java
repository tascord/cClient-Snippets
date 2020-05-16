package ai.tascord.client.gui.hud.elements;

import ai.tascord.client.Client;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.Gui;

@Getter
public class ClientSlider extends Element {

    @Setter private int x;
    @Setter private int y;
    @Setter private int width;
    @Setter private int height;
    @Setter private float value;

    private float min;
    private float max;

    private int relative_value;

    public ClientSlider(int x, int y, int width, int height, float min, float max, float value) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.min = min;
        this.max = max;

        this.relative_value = 0;
        this.value = value;

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

        Gui.drawRect(
                x - (width / 2),
                y - (height / 2),
                x - (width / 2) + relative_value,
                y + (height / 2) + 1,
                Client.getInstance().getColour()
        );

        drawHollowRect(x - (width / 2), y - (height / 2), width, height, -1);

    }

    @Override
    public void interact(int mouseX, int mouseY) {

        int x_min = x - (width / 2);
        int x_max = x + (width / 2);

        int prc = ((mouseX - x_min) * 100) / (x_max - x_min);
        if(prc < 0) prc = 0;
        if(prc > 100) prc = 100;

        relative_value = (width / 100) * prc;

        //value = (percentage * (max - min) / 100) + min
        value = (prc * (max - min) / 100) + min;

    }

    @Override
    public float getFloat() {
        return value;
    }
}
