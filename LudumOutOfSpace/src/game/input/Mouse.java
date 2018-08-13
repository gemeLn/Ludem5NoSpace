package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Game;
import game.entity.items.Item;
import game.entity.mob.Player;
import game.level.Shop;

public class Mouse implements MouseListener, MouseMotionListener {
	Shop shop;

	public Mouse(Shop shop, Player p) {
		this.shop = shop;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		boolean removeToolTip = true;
		for (Item shopitem : shop.availableShop) {
			if (shopitem.hitbox.contains(p)) {
				removeToolTip = false;
				shop.tooltipOn = true;
				shop.tooltipItem = shopitem;
			}
		}
		if (removeToolTip) {
			shop.tooltipOn = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if (e.getButton() == MouseEvent.BUTTON3) {
			System.out.println(e.getX() / 2 + "," + e.getY() / 2);
		}
		if (Game.game.state == Game.SHOPSTATE) {
			if (shop.jumpButton.contains(p)) {
				shop.buyJump();
			} else if (shop.speedButton.contains(p)) {
				shop.buySpeed();
			} else if (shop.exitButton.contains(p)) {
				shop.exit();
			}
			for (Item shopitem : shop.availableShop) {
				if (shopitem.hitbox.contains(p)) {
					shop.buy(shopitem);
				}
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
