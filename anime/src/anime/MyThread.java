package anime;
public class MyThread {
	public static void main(String[] args) {
		new SecondThread().start();
		new ThirdThread().start();
		for(int i=0; i<10; i++) {
			System.out.println("スレッド１：" + i);
			try {
				Thread.sleep(500);
			} catch(Exception e){};
		}
	}
}
class SecondThread extends Thread {
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println("　　　　　スレッド２：" + i);
			try {
				Thread.sleep(500);
			} catch(Exception e){};
		}
	}
}
class ThirdThread extends Thread {
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println("　　　　　　　　　　　スレッド３：" + i);
			try {
				Thread.sleep(500);
			} catch(Exception e){};
		}
	}
}