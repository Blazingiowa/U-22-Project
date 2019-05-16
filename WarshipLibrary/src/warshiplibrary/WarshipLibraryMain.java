package warshiplibrary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class WarshipLibraryMain extends JFrame implements ActionListener							
{
	
	private static JFrame frame;
	JLabel Image;
	int pgc =0;

	public static void main(String[] args)
	{
		WarshipLibraryMain frame = new WarshipLibraryMain("艦艇図鑑");					
		frame.setVisible(true);
	}
	WarshipLibraryMain(String title)
	{

		setTitle(title);//フレームのタイトル表示					
		setBounds(100, 100, 1500, 960);//フレームの位置指定
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//フレーム表示

		JPanel p1 = new JPanel();//背景色を設定
		p1.setLayout(new BorderLayout());

		JPanel p2 = new JPanel();
		p1.add(p2,BorderLayout.CENTER);

		Image = new JLabel("");
		p2.add(Image);

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new
				ImageIcon(cl.getResource("gyazo/title.jpg"));
		Image.setIcon(icon);

		UIManager.put("Button.font", new FontUIResource("HGS 明朝体",Font.BOLD, 16));

		//ボタンを作る
		JButton jb = new JButton("戻る");
		JButton nihon = new JButton("日本");
		JButton america = new JButton("アメリカ");
		JButton doistu = new JButton("ドイツ");
		JButton france = new JButton("フランス");
		JButton uk = new JButton("イギリス");
		JButton soren = new JButton("ソ連");
		JButton taiwan = new JButton("台湾");
		JButton jb1 = new JButton("次へ");

		jb.addActionListener(this);	
		jb1.addActionListener(this);	
		nihon.addActionListener(this);	
		america.addActionListener(this);
		doistu.addActionListener(this);
		france.addActionListener(this);
		uk.addActionListener(this);
		soren.addActionListener(this);
		taiwan.addActionListener(this);
		
		jb.setPreferredSize(new Dimension(100, 50));
		jb1.setPreferredSize(new Dimension(100, 50));

		p2.add(jb);
		p2.add(nihon);
		p2.add(america);
		p2.add(doistu);
		p2.add(france);
		p2.add(uk);
		p2.add(soren);
		p2.add(taiwan);
		p2.add(jb1);

		getContentPane().add(p1,BorderLayout.CENTER);

	}
	MyAudioPlayer title = new MyAudioPlayer("", true);
	MyAudioPlayer click = new MyAudioPlayer("", false);
	MyAudioPlayer us = new MyAudioPlayer("", true);
	MyAudioPlayer front = new MyAudioPlayer("", true);
	MyAudioPlayer doi = new MyAudioPlayer("", true);
	MyAudioPlayer fra = new MyAudioPlayer("", true);
	MyAudioPlayer roi = new MyAudioPlayer("", true);
	MyAudioPlayer ussr = new MyAudioPlayer("", true);
	MyAudioPlayer taiwan = new MyAudioPlayer("", true);
	MyAudioPlayer end = new MyAudioPlayer("", true);
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自動生成されたメソッド・スタブ
		String cmd = e.getActionCommand();
		if(cmd.equals("日本"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=2;
			
		}
		if(cmd.equals("アメリカ"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=42;
		}
		if(cmd.equals("ドイツ"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=79;
		}
		if(cmd.equals("フランス"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=106;
		}
		if(cmd.equals("イギリス"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=124;
		}
		if(cmd.equals("ソ連"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=151;
		}
		if(cmd.equals("台湾"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=170;
		}
		if(cmd.equals("次へ"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc++;
		}
		if(pgc>2&&cmd.equals("戻る"))
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc--;
		}
		
		if(pgc!=1)
		{
			front.close();
		}
		
		if(cmd.equals("次へ")&&pgc==178)
		{
			click = new MyAudioPlayer("music/click.mp3", false);
			pgc=1;
		}

		if(pgc==1)
		{
			front.close();
			end.close();
			title.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/front.jpg"));
			Image.setIcon(icon);
			front = new MyAudioPlayer("music/front.mp3", true);
		}
		if(pgc==2) 
		{
			roi.close();
			us.close();
			title.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			title = new MyAudioPlayer("music/title.mp3", true);
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/nihon.jpg"));
			Image.setIcon(icon);
		}
		if(pgc==3) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/kawachi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==4) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/myogi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==5) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/kongo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==6) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/fuso.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==7) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/nagato.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==8) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/amagi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==9) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/izumo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==10) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/yamato.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==11) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/tenryu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==12) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/kuma.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==13) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/furutaka.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==14) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/aoba.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==15) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/myoko.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==16) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/takao.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==17) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/mogami.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==18) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/ibuki.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==19) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gyazo/zao.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==20) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/umikaze.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==21) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/wakatake.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==22) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/isokaze.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==23) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/minekaze.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==24) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/mutsuki.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==25) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/hatsuharu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==26) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/fubuki.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==27) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/akatsuki.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==28) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/shiratsuyu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==29) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/kagero.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==30) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/akiduki.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==31) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/yugumo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==32) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/kitakaze.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==33) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/shimakaze.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==34) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpdd/harugumo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==35) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/hosyo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==36) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/zuiho.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==37) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/ryujo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==38) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/hiryu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==39) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/syokaku.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==40) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/taiho.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==41) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("jpcv/hakuryu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==42) 
		{
			roi.close();
			title.close();
			us.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/ustitle.jpg"));
			Image.setIcon(icon);
			us = new MyAudioPlayer("music/usnavy.mp3", true);
		}
		if(pgc==43) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/saucaro.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==44) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/waio.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==45) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/nyuyo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==46) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/nyume.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==47) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/colorado.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==48) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/nosucaro.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==49) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/iowa.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==50) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usbb/montana.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==51) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/sento.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==52) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/phenix.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==53) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/omaha.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==54) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/pensa.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==55) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/darasu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==56) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/nyuori.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==57) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/herena.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==58) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/boruti.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==59) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/kuribu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==60) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/baff.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==61) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/shiatoru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==62) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/usuta.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==63) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usca/demo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==64) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/sanpu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==65) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/kuremu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==66) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/niko.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==67) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/fara.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==68) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/mahan.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==69) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/benson.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==70) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/fure.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==71) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("usdd/gia.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==72) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/lang.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==73) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/bogu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==74) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/indi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==75) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/rang.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==76) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/lex.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==77) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/ese.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==78) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("uscv/mid.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==79) 
		{
			roi.close();
			title.close();
			us.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/doitsu.jpg"));
			Image.setIcon(icon);
			doi = new MyAudioPlayer("music/doistu.mp3", true);
		}
		if(pgc==80) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/nassau.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==81) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/kaiza.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==82) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/kenihi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==83) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/baie.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==84) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/syarun.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==85) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/bis.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==86) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/fri.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==87) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmbb/guro.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==88) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/koru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==89) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/karu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==90) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/kenihisu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==91) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/nyurun.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==92) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/yoru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==93) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/ado.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==94) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/roon.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==95) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmca/hinden.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==96) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/v25.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==97) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/g101.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==98) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/v170.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==99) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/t22.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==100) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/erun.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==101) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/mass.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==102) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/z23.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==103) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/z46.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==104) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/z52.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==105) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("gmdd/graf.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==106) 
		{
			roi.close();
			fra.close();
			title.close();
			us.close();
			doi.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/france.jpg"));
			Image.setIcon(icon);
			fra = new MyAudioPlayer("music/france.mp3",true);
		}
		if(pgc==107) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/tyure.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==108) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/curube.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==109) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/pro.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==110) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/noru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==111) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/lyon.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==112) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/danke.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==113) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/risyu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==114) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/alsasu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==115) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frbb/repu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==116) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/frian.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==117) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/dugei.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==118) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/emiru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==119) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/gari.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==120) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/aruje.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==121) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/maruteru.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==122) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/sanrui.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==123) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("frca/anri.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==124) 
		{
			roi.close();
			title.close();
			us.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/roiyaru.jpg"));
			Image.setIcon(icon);
			roi = new MyAudioPlayer("music/london.mp3", true);
		}
		if(pgc==125) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/dore.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==126) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/berero.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==127) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/orai.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==128) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/aian.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==129) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/qeen.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==130) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/king.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==131) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/mona.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==132) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/lion.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==133) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsbb/konka.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==134) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/kare.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==135) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/danai.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==136) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/eme.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==137) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/rian.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==138) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/fiji.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==139) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/edin.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==140) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/nepu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==141) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsca/mino.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==142) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/m.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==143) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/v.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==144) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/w.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==145) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/a.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==146) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/i.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==147) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/j.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==148) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/l.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==149) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/battle.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==150) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("hmsdd/d.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==151) 
		{
			roi.close();
			title.close();
			us.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/ussr.jpg"));
			Image.setIcon(icon);
			ussr = new MyAudioPlayer("music/peterburg.mp3", true);
		}
		if(pgc==152) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/kirohu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==153) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/makushimu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==154) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/bujo.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==155) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/sho.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==156) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/chapa.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==157) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/donsukoi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==158) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/suve.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==159) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/mosukuwa.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==160) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrca/sutarin.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==161) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/pado.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==162) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/gune.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==163) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/minsuku.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==164) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/tasyu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==165) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/kiehu.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==166) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/ogune.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==167) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/udaroi.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==168) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/habaro.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==169) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("ussrdd/guroza.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==170) 
		{
			roi.close();
			us.close();
			title.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			title = new MyAudioPlayer("music/sentoukaishi.mp3", true);
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/taiwan.jpg"));
			Image.setIcon(icon);
		}
		if(pgc==171) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/tyonan.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==172) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/ronjan.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==173) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/shenyan.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==174) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/janwei.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==175) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/shenyan2.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==176) 
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("taiwandd/shanyan.jpg"));
			Image.setIcon(icon);	
		}
		if(pgc==177) 
		{
			roi.close();
			title.close();
			us.close();
			doi.close();
			fra.close();
			ussr.close();
			taiwan.close();
			end.close();
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new
					ImageIcon(cl.getResource("titleus/endtitle.jpg"));
			Image.setIcon(icon);
			end = new MyAudioPlayer("music/end.mp3", true);
		}
	}
}