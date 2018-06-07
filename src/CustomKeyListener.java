import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {
	Bat bat;
	public CustomKeyListener(Bat bat) {
		this.bat = bat;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			bat.setLocation(bat.getX() + 30, bat.getY());
			System.out.println("Right Pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			bat.setLocation(bat.getX() - 30, bat.getY());
			System.out.println("Left Pressed");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
