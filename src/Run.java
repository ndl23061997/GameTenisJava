import javax.swing.JOptionPane;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		int wndHeight = 0;
		int nax, nay;
		Surface sf = new Surface();
		Window wnd = new Window();		
		wnd.add(sf);
		wnd.setVisible(true);	
		wnd.setResizable(false);
		while (true) {
			if(sf.gameOver == 1) {
				JOptionPane.showMessageDialog(null, "Tro choi ket thuc\n Diem so : " + sf.soccer);
				System.exit(0);
			}
			if(wnd.getHeight() != wndHeight) {
				sf.bat.setLocation(0, sf.getHeight() - 40);
				sf.ball.setLocation(sf.bat.getX() + sf.bat.getWidth()/2 - sf.ball.getSize()/2, sf.bat.getY() - sf.ball.getSize());
			}
			/*if(sf.ball.getX() + sf.ax < 0 ) {
				sf.ball.setLocation(0, sf.ball.getY());
			}
			else if (sf.ball.getX() + sf.ball.size + sf.ax > sf.getWidth()) {
				sf.ball.setLocation(sf.getWidth(), sf.ball.getY());
			}*/
			if(sf.ball.getY() + sf.ball.getSize() > sf.bat.getY() && sf.bat.getBatBound().intersects(sf.ball.getBallBound())) {
				sf.ball.setLocation(sf.ball.getX() + sf.ax, sf.bat.getY() - sf.ball.getSize());
			}
			else {
				sf.ball.setLocation(sf.ball.getX() + sf.ax, sf.ball.getY() + sf.ay);
			}
			sf.repaint();
			
			wndHeight = wnd.getHeight();
			Thread.sleep(20);
		}
	}
}
