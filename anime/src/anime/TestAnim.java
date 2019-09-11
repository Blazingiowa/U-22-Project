package anime;

import java.awt.Frame;
import java.awt.Graphics;

public class TestAnim extends Frame {
	int x = 0;

	TestAnim() {
		this.setSize(400, 300);
		setVisible(true);
		run();				// アニメーション開始
	}
	public static void main(String[] a) {
		new TestAnim();
	}
	public void run() {
		while (true) {			// 無限ループ
			x = x + 1;
			if (x > 400) x = 0;
			repaint();		// 画面更新
			try {
				Thread.sleep(10); // 画面更新間隔指定
			} catch (Exception e) { }
		}
	}
	public void paint(Graphics g) {
		g.drawOval(x, 100, 50, 50);	// 実際の画像
	}
}