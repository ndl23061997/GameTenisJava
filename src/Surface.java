import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

public class Surface extends JPanel{
	Random rd = new Random();
	int ax, ay; // Gia toc cua qua bong
	public Bat bat;
	public Ball ball;
	KeyListener kl = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_LEFT:
					if(bat.getX() <= bat.getWidth()) {
						bat.setLocation(0, bat.getY());
						if(ax == 0 && ay == 0) {
							ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
						}
						return;
					}
					bat.setLocation(bat.getX() - (getWidth() - bat.getWidth()) / 5, bat.getY());
					if(ax == 0 && ay == 0) {
						ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
					}
					System.out.println("Left Pressed");
					break;
				case KeyEvent.VK_RIGHT:
					if(getWidth() - (bat.getX() + bat.getWidth()) <= bat.getWidth()) {
						bat.setLocation(getWidth() - bat.getWidth(), bat.getY());
						if(ax == 0 && ay == 0) {
							ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
						}
						return;
					}
					bat.setLocation(bat.getX() + (getWidth() - bat.getWidth()) / 5, bat.getY());
					if(ax == 0 && ay == 0) {
						ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
					}
					System.out.println("Right Pressed");
					break;
				case KeyEvent.VK_ENTER:
					if(ax == 0 && ay == 0) {
						ay = -2;
						ax = rd.nextInt() % 3;
					}
					break;
			}
		}
	};
	public Surface() {
		ax = 0;
		ay = 0;
		bat = new Bat();
		ball = new Ball();
		System.out.println(this.getHeight());
		this.addKeyListener(kl);
		this.setFocusable(true);
	}
	
	private void drawBat(Graphics g, Bat bat) {
		g.setColor(Color.black);
		g.fillRect(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight());
	}
	
	private void drawBall(Graphics g, Ball ball) {
		g.setColor(Color.black);
		g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBat(g, bat);
		drawBall(g,ball);
		isCollision();
	}
	
	public Rectangle getSurfaceBound() {
		return new Rectangle(0,0, this.getWidth(), this.getHeight());
	}
	
	private boolean isCollision() {
		boolean die = false;
		if(this.getSurfaceBound().intersects(ball.getBallBound())) {
			if(ball.getX() + ball.getSize() >= this.getWidth() || ball.getX() <= 0) {
				ax = ax*-1;
			}
			
			if(ball.getY() <= 0) {
				ay = ay*-1;
			}
			
			if(ball.getY() == this.getHeight()) die = true;
		}
			
		if(bat.getBatBound().intersects(ball.getBallBound())) {
			ay = ay*-1;
		}
		return die;
	}
}
