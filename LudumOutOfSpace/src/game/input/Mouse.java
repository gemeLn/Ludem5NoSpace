package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game;
import game.entity.mob.Player;
import game.level.Shop;

public class Mouse implements MouseListener {
	Shop shop;

	public Mouse(Shop shop, Player p) {
		this.shop = shop;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if (Game.game.state == Game.SHOPSTATE) {
			if (shop.jumpButton.contains(p)) {
				shop.buyJump();
			} else if (shop.speedButton.contains(p)) {
				shop.buySpeed();
			}
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
