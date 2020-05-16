package ai.tascord.client.gui.hud.elements;

import ai.tascord.client.Client;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

@Getter
public class ClientButton extends Element {

    @Setter private String text;
    @Setter private int x;
    @Setter private int y;
    @Setter private int width;
    @Setter private int height;
    @Setter private int colour;

    private int anim;

    public ClientButton(String text, int x, int y, int width, int height, int colour) {

        this.text = text;

        this.x = x;
        this.y = y;

        if(width == -1) this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) + 20;
        else this.width = width;

        this.height = height;

        this.anim = 0;

        this.colour = colour;

    }

    public ClientButton(String text, int x, int y, int width, int height) {

        this.text = text;

        this.x = x;
        this.y = y;

        if(width == -1) this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) + 20;
        else this.width = width;

        this.height = height;

        this.anim = 0;

        this.colour = -1;

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

    public void draw() {

        Gui.drawRect(
                x - (width / 2),
                y + (height / 2) - (anim / 2),
                x + (width / 2) + 1,
                y + (height / 2),
                Client.getInstance().getColour()
        );

        drawHollowRect(x - (width / 2), y - (height / 2), width, height, -1);

        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawString(text, x - (font.getStringWidth(text) / 2), y - (font.FONT_HEIGHT / 2), colour);

    }

    @Override
    public void hover() {
        if(y + (height / 2) - (anim / 2) > y - (height / 2)) anim+=2;
    }

    @Override
    public void unHover() {
        if(anim > 1) anim-=3;
    }

    @Override
    public String getString() {
        return text;
    }
}
