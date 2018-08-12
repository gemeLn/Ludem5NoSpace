package game.entity;

import java.awt.Rectangle;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;

public abstract class Interactable {
	Sprite sprite;

	public abstract void action();

	public abstract void render(Screen screen, int dy);

	public Rectangle hitbox;
}
