package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Game;
import game.entity.items.Item;
import game.entity.mob.Player;
import game.level.Menu;
import game.level.Shop;

public class Mouse implements MouseListener, MouseMotionListener {
	Shop shop;
	Menu menu;
	Player player;

	public Mouse(Shop shop, Menu menu, Player p) {
		this.shop = shop;
		this.menu = menu;
		this.player = p;
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if (Game.game.state == Game.SHOPSTATE) {
			boolean removeToolTip = true;
			for (Item shopitem : shop.availableShop) {
				if (shopitem.hitbox.contains(p)) {
					removeToolTip = false;
					shop.tooltipOn = true;
					shop.tooltipItem = shopitem;
					shop.tooltipCost = true;
				}
			}
			for (int i = 0; i < player.inventory.size(); i++) {
				Item item = player.inventory.get(i);
				if (item.hitbox.contains(p)) {
					removeToolTip = false;
					shop.tooltipOn = true;
					shop.tooltipItem = item;
					shop.tooltipCost = false;
				}
			}
			if (removeToolTip) {
				shop.tooltipOn = false;
			}
		} else if (Game.game.state == Game.MENUSTATE) {
			if (menu.start.contains(p)) {
				menu.sprite2();
			} else {
				menu.sprite1();
			}
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
			for (int i = 0; i < shop.availableShop.size(); i++) {
				Item shopitem = shop.availableShop.get(i);
				if (shopitem.hitbox.contains(p)) {
					shop.buy(shopitem);
				}
			}
			for (int i = 0; i < player.inventory.size(); i++) {
				Item item = player.inventory.get(i);
				if (item.hitbox.contains(p)) {
					Item temp = player.inventory.get(0);
					player.inventory.set(0, item);
					player.inventory.set(i, temp);
				}
			}
		}
		if (Game.game.state == Game.MENUSTATE) {
			if (menu.start.contains(p)) {
				menu.startGame();
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
