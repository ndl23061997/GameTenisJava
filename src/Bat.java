import java.awt.Rectangle;

public class Bat {
	private int x, y, width, height;
	public Bat() {
		x = 0;
		y = 0;
		width = 70;
		height = 20;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBatBound() {
		return new Rectangle(x,y, width, height);
	}
}
