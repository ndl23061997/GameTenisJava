
public class Run {
	public static void main(String[] args) throws InterruptedException {
		int wndHeight = 0;
		Surface sf = new Surface();
		Window wnd = new Window();		
		wnd.add(sf);
		wnd.setVisible(true);	
		while (true) {
			if(wnd.getHeight() != wndHeight) {
				sf.bat.setLocation(0, sf.getHeight() - 40);
				sf.ball.setLocation(sf.bat.getX() + sf.bat.getWidth()/2 - sf.ball.getSize()/2, sf.bat.getY() - sf.ball.getSize());
			}
			sf.ball.setLocation(sf.ball.getX() + sf.ax, sf.ball.getY() + sf.ay);
			sf.repaint();
			wndHeight = wnd.getHeight();
			Thread.sleep(20);
		}
	}
}
