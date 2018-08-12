package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game;
import game.level.Shop;

public class Mouse implements MouseListener {
	Shop shop;

	public Mouse(Shop shop) {
		this.shop = shop;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		if (Game.game.state == Game.SHOPSTATE) {
			System.out.println("OKOKOKOKOK");
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {

	}

}
