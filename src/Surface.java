import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Surface extends JPanel implements ActionListener {
	int soccer;
	Timer timer;
	Random rd = new Random();
	int ax, ay; // Gia toc cua qua bong
	int move;
	public int gameOver;
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
		// Nhan va xua li su kien khi nguoi choi nhan phim
		@Override
		public void keyPressed(KeyEvent e) {
			int xbat = (getWidth() - bat.getWidth())/15; // khoang cach di chuyen vot
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_LEFT:
					if(bat.getX() - xbat < 0) {
						bat.setLocation(0, bat.getY()); // Di chuyen cay vot vao trong khi ra ngoai man hinh
						if(ax == 0 && ay == 0) { // Dua bong vao trong khi bi ra ngoai
							ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
						} 
						move = -1;
						return;
					}
					
					if(xbat > 30) xbat = 30;
					bat.setLocation(bat.getX() - xbat, bat.getY());
					// Neu tro choi chua bat dau , dua bong vao giua vot
					if(ax == 0 && ay == 0) { 
						ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
					}
					System.out.println("Left Pressed");
					move = -1;
					
					break;
				case KeyEvent.VK_RIGHT:
					if(bat.getX() + bat.getWidth() + xbat > getWidth()) { // Neu vot cua ra ngoai man hinh
						bat.setLocation(getWidth() - bat.getWidth(), bat.getY());
						if(ax == 0 && ay == 0) {
							ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
						}
						move = 1;
						return;
					}
					
					if(xbat > 30 ) xbat = 30;
					bat.setLocation(bat.getX() + xbat, bat.getY());
					// Neu tro choi chua bat dau , dua bong vao giua vot
					if(ax == 0 && ay == 0) {
						ball.setLocation(bat.getX() + bat.getWidth()/2 - ball.getSize()/2, bat.getY() - ball.getSize());
					}
					System.out.println("Right Pressed");
					move = 1;
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
	// Hien thi diem cua nguoi choi
	private void drawSoccer(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.red);
		g2d.setFont(new Font("Cosolas", Font.BOLD, 20));
		g2d.drawString("Soccer : " + Integer.toString(this.soccer), 30, 30);
		g2d.dispose();
	}
	
	public Surface() {
		initTimer();
		ax = 0;
		ay = 0;
		bat = new Bat();
		ball = new Ball();
		System.out.println(this.getHeight());
		this.addKeyListener(kl);
		this.setFocusable(true);
	}
	
	private void initTimer() {
		timer = new Timer(500, this);
		timer.start();
	}
	// Ve cay vot
	private void drawBat(Graphics g, Bat bat) {
		g.setColor(Color.black);
		g.fillRoundRect(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight(), 10,10);
	}
	// Ve bong tren man hinh
	private void drawBall(Graphics g, Ball ball) {
		g.setColor(Color.black);
		g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBat(g, bat);
		drawBall(g,ball);
		drawSoccer(g);
		if(isCollision()) gameOver = 1;
	}
	
	public Rectangle getSurfaceBound() {
		return new Rectangle(0,0, this.getWidth(), this.getHeight());
	}
	// Xu li va cham (impact)
	public boolean isCollision() { 
		boolean die = false;
		if(ball.getY() >= this.getHeight()) return true;
		// Khi bong va cham voi thanh man hinh
		if(this.getSurfaceBound().intersects(ball.getBallBound())) {
			
			if(ball.getX() + ball.getSize() >= this.getWidth() || ball.getX() <= 0) {
				ax = ax*-1;
			}
			else if(ball.getY() <= 0) {
				ay = ay*-1;
			}
		}
		// Khi bong va cham voi vot
		if(bat.getBatLeftBound().intersects(ball.getBallBound()) || bat.getBatLeftBound().intersects(ball.getBallBound())) {
			ay = ay*-1;
			while (ax == 0) ax = rd.nextInt() % 3;
			ax = ax*-1;
			this.soccer ++;
			// Tang do kho cho game moi khi nguoi dat 5 diem
			if(soccer % 5 == 0) {
				if(ax < 0) ax --;
				else ax ++;
				ay--;
			}
		}
		else if(bat.getBatBound().intersects(ball.getBallBound())) {
			ay = ay*-1;
			while (ax == 0) ax = rd.nextInt() % 3;
			if(ax*move < 0) ax = ax*-1;
			this.soccer ++;
			// Tang do kho cho game moi khi nguoi dat 5 diem
			if(soccer % 5 == 0) {
				if(ax < 0) ax --;
				else ax ++;
				ay--;
			}
		}
		
		
		return die;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move = 0;
	}
}
