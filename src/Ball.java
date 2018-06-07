import java.awt.Rectangle;

public class Ball {
	private int x, y, size;
	public Ball() {
		x = 0;
		y = 0;
		size = 20;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
	public Rectangle getBallBound() {
		return new Rectangle(x,y, size, size);
	}
}
