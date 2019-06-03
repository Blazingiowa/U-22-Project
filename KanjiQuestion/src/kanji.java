import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class kanji extends JFrame implements ActionListener {
	String mondai = "漢字検定マラソン！";
	String mainq = "全120問！";
	String hint = "目指せ完走！";
	String setsumei = "ルール";
	String setsumei2 = "自分の漢字力の限界に挑戦！HPが無くなったら即終了！";
	String setsumei3 = "準備OKなら挑戦する級を選択して決定をクリック！";
	String no1 = "選択肢1";
	String no2 = "選択肢2";
	String no3 = "選択肢3";
	String no4 = "選択肢4";
	String no5 = "選択肢5";
	String no6 = "選択肢6";
	int kyu = 10;
	int hp = 10;
	int pgc = 0;
	int kirikae = 1;
	int hantei = 0;
	JRadioButton radio1, radio2, radio3, radio4, radio5, radio6;
	JLabel systemmess;
	JLabel kara;
	JLabel kara2;
	JLabel kara3;
	JLabel shintyu;
	JLabel shintyu2;
	JLabel shintyu3;
	JPanel p1;
	JPanel toi;
	JPanel rule;
	JPanel meireibun;
	JPanel dodai;
	JPanel stmenu;
	String[] kyustr= {"10級","9級","8級","7級","6級","5級","4級","3級","準2級","2級","準1級","1級"};

	JLabel title1;
	JLabel subt;
	JLabel mokuhyo;
	JLabel kuuhaku;
	JLabel crulr1;
	JLabel crulr2;
	JLabel crulr3;
	JRadioButton[] kyubutton=new JRadioButton[12];
	CardLayout clay = new CardLayout();

	MyAudioPlayer bgm = new MyAudioPlayer("", false);
	MyAudioPlayer voice = new MyAudioPlayer("", false);

	public static void main(String[] args) {
		kanji frame = new kanji("漢字検定");
		frame.setVisible(true);
	}

	kanji(String title)
	{

		setTitle(title);
		setBounds(100, 100, 1700, 850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dodai= new JPanel();
		dodai.setLayout(clay);

		stmenu=new JPanel();
		stmenu.setBackground(new Color(83,122,122));
		stmenu.setLayout(new GridLayout(4,3));

		dodai.add(stmenu);
		ButtonGroup kyugroup = new ButtonGroup();

		title1 = new JLabel(mondai);
		title1.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		title1.setHorizontalAlignment(JTextField.CENTER);
		stmenu.add(title1);

		subt = new JLabel(mainq);
		subt.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		subt.setHorizontalAlignment(JTextField.CENTER);
		stmenu.add(subt);

		mokuhyo = new JLabel(hint);
		mokuhyo.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		mokuhyo.setHorizontalAlignment(JTextField.CENTER);
		stmenu.add(mokuhyo);

		for(int i=0;i<12;i++)
		{
		kyubutton[i]=new JRadioButton();
		kyubutton[i] = new JRadioButton(kyustr[i]);
		kyubutton[i].setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));
		kyugroup.add(kyubutton[i]);
		}

		JPanel[] nanido=new JPanel[6];
		for(int i=0,j=0;i<6;i++)
		{
		nanido[i]=new JPanel();
		nanido[i].setLayout(new GridLayout(1,1));
		stmenu.add(nanido[i]);
		nanido[i].add(kyubutton[j++]);
		nanido[i].add(kyubutton[j++]);
		}

		kuuhaku=new JLabel();
		kuuhaku.setBackground(new Color(181,68,238));
		stmenu.add(kuuhaku);
		
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("back/wa.jpg"));
		kuuhaku.setIcon(icon);

		JPanel strule=new JPanel();
		strule.setBackground(new Color(153,238,0));
		strule.setLayout(new GridLayout(3,1));
		stmenu.add(strule);

		JButton next=new JButton("決定");
		next.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));
		next.addActionListener(this);
		stmenu.add(next);
		
		crulr1 = new JLabel(setsumei);
		crulr1.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
		crulr1.setHorizontalAlignment(JTextField.LEFT);

		crulr2 = new JLabel(setsumei2);
		crulr2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		crulr2.setHorizontalAlignment(JTextField.LEFT);

		crulr3 = new JLabel(setsumei3);
		crulr3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 22));
		crulr3.setHorizontalAlignment(JTextField.CENTER);
		crulr3.setForeground(Color.red);
		
		strule.add(crulr1);
		strule.add(crulr2);
		strule.add(crulr3);

		//メインパネル
		p1 = new JPanel();
		p1.setBackground(new Color(153,238,0));
		p1.setLayout(new GridLayout(4, 3));
		dodai.add(p1);

		toi = new JPanel();
		toi.setBackground(new Color(153,238,0));
		toi.setLayout(new GridLayout(1,1));

		rule = new JPanel();
		rule.setBackground(new Color(153,238,0));
		rule.setLayout(new GridLayout(3,1));

		meireibun = new JPanel();
		meireibun.setBackground(new Color(153,238,0));
		meireibun.setLayout(new GridLayout(1,1));

		radio1 = new JRadioButton(no1);
		radio1.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		radio2 = new JRadioButton(no2);
		radio2.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		radio3 = new JRadioButton(no3);
		radio3.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		radio4 = new JRadioButton(no4);
		radio4.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		radio5= new JRadioButton(no5);
		radio5.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		radio6 = new JRadioButton(no6);
		radio6.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 50));

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
		group.add(radio5);
		group.add(radio6);

		shintyu = new JLabel(setsumei);
		shintyu.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 40));

		shintyu2 = new JLabel(setsumei2);
		shintyu2.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 20));

		shintyu3 = new JLabel(setsumei3);
		shintyu3.setFont(new Font("ＭＳ Ｐ明朝", Font.CENTER_BASELINE, 30));
		shintyu3.setHorizontalAlignment(JTextField.CENTER);
		shintyu3.setForeground(Color.red);

		JButton button = new JButton("回答");

		button.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 40));
		JButton button2 = new JButton("次へ");

		button2.setFont(new Font("ＭＳ ゴシック", Font.CENTER_BASELINE, 40));

		button.addActionListener(this);
		button2.addActionListener(this);

		systemmess = new JLabel(mondai);
		systemmess.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		systemmess.setHorizontalAlignment(JTextField.CENTER);

		kara = new JLabel(mainq);
		kara.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		kara.setHorizontalAlignment(JTextField.CENTER);

		kara2 = new JLabel(hint);
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		kara2.setHorizontalAlignment(JTextField.CENTER);

		p1.add(systemmess);
		p1.add(toi);
		p1.add(meireibun);

		p1.add(radio1);
		p1.add(radio2);
		p1.add(radio3);
		p1.add(radio4);
		p1.add(radio5);
		p1.add(radio6);

		p1.add(button);
		p1.add(rule);
		p1.add(button2);

		toi.add(kara);

		rule.add(shintyu);
		rule.add(shintyu2);
		rule.add(shintyu3);

		meireibun.add(kara2);

		getContentPane().add(dodai,BorderLayout.CENTER);
	}

	public void Jukyu() {
		
		p1.setBackground(new Color(247, 129, 190));

		systemmess.setText(kyu+"級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi1.jpg"));
		kara.setIcon(icon);

		no1 = "の";
		no2 = "え";
		no3 = "こ";
		no4 = "ぽ";
		no5 = "ぬ";
		no6 = "た";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		pgc++;
	}

	public void Jukyu2() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi2.jpg"));
		kara.setIcon(icon);

		no1 = "こん";
		no2 = "よん";
		no3 = "おん";
		no4 = "ほん";
		no5 = "こん";
		no6 = "とん";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu3() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi3.jpg"));
		kara.setIcon(icon);

		no1 = "こやし";
		no2 = "じやし";
		no3 = "さやし";
		no4 = "もやし";
		no5 = "はやし";
		no6 = "いやし";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu4() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi4.jpg"));
		kara.setIcon(icon);

		no1 = "かわ";
		no2 = "さわ";
		no3 = "しわ";
		no4 = "きわ";
		no5 = "じわ";
		no6 = "のわ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu5() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi5.jpg"));
		kara.setIcon(icon);

		no1 = "ぼら";
		no2 = "きら";
		no3 = "そら";
		no4 = "はら";
		no5 = "びら";
		no6 = "わら";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu6() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi6.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 書き");

		hint = "次の下線部の書きを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "八";
		no2 = "玉";
		no3 = "気";
		no4 = "木";
		no5 = "金";
		no6 = "見";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu7() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi7.jpg"));
		kara.setIcon(icon);

		no1 = "町";
		no2 = "花";
		no3 = "石";
		no4 = "草";
		no5 = "森";
		no6 = "雨";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu8() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi8.jpg"));
		kara.setIcon(icon);

		no1 = "五";
		no2 = "虫";
		no3 = "口";
		no4 = "校";
		no5 = "左";
		no6 = "三";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu9() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi9.jpg"));
		kara.setIcon(icon);

		no1 = "六";
		no2 = "名";
		no3 = "休";
		no4 = "青";
		no5 = "赤";
		no6 = "四";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Jukyu10() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi10.jpg"));
		kara.setIcon(icon);

		no1 = "天";
		no2 = "文";
		no3 = "千";
		no4 = "先";
		no5 = "入";
		no6 = "日";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu() {
		p1.setBackground(new Color(159, 129, 247));
		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi11.jpg"));
		kara.setIcon(icon);

		no1 = "えん";
		no2 = "さん";
		no3 = "うみ";
		no4 = "かみ";
		no5 = "かい";
		no6 = "よい";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu2() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi12.jpg"));
		kara.setIcon(icon);

		no1 = "わん";
		no2 = "えん";
		no3 = "じん";
		no4 = "さん";
		no5 = "かん";
		no6 = "にん";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu3() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi13.jpg"));
		kara.setIcon(icon);

		no1 = "いもうと";
		no2 = "わこうど";
		no3 = "ささづけ";
		no4 = "しんがり";
		no5 = "こがらし";
		no6 = "にんたい";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu4() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi14.jpg"));
		kara.setIcon(icon);

		no1 = "みえ";
		no2 = "さえ";
		no3 = "なえ";
		no4 = "かえ";
		no5 = "さえず";
		no6 = "い";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu5() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi15.jpg"));
		kara.setIcon(icon);

		no1 = "じ";
		no2 = "は";
		no3 = "み";
		no4 = "そ";
		no5 = "き";
		no6 = "も";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu6() {
		systemmess.setText(kyu + "級 書き");

		hint = "次の下線部の書きを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi16.jpg"));
		kara.setIcon(icon);

		no1 = "夏";
		no2 = "雲";
		no3 = "園";
		no4 = "遠";
		no5 = "生";
		no6 = "星";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu7() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi17.jpg"));
		kara.setIcon(icon);

		no1 = "細";
		no2 = "寺";
		no3 = "作";
		no4 = "矢";
		no5 = "市";
		no6 = "図";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu8() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi18.jpg"));
		kara.setIcon(icon);

		no1 = "用";
		no2 = "番";
		no3 = "茶";
		no4 = "池";
		no5 = "才";
		no6 = "食";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu9() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi19.jpg"));
		kara.setIcon(icon);

		no1 = "午";
		no2 = "古";
		no3 = "戸";
		no4 = "京";
		no5 = "万";
		no6 = "千";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Kyukyu10() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi20.jpg"));
		kara.setIcon(icon);

		no1 = "電";
		no2 = "長";
		no3 = "鳥";
		no4 = "朝";
		no5 = "直";
		no6 = "通";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "判定";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu() {
		p1.setBackground(new Color(243, 247, 129));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi21.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "あおき";
		no2 = "やよい";
		no3 = "くすり";
		no4 = "どんき";
		no5 = "くさり";
		no6 = "かんき";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu2() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi22.jpg"));
		kara.setIcon(icon);

		no1 = "こおり";
		no2 = "こやり";
		no3 = "こうり";
		no4 = "こまり";
		no5 = "こでり";
		no6 = "こじり";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu3() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi23.jpg"));
		kara.setIcon(icon);

		no1 = "あい";
		no2 = "あし";
		no3 = "あり";
		no4 = "あそ";
		no5 = "あご";
		no6 = "あき";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu4() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi24.jpg"));
		kara.setIcon(icon);

		no1 = "なし";
		no2 = "ばし";
		no3 = "こし";
		no4 = "そし";
		no5 = "おし";
		no6 = "だし";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu5() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi25.jpg"));
		kara.setIcon(icon);

		no1 = "さつ";
		no2 = "しつ";
		no3 = "もつ";
		no4 = "こつ";
		no5 = "じつ";
		no6 = "ぴつ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu6() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi26.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 書き");

		hint = "次の下線部の書きを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "仕";
		no2 = "皿";
		no3 = "港";
		no4 = "号";
		no5 = "根";
		no6 = "祭";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu7() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi27.jpg"));
		kara.setIcon(icon);

		no1 = "湯";
		no2 = "植";
		no3 = "登";
		no4 = "等";
		no5 = "動";
		no6 = "童";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu8() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi28.jpg"));
		kara.setIcon(icon);

		no1 = "具";
		no2 = "苦";
		no3 = "区";
		no4 = "銀";
		no5 = "君";
		no6 = "係";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu9() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi29.jpg"));
		kara.setIcon(icon);

		no1 = "深";
		no2 = "進";
		no3 = "世";
		no4 = "整";
		no5 = "秒";
		no6 = "昔";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Hakkyu10() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi30.jpg"));
		kara.setIcon(icon);

		no1 = "全";
		no2 = "新";
		no3 = "相";
		no4 = "送";
		no5 = "想";
		no6 = "息";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ余裕？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu() {
		p1.setBackground(new Color(129, 247, 243));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi31.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "や";
		no2 = "も";
		no3 = "き";
		no4 = "そ";
		no5 = "ま";
		no6 = "か";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu2() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi32.jpg"));
		kara.setIcon(icon);

		no1 = "かお";
		no2 = "さお";
		no3 = "しお";
		no4 = "まお";
		no5 = "ざお";
		no6 = "うお";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu3() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi33.jpg"));
		kara.setIcon(icon);

		no1 = "ごご";
		no2 = "れご";
		no3 = "きご";
		no4 = "えご";
		no5 = "あご";
		no6 = "まご";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu4() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi34.jpg"));
		kara.setIcon(icon);

		no1 = "おい";
		no2 = "あい";
		no3 = "かい";
		no4 = "じい";
		no5 = "わい";
		no6 = "まい";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu5() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi35.jpg"));
		kara.setIcon(icon);

		no1 = "つ";
		no2 = "せ";
		no3 = "す";
		no4 = "め";
		no5 = "お";
		no6 = "し";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu6() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi36.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 書き");

		hint = "次の下線部の書きを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "察";
		no2 = "静";
		no3 = "参";
		no4 = "産";
		no5 = "散";
		no6 = "残";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu7() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi37.jpg"));
		kara.setIcon(icon);

		no1 = "緩";
		no2 = "塩";
		no3 = "岡";
		no4 = "億";
		no5 = "加";
		no6 = "英";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu8() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi38.jpg"));
		kara.setIcon(icon);

		no1 = "管";
		no2 = "関";
		no3 = "観";
		no4 = "願";
		no5 = "養";
		no6 = "岐";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu9() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi39.jpg"));
		kara.setIcon(icon);

		no1 = "埼";
		no2 = "材";
		no3 = "刷";
		no4 = "崎";
		no5 = "昨";
		no6 = "札";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Nanakyu10() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi40.jpg"));
		kara.setIcon(icon);

		no1 = "浅";
		no2 = "博";
		no3 = "戦";
		no4 = "選";
		no5 = "然";
		no6 = "争";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだいけそう？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu() {
		p1.setBackground(new Color(190, 129, 247));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi41.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "さくら";
		no2 = "あやめ";
		no3 = "はぎ";
		no4 = "やなぎ";
		no5 = "ぼたん";
		no6 = "ふじ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu2() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi42.jpg"));
		kara.setIcon(icon);

		no1 = "なく";
		no2 = "はく";
		no3 = "ぼく";
		no4 = "どく";
		no5 = "もく";
		no6 = "じく";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu3() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi43.jpg"));
		kara.setIcon(icon);

		no1 = "はめ";
		no2 = "ため";
		no3 = "さめ";
		no4 = "かめ";
		no5 = "まめ";
		no6 = "ゆめ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu4() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi44.jpg"));
		kara.setIcon(icon);

		no1 = "な";
		no2 = "め";
		no3 = "き";
		no4 = "さ";
		no5 = "は";
		no6 = "ぬ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu5() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi45.jpg"));
		kara.setIcon(icon);

		no1 = "かん";
		no2 = "じん";
		no3 = "そん";
		no4 = "まん";
		no5 = "けん";
		no6 = "さん";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu6() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi46.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 書き");

		hint = "次の下線部の書きを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "採";
		no2 = "潔";
		no3 = "際";
		no4 = "在";
		no5 = "財";
		no6 = "罪";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu7() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi47.jpg"));
		kara.setIcon(icon);

		no1 = "築";
		no2 = "断";
		no3 = "団";
		no4 = "態";
		no5 = "退";
		no6 = "貸";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu8() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi48.jpg"));
		kara.setIcon(icon);

		no1 = "妻";
		no2 = "採";
		no3 = "災";
		no4 = "際";
		no5 = "在";
		no6 = "財";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu9() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi49.jpg"));
		kara.setIcon(icon);

		no1 = "婦";
		no2 = "布";
		no3 = "貧";
		no4 = "備";
		no5 = "俵";
		no6 = "評";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Rokkyu10() {
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi50.jpg"));
		kara.setIcon(icon);

		no1 = "図";
		no2 = "測";
		no3 = "計";
		no4 = "諮";
		no5 = "量";
		no6 = "謀";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだまだ序の口";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Gokyu() {
		p1.setBackground(new Color(250, 172, 88));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi51.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "らん";
		no2 = "たいこ";
		no3 = "たまご";
		no4 = "さかな";
		no5 = "たかご";
		no6 = "たらこ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "小学校ラストの級";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Yonkyu()
	{
		p1.setBackground(new Color(250, 88, 130));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi61.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "けいと";
		no2 = "けがれ";
		no3 = "けむり";
		no4 = "けんか";
		no5 = "けまり";
		no6 = "けずり";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "まだ履歴書に書けないレベル";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Sankyu()
	{
		p1.setBackground(new Color(88, 250, 172));
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi71.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "けいてき";
		no2 = "けいしょう";
		no3 = "けいかん";
		no4 = "けいたい";
		no5 = "けいこう";
		no6 = "けいすう";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "やっと履歴書に書けるが効力は弱い";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Junnikyu()
	{
		p1.setBackground(new Color(250, 64, 0));
		systemmess.setForeground(Color.white);
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi81.jpg"));
		kara.setIcon(icon);

		systemmess.setText("準" + kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "ちょうしゅ";
		no2 = "がんしゅ";
		no3 = "じゅんしゅ";
		no4 = "しゅんしゅ";
		no5 = "こしゅ";
		no6 = "おんしゅ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "ここらへんで得意苦手分かれそう";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Nikyu()
	{
		p1.setBackground(new Color(46, 46, 254));
		systemmess.setForeground(Color.white);
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi91.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "くつぞふ";
		no2 = "くつだく";
		no3 = "くつぼく";
		no4 = "くつずみ";
		no5 = "くつじみ";
		no6 = "くつすみ";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Junikkyu()
	{
		p1.setBackground(new Color(180, 49, 4));
		systemmess.setForeground(Color.white);
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi101.jpg"));
		kara.setIcon(icon);

		systemmess.setText("準" + kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "じんぼく";
		no2 = "てんぼく";
		no3 = "かんぼく";
		no4 = "あんぼく";
		no5 = "ろんぼく";
		no6 = "がんぼく";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "無理しなくていいんだからね・・・？";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}
	
	public void Ikkyu()
	{
		bgm.close();
		bgm = new MyAudioPlayer("music/BF.mp3", true);
		
		p1.setBackground(new Color(8, 138, 8));
		systemmess.setForeground(Color.white);
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi111.jpg"));
		kara.setIcon(icon);

		systemmess.setText(kyu + "級 読み");

		hint = "次の下線部の読みを答えなさい";
		kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		kara2.setText(hint);

		no1 = "ユーゴスラビア";
		no2 = "ギリシャ";
		no3 = "スウェーデン";
		no4 = "ペルー";
		no5 = "ブラジル";
		no6 = "アルゼンチン";

		radio1.setText(no1);
		radio2.setText(no2);
		radio3.setText(no3);
		radio4.setText(no4);
		radio5.setText(no5);
		radio6.setText(no6);

		setsumei3 = "何でも漢字で書けちゃう時代";
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
		shintyu3.setForeground(Color.black);
		shintyu3.setText(setsumei3);

		pgc++;
	}

	public void Fuseikai() {
		voice = new MyAudioPlayer("music/bad.mp3", false);
		setsumei3 = "不正解・・・";
		shintyu3.setText(setsumei3);
		shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
		shintyu3.setForeground(Color.blue);
		hp--;
		shintyu2.setText(Integer.toString(hp));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		boolean[] kyutus=new boolean[12];
		
		for (int i = 0; i < 12; i++) {
			kyutus[i]=kyubutton[i].isSelected();
		}
		
		if(cmd.equals("決定"))
		{
			bgm.close();
			bgm = new MyAudioPlayer("music/backmusic.mp3", true);
			clay.last(dodai);
			mainq="";
			kara.setText(mainq);
			
			setsumei = "HP";
			shintyu.setText(setsumei);

			setsumei2 = "";
			shintyu2.setText(Integer.toString(hp));
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei3 = "判定";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			Jukyu();
			
			if(kyutus[0])
			{
				hantei=0;
				kirikae=1;
				pgc=0;
				kyu=10;
				Jukyu();
			}
			if (kyutus[1]) 
			{
				hantei=1;
				kirikae=11;
				pgc=10;
				kyu=9;
				Kyukyu();
			}
			if (kyutus[2]) 
			{
				hantei=2;
				kirikae=21;
				pgc=20;
				kyu=8;
				Hakkyu();
			}
			if (kyutus[3]) 
			{
				hantei=3;
				kyu=7;
				kirikae=31;
				pgc=30;
				Nanakyu();
			}
			if (kyutus[4]) 
			{
				hantei=4;
				kyu=6;
				kirikae=41;
				pgc=40;
				Rokkyu();
			}
			if (kyutus[5]) 
			{
				hantei=5;
				kyu=5;
				kirikae=51;
				pgc=50;
				Gokyu();
			}
			if (kyutus[6]) 
			{
				hantei=6;
				kyu=4;
				kirikae=61;
				pgc=60;
				Yonkyu();
			}
			if (kyutus[7]) 
			{
				hantei=7;
				kyu=3;
				kirikae=71;
				pgc=70;
				Sankyu();
			}
			if (kyutus[8]) 
			{
				hantei=8;
				kyu=2;
				kirikae=81;
				pgc=80;
				Junnikyu();
			}
			if (kyutus[9]) 
			{
				hantei=9;
				kyu=2;
				kirikae=91;
				pgc=90;
				Nikyu();
			}
			if (kyutus[10]) 
			{
				hantei=10;
				kyu=1;
				kirikae=101;
				pgc=100;
				Junikkyu();
			}
			if (kyutus[11]) 
			{
				hantei=11;
				kyu=1;
				kirikae=111;
				pgc=110;
				Ikkyu();
			}
		}
		
		//メインパネル↓
		boolean status1 = radio1.isSelected();
		boolean status2 = radio2.isSelected();
		boolean status3 = radio3.isSelected();
		boolean status4 = radio4.isSelected();
		boolean status5 = radio5.isSelected();
		boolean status6 = radio6.isSelected();

		if (cmd.equals("次へ") && pgc == 0 && kirikae == 1) {
			Jukyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 1 && kirikae == 1) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 1 && kirikae == 1) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 1 && kirikae == 1) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 1 && kirikae == 1) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 1 && kirikae == 1) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 1 && kirikae == 1) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 2 && pgc == 1) {
			Jukyu2();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 2 && kirikae == 2) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 2 && kirikae == 2) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 2 && kirikae == 2) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 2 && kirikae == 2) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 2 && kirikae == 2) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 2 && kirikae == 2) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 3 && pgc == 2) {
			Jukyu3();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 3 && kirikae == 3) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 3 && kirikae == 3) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 3 && kirikae == 3) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 3 && kirikae == 3) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 3 && kirikae == 3) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 3 && kirikae == 3) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 4 && pgc == 3) {
			Jukyu4();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 4 && kirikae == 4) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 4 && kirikae == 4) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 4 && kirikae == 4) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 4 && kirikae == 4) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 4 && kirikae == 4) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 4 && kirikae == 4) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 5 && pgc == 4) {
			Jukyu5();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 5 && kirikae == 5) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 5 && kirikae == 5) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 5 && kirikae == 5) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 5 && kirikae == 5) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 5 && kirikae == 5) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 5 && kirikae == 5) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 6 && pgc == 5) {
			Jukyu6();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 6 && kirikae == 6) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 6 && kirikae == 6) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 6 && kirikae == 6) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 6 && kirikae == 6) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 6 && kirikae == 6) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 6 && kirikae == 6) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 7 && pgc == 6) {
			Jukyu7();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 7 && kirikae == 7) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 7 && kirikae == 7) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 7 && kirikae == 7) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 7 && kirikae == 7) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 7 && kirikae == 7) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 7 && kirikae == 7) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 8 && pgc == 7) {
			Jukyu8();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 8 && kirikae == 8) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 8 && kirikae == 8) {

			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 8 && kirikae == 8) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 8 && kirikae == 8) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 8 && kirikae == 8) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 8 && kirikae == 8) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 9 && pgc == 8) {
			Jukyu9();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 9 && kirikae == 9) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 9 && kirikae == 9) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 9 && kirikae == 9) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 9 && kirikae == 9) {
			setsumei3 = "正解！ 次へいこう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 9 && kirikae == 9) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 9 && kirikae == 9) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 10 && pgc == 9) {
			Jukyu10();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 10 && kirikae == 10) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 10 && kirikae == 10) {
			setsumei3 = "正解！ １０級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 10 && kirikae == 10) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 10 && kirikae == 10) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 10 && kirikae == 10) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 10 && kirikae == 10) {
			Fuseikai();
		}
		//10級終了
		if (cmd.equals("次へ") && kirikae == 11 && pgc == 10) {
			Kyukyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 11 && kirikae == 11) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 11 && kirikae == 11) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 11 && kirikae == 11) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 11 && kirikae == 11) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 11 && kirikae == 11) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 11 && kirikae == 11) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 12 && pgc == 11) {
			Kyukyu2();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 12 && kirikae == 12) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 12 && kirikae == 12) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 12 && kirikae == 12) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 12 && kirikae == 12) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 12 && kirikae == 12) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 12 && kirikae == 12) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 13 && pgc == 12) {
			Kyukyu3();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 13 && kirikae == 13) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 13 && kirikae == 13) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 13 && kirikae == 13) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 13 && kirikae == 13) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 13 && kirikae == 13) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 13 && kirikae == 13) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 14 && pgc == 13) {
			Kyukyu4();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 14 && kirikae == 14) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 14 && kirikae == 14) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 14 && kirikae == 14) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 14 && kirikae == 14) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 14 && kirikae == 14) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 14 && kirikae == 14) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 15 && pgc == 14) {
			Kyukyu5();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 15 && kirikae == 15) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 15 && kirikae == 15) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 15 && kirikae == 15) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 15 && kirikae == 15) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 15 && kirikae == 15) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 15 && kirikae == 15) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 16 && pgc == 15) {
			Kyukyu6();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 16 && kirikae == 16) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 16 && kirikae == 16) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 16 && kirikae == 16) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 16 && kirikae == 16) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 16 && kirikae == 16) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 16 && kirikae == 16) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 17 && pgc == 16) {
			Kyukyu7();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 17 && kirikae == 17) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 17 && kirikae == 17) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 17 && kirikae == 17) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 17 && kirikae == 17) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 17 && kirikae == 17) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 17 && kirikae == 17) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 18 && pgc == 17) {
			Kyukyu8();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 18 && kirikae == 18) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 18 && kirikae == 18) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 18 && kirikae == 18) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 18 && kirikae == 18) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 18 && kirikae == 18) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 18 && kirikae == 18) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 19 && pgc == 18) {
			Kyukyu9();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 19 && kirikae == 19) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 19 && kirikae == 19) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 19 && kirikae == 19) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 19 && kirikae == 19) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 19 && kirikae == 19) {
			setsumei3 = "正解！調子いいね！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 19 && kirikae == 19) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 20 && pgc == 19) {
			Kyukyu10();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 20 && kirikae == 20) {
			setsumei3 = "正解！9級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 20 && kirikae == 20) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 20 && kirikae == 20) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 20 && kirikae == 20) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 20 && kirikae == 20) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 20 && kirikae == 20) {
			Fuseikai();
		}
		//9級終了

		if (cmd.equals("次へ") && kirikae == 21 && pgc == 20) {
			Hakkyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 21 && kirikae == 21) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 21 && kirikae == 21) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 21 && kirikae == 21) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 21 && kirikae == 21) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 21 && kirikae == 21) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 21 && kirikae == 21) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 22 && pgc == 21) {
			Hakkyu2();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 22 && kirikae == 22) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 22 && kirikae == 22) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 22 && kirikae == 22) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 22 && kirikae == 22) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 22 && kirikae == 22) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 22 && kirikae == 22) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 23 && pgc == 22) {
			Hakkyu3();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 23 && kirikae == 23) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 23 && kirikae == 23) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 23 && kirikae == 23) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 23 && kirikae == 23) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 23 && kirikae == 23) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 23 && kirikae == 23) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 24 && pgc == 23) {
			Hakkyu4();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 24 && kirikae == 24) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 24 && kirikae == 24) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 24 && kirikae == 24) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 24 && kirikae == 24) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 24 && kirikae == 24) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 24 && kirikae == 24) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 25 && pgc == 24) {
			Hakkyu5();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 25 && kirikae == 25) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 25 && kirikae == 25) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 25 && kirikae == 25) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 25 && kirikae == 25) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 25 && kirikae == 25) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 25 && kirikae == 25) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 26 && pgc == 25) {
			Hakkyu6();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 26 && kirikae == 26) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 26 && kirikae == 26) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 26 && kirikae == 26) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 26 && kirikae == 26) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 26 && kirikae == 26) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 26 && kirikae == 26) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 27 && pgc == 26) {
			Hakkyu7();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 27 && kirikae == 27) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 27 && kirikae == 27) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 27 && kirikae == 27) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 27 && kirikae == 27) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 27 && kirikae == 27) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 27 && kirikae == 27) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 28 && pgc == 27) {
			Hakkyu8();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 28 && kirikae == 28) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 28 && kirikae == 28) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 28 && kirikae == 28) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 28 && kirikae == 28) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 28 && kirikae == 28) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 28 && kirikae == 28) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 29 && pgc == 28) {
			Hakkyu9();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 29 && kirikae == 29) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 29 && kirikae == 29) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 29 && kirikae == 29) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 29 && kirikae == 29) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 29 && kirikae == 29) {
			setsumei3 = "正解！朝飯前？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 29 && kirikae == 29) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 30 && pgc == 29) {
			Hakkyu10();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 30 && kirikae == 30) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 30 && kirikae == 30) {
			setsumei3 = "正解！8級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 30 && kirikae == 30) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 30 && kirikae == 30) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 30 && kirikae == 30) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 30 && kirikae == 30) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 31 && pgc == 30) {
			Nanakyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 31 && kirikae == 31) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 31 && kirikae == 31) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 31 && kirikae == 31) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 31 && kirikae == 31) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 31 && kirikae == 31) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 31 && kirikae == 31) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 32 && pgc == 31) {
			Nanakyu2();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 32 && kirikae == 32) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 32 && kirikae == 32) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 32 && kirikae == 32) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 32 && kirikae == 32) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 32 && kirikae == 32) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 32 && kirikae == 32) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 33 && pgc == 32) {
			Nanakyu3();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 33 && kirikae == 33) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 33 && kirikae == 33) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 33 && kirikae == 33) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 33 && kirikae == 33) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 33 && kirikae == 33) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 33 && kirikae == 33) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 34 && pgc == 33) {
			Nanakyu4();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 34 && kirikae == 34) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 34 && kirikae == 34) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 34 && kirikae == 34) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 34 && kirikae == 34) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 34 && kirikae == 34) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 34 && kirikae == 34) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 35 && pgc == 34) {
			Nanakyu5();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 35 && kirikae == 35) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 35 && kirikae == 35) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 35 && kirikae == 35) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 35 && kirikae == 35) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 35 && kirikae == 35) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 35 && kirikae == 35) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 36 && pgc == 35) {
			Nanakyu6();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 36 && kirikae == 36) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 36 && kirikae == 36) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 36 && kirikae == 36) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 36 && kirikae == 36) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 36 && kirikae == 36) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 36 && kirikae == 36) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 37 && pgc == 36) {
			Nanakyu7();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 37 && kirikae == 37) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 37 && kirikae == 37) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 37 && kirikae == 37) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 37 && kirikae == 37) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 37 && kirikae == 37) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 37 && kirikae == 37) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 38 && pgc == 37) {
			Nanakyu8();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 38 && kirikae == 38) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 38 && kirikae == 38) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 38 && kirikae == 38) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 38 && kirikae == 38) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 38 && kirikae == 38) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 38 && kirikae == 38) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 39 && pgc == 38) {
			Nanakyu9();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 39 && kirikae == 39) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 39 && kirikae == 39) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 39 && kirikae == 39) {
			setsumei3 = "正解！まだまだこれから？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 39 && kirikae == 39) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 39 && kirikae == 39) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 39 && kirikae == 39) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 40 && pgc == 39) {
			Nanakyu10();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 40 && kirikae == 40) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 40 && kirikae == 40) {
			setsumei3 = "正解！7級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 40 && kirikae == 40) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 40 && kirikae == 40) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 40 && kirikae == 40) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 40 && kirikae == 40) {
			Fuseikai();
		}
		//7級終了

		if (cmd.equals("次へ") && kirikae == 41 && pgc == 40) {
			Rokkyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 41 && kirikae == 41) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 41 && kirikae == 41) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 41 && kirikae == 41) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 41 && kirikae == 41) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 41 && kirikae == 41) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 41 && kirikae == 41) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 42 && pgc == 41) {
			Rokkyu2();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 42 && kirikae == 42) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 42 && kirikae == 42) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 42 && kirikae == 42) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 42 && kirikae == 42) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 42 && kirikae == 42) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 42 && kirikae == 42) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 43 && pgc == 42) {
			Rokkyu3();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 43 && kirikae == 43) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 43 && kirikae == 43) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 43 && kirikae == 43) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 43 && kirikae == 43) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 43 && kirikae == 43) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 43 && kirikae == 43) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 44 && pgc == 43) {
			Rokkyu4();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 44 && kirikae == 44) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 44 && kirikae == 44) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 44 && kirikae == 44) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 44 && kirikae == 44) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 44 && kirikae == 44) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 44 && kirikae == 44) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 45 && pgc == 44) {
			Rokkyu5();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 45 && kirikae == 45) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 45 && kirikae == 45) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 45 && kirikae == 45) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 45 && kirikae == 45) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 45 && kirikae == 45) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 45 && kirikae == 45) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 46 && pgc == 45) {
			Rokkyu6();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 46 && kirikae == 46) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 46 && kirikae == 46) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 46 && kirikae == 46) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 46 && kirikae == 46) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 46 && kirikae == 46) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 46 && kirikae == 46) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 47 && pgc == 46) {
			Rokkyu7();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 47 && kirikae == 47) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 47 && kirikae == 47) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 47 && kirikae == 47) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 47 && kirikae == 47) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 47 && kirikae == 47) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 47 && kirikae == 47) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 48 && pgc == 47) {
			Rokkyu8();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 48 && kirikae == 48) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 48 && kirikae == 48) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 48 && kirikae == 48) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 48 && kirikae == 48) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 48 && kirikae == 48) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 48 && kirikae == 48) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 49 && pgc == 48) {
			Rokkyu9();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 49 && kirikae == 49) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 49 && kirikae == 49) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 49 && kirikae == 49) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 49 && kirikae == 49) {
			setsumei3 = "正解！その調子！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 49 && kirikae == 49) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 49 && kirikae == 49) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 50 && pgc == 49) {
			Rokkyu10();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 50 && kirikae == 50) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 50 && kirikae == 50) {
			setsumei3 = "正解！6級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 50 && kirikae == 50) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 50 && kirikae == 50) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 50 && kirikae == 50) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 50 && kirikae == 50) {
			Fuseikai();
		}
		//6級終わり
		if (cmd.equals("次へ") && kirikae == 51 && pgc == 50) {
			Gokyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 51 && kirikae == 51) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 51 && kirikae == 51) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 51 && kirikae == 51) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 51 && kirikae == 51) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 51 && kirikae == 51) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 51 && kirikae == 51) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 52 && pgc == 51) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi52.jpg"));
			kara.setIcon(icon);

			no1 = "たから";
			no2 = "おから";
			no3 = "はから";
			no4 = "てから";
			no5 = "すから";
			no6 = "わから";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 52 && kirikae == 52) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 52 && kirikae == 52) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 52 && kirikae == 52) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 52 && kirikae == 52) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 52 && kirikae == 52) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 52 && kirikae == 52) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 53 && pgc == 52) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi53.jpg"));
			kara.setIcon(icon);

			no1 = "まり";
			no2 = "しり";
			no3 = "もり";
			no4 = "ゆり";
			no5 = "はり";
			no6 = "せり";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 53 && kirikae == 53) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 53 && kirikae == 53) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 53 && kirikae == 53) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 53 && kirikae == 53) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 53 && kirikae == 53) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 53 && kirikae == 53) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 54 && pgc == 53) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi54.jpg"));
			kara.setIcon(icon);

			no1 = "まいこ";
			no2 = "かいこ";
			no3 = "さいこ";
			no4 = "らいこ";
			no5 = "えいこ";
			no6 = "ずいこ";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 54 && kirikae == 54) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 54 && kirikae == 54) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 54 && kirikae == 54) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 54 && kirikae == 54) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 54 && kirikae == 54) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 54 && kirikae == 54) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 55 && pgc == 54) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi55.jpg"));
			kara.setIcon(icon);

			no1 = "ま";
			no2 = "こ";
			no3 = "と";
			no4 = "い";
			no5 = "も";
			no6 = "で";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 55 && kirikae == 55) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 55 && kirikae == 55) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 55 && kirikae == 55) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 55 && kirikae == 55) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 55 && kirikae == 55) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 55 && kirikae == 55) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 56 && pgc == 55) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi56.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "権";
			no2 = "憲";
			no3 = "源";
			no4 = "密";
			no5 = "厳";
			no6 = "己";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 56 && kirikae == 56) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 56 && kirikae == 56) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 56 && kirikae == 56) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 56 && kirikae == 56) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 56 && kirikae == 56) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 56 && kirikae == 56) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 57 && pgc == 56) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi57.jpg"));
			kara.setIcon(icon);

			no1 = "補";
			no2 = "秘";
			no3 = "俵";
			no4 = "腹";
			no5 = "奮";
			no6 = "並";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 57 && kirikae == 57) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 57 && kirikae == 57) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 57 && kirikae == 57) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 57 && kirikae == 57) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 57 && kirikae == 57) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 57 && kirikae == 57) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 58 && pgc == 57) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi58.jpg"));
			kara.setIcon(icon);

			no1 = "宅";
			no2 = "担";
			no3 = "尊";
			no4 = "探";
			no5 = "誕";
			no6 = "段";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 58 && kirikae == 58) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 58 && kirikae == 58) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 58 && kirikae == 58) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 58 && kirikae == 58) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 58 && kirikae == 58) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 58 && kirikae == 58) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 59 && pgc == 58) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi59.jpg"));
			kara.setIcon(icon);

			no1 = "郵";
			no2 = "優";
			no3 = "預";
			no4 = "幼";
			no5 = "欲";
			no6 = "従";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 59 && kirikae == 59) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 59 && kirikae == 59) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 59 && kirikae == 59) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 59 && kirikae == 59) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 59 && kirikae == 59) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 59 && kirikae == 59) {
			setsumei3 = "正解！まだ先は長い・・・";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 60 && pgc == 59) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi60.jpg"));
			kara.setIcon(icon);

			no1 = "糖";
			no2 = "届";
			no3 = "難";
			no4 = "著";
			no5 = "乳";
			no6 = "認";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "小学校ラストの級";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 60 && kirikae == 60) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 60 && kirikae == 60) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 60 && kirikae == 60) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 60 && kirikae == 60) {
			setsumei3 = "おめでとう！小学校卒業だ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 40));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 60 && kirikae == 60) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 60 && kirikae == 60) {
			Fuseikai();
		}
		//5級終了

		if (cmd.equals("次へ") && kirikae == 61 && pgc == 60) {
			Yonkyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 61 && kirikae == 61) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 61 && kirikae == 61) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 61 && kirikae == 61) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 61 && kirikae == 61) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 61 && kirikae == 61) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 61 && kirikae == 61) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 62 && pgc == 61) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi62.jpg"));
			kara.setIcon(icon);

			no1 = "まけ";
			no2 = "まき";
			no3 = "まふ";
			no4 = "ます";
			no5 = "まぶ";
			no6 = "まず";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 62 && kirikae == 62) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 62 && kirikae == 62) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 62 && kirikae == 62) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 62 && kirikae == 62) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 62 && kirikae == 62) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 62 && kirikae == 62) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 63 && pgc == 62) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi63.jpg"));
			kara.setIcon(icon);

			no1 = "きぬ";
			no2 = "きし";
			no3 = "きふ";
			no4 = "きる";
			no5 = "きり";
			no6 = "きい";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 63 && kirikae == 63) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 63 && kirikae == 63) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 63 && kirikae == 63) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 63 && kirikae == 63) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 63 && kirikae == 63) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 63 && kirikae == 63) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 64 && pgc == 63) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi64.jpg"));
			kara.setIcon(icon);

			no1 = "しきさい";
			no2 = "しきさき";
			no3 = "しきさめ";
			no4 = "しきさじ";
			no5 = "しきさく";
			no6 = "しきさえ";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 64 && kirikae == 64) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 64 && kirikae == 64) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 64 && kirikae == 64) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 64 && kirikae == 64) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 64 && kirikae == 64) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 64 && kirikae == 64) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 65 && pgc == 64) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi65.jpg"));
			kara.setIcon(icon);

			no1 = "でんしょう";
			no2 = "きんしょう";
			no3 = "かんしょう";
			no4 = "もんしょう";
			no5 = "さんしょう";
			no6 = "ろんしょう";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 65 && kirikae == 65) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 65 && kirikae == 65) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 65 && kirikae == 65) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 65 && kirikae == 65) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 65 && kirikae == 65) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 65 && kirikae == 65) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 66 && pgc == 65) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi66.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "鮮";
			no2 = "罰";
			no3 = "般";
			no4 = "販";
			no5 = "搬";
			no6 = "範";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 66 && kirikae == 66) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 66 && kirikae == 66) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 66 && kirikae == 66) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 66 && kirikae == 66) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 66 && kirikae == 66) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 66 && kirikae == 66) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 67 && pgc == 66) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi67.jpg"));
			kara.setIcon(icon);

			no1 = "膚";
			no2 = "賦";
			no3 = "舞";
			no4 = "濁";
			no5 = "幅";
			no6 = "払";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 67 && kirikae == 67) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 67 && kirikae == 67) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 67 && kirikae == 67) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 67 && kirikae == 67) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 67 && kirikae == 67) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 67 && kirikae == 67) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 68 && pgc == 67) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi68.jpg"));
			kara.setIcon(icon);

			no1 = "透";
			no2 = "桃";
			no3 = "唐";
			no4 = "倒";
			no5 = "逃";
			no6 = "蓄";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 68 && kirikae == 68) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 68 && kirikae == 68) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 68 && kirikae == 68) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 68 && kirikae == 68) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 68 && kirikae == 68) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 68 && kirikae == 68) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 69 && pgc == 68) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi69.jpg"));
			kara.setIcon(icon);

			no1 = "療";
			no2 = "殿";
			no3 = "隣";
			no4 = "涙";
			no5 = "隷";
			no6 = "齢";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 69 && kirikae == 69) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 69 && kirikae == 69) {
			setsumei3 = "正解！手応え感じてきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 69 && kirikae == 69) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 69 && kirikae == 69) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 69 && kirikae == 69) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 69 && kirikae == 69) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 70 && pgc == 69) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi70.jpg"));
			kara.setIcon(icon);

			no1 = "人文";
			no2 = "腎門";
			no3 = "尋問";
			no4 = "陣門";
			no5 = "神門";
			no6 = "仁悶";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まだ履歴書に書けないレベル";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 70 && kirikae == 70) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 70 && kirikae == 70) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 70 && kirikae == 70) {
			setsumei3 = "正解！4級クリア！！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 70 && kirikae == 70) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 70 && kirikae == 70) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 70 && kirikae == 70) {
			Fuseikai();
		}
		//4級終了
		if (cmd.equals("次へ") && kirikae == 71 && pgc == 70) {
			Sankyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 71 && kirikae == 71) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 71 && kirikae == 71) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 71 && kirikae == 71) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 71 && kirikae == 71) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 71 && kirikae == 71) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 71 && kirikae == 71) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 72 && pgc == 71) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi72.jpg"));
			kara.setIcon(icon);

			no1 = "ちけん";
			no2 = "ちこう";
			no3 = "ちえん";
			no4 = "ちたい";
			no5 = "ちかん";
			no6 = "ちくま";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 72 && kirikae == 72) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 72 && kirikae == 72) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 72 && kirikae == 72) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 72 && kirikae == 72) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 72 && kirikae == 72) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 72 && kirikae == 72) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 73 && pgc == 72) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi73.jpg"));
			kara.setIcon(icon);

			no1 = "ほうじゅつ";
			no2 = "ほうじゅん";
			no3 = "ほうけん";
			no4 = "ほうかつ";
			no5 = "ほうもん";
			no6 = "ほうぶん";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 73 && kirikae == 73) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 73 && kirikae == 73) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 73 && kirikae == 73) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 73 && kirikae == 73) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 73 && kirikae == 73) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 73 && kirikae == 73) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 74 && pgc == 73) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi74.jpg"));
			kara.setIcon(icon);

			no1 = "たくいつ";
			no2 = "たくあん";
			no3 = "たくぼく";
			no4 = "たくさん";
			no5 = "たくばつ";
			no6 = "たくえつ";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 74 && kirikae == 74) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 74 && kirikae == 74) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 74 && kirikae == 74) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 74 && kirikae == 74) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 74 && kirikae == 74) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 74 && kirikae == 74) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 75 && pgc == 74) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi75.jpg"));
			kara.setIcon(icon);

			no1 = "きょせい";
			no2 = "れっせい";
			no3 = "いんせい";
			no4 = "かんせい";
			no5 = "じんせい";
			no6 = "さんせい";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 75 && kirikae == 75) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 75 && kirikae == 75) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 75 && kirikae == 75) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 75 && kirikae == 75) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 75 && kirikae == 75) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 75 && kirikae == 75) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 76 && pgc == 75) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi76.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "欄";
			no2 = "覧";
			no3 = "濫";
			no4 = "爛";
			no5 = "乱";
			no6 = "藍";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 76 && kirikae == 76) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 76 && kirikae == 76) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 76 && kirikae == 76) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 76 && kirikae == 76) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 76 && kirikae == 76) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 76 && kirikae == 76) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 77 && pgc == 76) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi77.jpg"));
			kara.setIcon(icon);

			no1 = "尉";
			no2 = "緯";
			no3 = "囲";
			no4 = "鋳";
			no5 = "維";
			no6 = "遺";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 77 && kirikae == 77) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 77 && kirikae == 77) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 77 && kirikae == 77) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 77 && kirikae == 77) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 77 && kirikae == 77) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 77 && kirikae == 77) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 78 && pgc == 77) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi78.jpg"));
			kara.setIcon(icon);

			no1 = "隻";
			no2 = "籍";
			no3 = "席";
			no4 = "関";
			no5 = "責";
			no6 = "積";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 78 && kirikae == 78) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 78 && kirikae == 78) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 78 && kirikae == 78) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 78 && kirikae == 78) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 78 && kirikae == 78) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 78 && kirikae == 78) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 79 && pgc == 78) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi79.jpg"));
			kara.setIcon(icon);

			no1 = "装";
			no2 = "躁";
			no3 = "送";
			no4 = "宗";
			no5 = "総";
			no6 = "葬";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 79 && kirikae == 79) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 79 && kirikae == 79) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 79 && kirikae == 79) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 79 && kirikae == 79) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 79 && kirikae == 79) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 79 && kirikae == 79) {
			setsumei3 = "正解！手強くなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 80 && pgc == 79) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi80.jpg"));
			kara.setIcon(icon);

			no1 = "陳";
			no2 = "鎮";
			no3 = "珍";
			no4 = "朕";
			no5 = "賃";
			no6 = "枕";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "やっと履歴書に書けるが効力は弱い";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 80 && kirikae == 80) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 80 && kirikae == 80) {
			setsumei3 = "正解！中学卒業おめでとう！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 80 && kirikae == 80) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 80 && kirikae == 80) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 80 && kirikae == 80) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 80 && kirikae == 80) {
			Fuseikai();
		}
		//3級終了

		if (cmd.equals("次へ") && kirikae == 81 && pgc == 80)
		{
			Junnikyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 81 && kirikae == 81) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 81 && kirikae == 81) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 81 && kirikae == 81) {
			setsumei3 = "正解！ここから本番！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 81 && kirikae == 81) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 81 && kirikae == 81) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 81 && kirikae == 81) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 82 && pgc == 81) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi82.jpg"));
			kara.setIcon(icon);

			no1 = "えんらく";
			no2 = "ゆうらく";
			no3 = "ほうらく";
			no4 = "てんらく";
			no5 = "きょうらく";
			no6 = "かんらく";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "ここらへんで得意苦手分かれそう";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 82 && kirikae == 82) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 82 && kirikae == 82) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 82 && kirikae == 82) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 82 && kirikae == 82) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 82 && kirikae == 82) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 82 && kirikae == 82) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 83 && pgc == 82) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi83.jpg"));
			kara.setIcon(icon);

			no1 = "かぶん";
			no2 = "かもん";
			no3 = "かびん";
			no4 = "かじん";
			no5 = "かえん";
			no6 = "かいん";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "ここらへんで得意苦手分かれそう";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 83 && kirikae == 83) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 83 && kirikae == 83) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 83 && kirikae == 83) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 83 && kirikae == 83) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 83 && kirikae == 83) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 83 && kirikae == 83) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 84 && pgc == 83) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi84.jpg"));
			kara.setIcon(icon);

			no1 = "か";
			no2 = "き";
			no3 = "ゆ";
			no4 = "う";
			no5 = "も";
			no6 = "い";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "ここらへんで得意苦手分かれそう";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 84 && kirikae == 84) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 84 && kirikae == 84) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 84 && kirikae == 84) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 84 && kirikae == 84) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 84 && kirikae == 84) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 84 && kirikae == 84) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 85 && pgc == 84) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi85.jpg"));
			kara.setIcon(icon);

			no1 = "うんりょう";
			no2 = "ゆうりょう";
			no3 = "しゃくりょう";
			no4 = "せんりょう";
			no5 = "おんりょう";
			no6 = "かんりょう";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "ここらへんで得意苦手分かれそう";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 85 && kirikae == 85) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 85 && kirikae == 85) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 85 && kirikae == 85) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 85 && kirikae == 85) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 85 && kirikae == 85) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 85 && kirikae == 85) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 86 && pgc == 85) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi86.jpg"));
			kara.setIcon(icon);

			systemmess.setText("準" + kyu + "級 四字熟語");

			hint = "次の□に当てはまる漢字を答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "聞";
			no2 = "蘭";
			no3 = "議";
			no4 = "歴";
			no5 = "罰";
			no6 = "達";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まさか読み書きだけだと思ってた？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 86 && kirikae == 86) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 86 && kirikae == 86) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 86 && kirikae == 86) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 86 && kirikae == 86) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 86 && kirikae == 86) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 86 && kirikae == 86) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 87 && pgc == 86) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi87.jpg"));
			kara.setIcon(icon);

			no1 = "歴";
			no2 = "訪";
			no3 = "光";
			no4 = "門";
			no5 = "園";
			no6 = "鼓";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まさか読み書きだけだと思ってた？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 87 && kirikae == 87) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 87 && kirikae == 87) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 87 && kirikae == 87) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 87 && kirikae == 87) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 87 && kirikae == 87) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 87 && kirikae == 87) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 88 && pgc == 87) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi88.jpg"));
			kara.setIcon(icon);

			no1 = "牛";
			no2 = "犬";
			no3 = "猿";
			no4 = "羊";
			no5 = "馬";
			no6 = "鳥";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まさか読み書きだけだと思ってた？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 88 && kirikae == 88) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 88 && kirikae == 88) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 88 && kirikae == 88) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 88 && kirikae == 88) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 88 && kirikae == 88) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 88 && kirikae == 88) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 89 && pgc == 88) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi89.jpg"));
			kara.setIcon(icon);

			no1 = "考";
			no2 = "慮";
			no3 = "雄";
			no4 = "風";
			no5 = "温";
			no6 = "碗";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まさか読み書きだけだと思ってた？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 89 && kirikae == 89) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 89 && kirikae == 89) {
			setsumei3 = "正解！まだいける？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 89 && kirikae == 89) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 89 && kirikae == 89) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 89 && kirikae == 89) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 89 && kirikae == 89) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 90 && pgc == 89) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi90.jpg"));
			kara.setIcon(icon);

			no1 = "皇";
			no2 = "退";
			no3 = "忌";
			no4 = "慈";
			no5 = "個";
			no6 = "是";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "まさか読み書きだけだと思ってた？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 90 && kirikae == 90) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 90 && kirikae == 90) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 90 && kirikae == 90) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 90 && kirikae == 90) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 90 && kirikae == 90) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 90 && kirikae == 90) {
			setsumei3 = "正解！準2級クリア！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
			hantei++;
		}
		//準2級終了

		if (cmd.equals("次へ") && kirikae == 91 && pgc == 90) {
			Nikyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 91 && kirikae == 91) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 91 && kirikae == 91) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 91 && kirikae == 91) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 91 && kirikae == 91) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status5 == true && pgc == 91 && kirikae == 91) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 91 && kirikae == 91) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 92 && pgc == 91) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi92.jpg"));
			kara.setIcon(icon);

			no1 = "ていぞう";
			no2 = "かんぞう";
			no3 = "じゅうぞう";
			no4 = "さんぞう";
			no5 = "しゅうぞう";
			no6 = "あいぞう";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 92 && kirikae == 92) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 92 && kirikae == 92) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 92 && kirikae == 92) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 92 && kirikae == 92) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 92 && kirikae == 92) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 92 && kirikae == 92) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 93 && pgc == 92) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi93.jpg"));
			kara.setIcon(icon);

			no1 = "あいじ";
			no2 = "おもむ";
			no3 = "ねんご";
			no4 = "いどこ";
			no5 = "あおみど";
			no6 = "あんずい";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 93 && kirikae == 93) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 93 && kirikae == 93) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 93 && kirikae == 93) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 93 && kirikae == 93) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 93 && kirikae == 93) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 93 && kirikae == 93) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 94 && pgc == 93) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi94.jpg"));
			kara.setIcon(icon);

			no1 = "おい";
			no2 = "いまし";
			no3 = "き";
			no4 = "いじ";
			no5 = "はずかし";
			no6 = "あか";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 94 && kirikae == 94) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 94 && kirikae == 94) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 94 && kirikae == 94) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 94 && kirikae == 94) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 94 && kirikae == 94) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 94 && kirikae == 94) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 95 && pgc == 94) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi95.jpg"));
			kara.setIcon(icon);

			no1 = "そそのか";
			no2 = "あやか";
			no3 = "ため";
			no4 = "おく";
			no5 = "あきか";
			no6 = "あけわた";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 95 && kirikae == 95) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 95 && kirikae == 95) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 95 && kirikae == 95) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 95 && kirikae == 95) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 95 && kirikae == 95) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 95 && kirikae == 95) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 96 && pgc == 95) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi96.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "遺漏";
			no2 = "慰労";
			no3 = "煎郎";
			no4 = "遺楼";
			no5 = "威浪";
			no6 = "意牢";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 96 && kirikae == 96) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 96 && kirikae == 96) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 96 && kirikae == 96) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 96 && kirikae == 96) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 96 && kirikae == 96) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 96 && kirikae == 96) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 97 && pgc == 96) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi97.jpg"));
			kara.setIcon(icon);

			no1 = "教反";
			no2 = "興叛";
			no3 = "経翻";
			no4 = "教本";
			no5 = "狂奔";
			no6 = "凶奔";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 97 && kirikae == 97) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 97 && kirikae == 97) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 97 && kirikae == 97) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 97 && kirikae == 97) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 97 && kirikae == 97) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 97 && kirikae == 97) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 98 && pgc == 97) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi98.jpg"));
			kara.setIcon(icon);

			no1 = "瞬結";
			no2 = "俊傑";
			no3 = "駿傑";
			no4 = "瞬結";
			no5 = "竣訣";
			no6 = "駿桔";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "この級クリアできれば社会でも恥ずかしくない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 98 && kirikae == 98) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 98 && kirikae == 98) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 98 && kirikae == 98) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 98 && kirikae == 98) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 98 && kirikae == 98) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 98 && kirikae == 98) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 99 && pgc == 98) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi99.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 四字熟語");

			hint = "次の□に当てはまる漢字を答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "賞嘆";
			no2 = "熊胆";
			no3 = "自若";
			no4 = "奇矯";
			no5 = "三道";
			no6 = "傑作";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "帰ってきた四字熟語";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 99 && kirikae == 99) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 99 && kirikae == 99) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 99 && kirikae == 99) {
			setsumei3 = "正解！しんどくなってきた？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 99 && kirikae == 99) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 99 && kirikae == 99) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 99 && kirikae == 99) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 100 && pgc == 99) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi100.jpg"));
			kara.setIcon(icon);

			no1 = "波蘭";
			no2 = "瘢痕";
			no3 = "諸突";
			no4 = "冬雷";
			no5 = "襲名";
			no6 = "明鏡";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "帰ってきた四字熟語";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 100 && kirikae == 100) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 100 && kirikae == 100) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 100 && kirikae == 100) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 100 && kirikae == 100) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 100 && kirikae == 100) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 100 && kirikae == 100) {
			setsumei3 = "おめでとう！社会でも困らない！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
			kyu--;
			hantei++;
		}
		//2級終了
		if (cmd.equals("次へ") && kirikae == 101 && pgc == 100) 
		{
			Junikkyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 101 && kirikae == 101) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 101 && kirikae == 101) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 101 && kirikae == 101) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 101 && kirikae == 101) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 101 && kirikae == 101) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 101 && kirikae == 101) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 102 && pgc == 101) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi102.jpg"));
			kara.setIcon(icon);

			no1 = "じょうこう";
			no2 = "さいり";
			no3 = "おんどん";
			no4 = "たんいつ";
			no5 = "れんこう";
			no6 = "あんかん";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "無理しなくていいんだからね・・・？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 102 && kirikae == 102) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 102 && kirikae == 102) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 102 && kirikae == 102) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 102 && kirikae == 102) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 102 && kirikae == 102) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 102 && kirikae == 102) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 103 && pgc == 102) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi103.jpg"));
			kara.setIcon(icon);

			no1 = "すうかい";
			no2 = "しっかい";
			no3 = "おんかい";
			no4 = "じっかい";
			no5 = "いんかい";
			no6 = "えんかい";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "無理しなくていいんだからね・・・？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 103 && kirikae == 103) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 103 && kirikae == 103) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 103 && kirikae == 103) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 103 && kirikae == 103) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 103 && kirikae == 103) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 103 && kirikae == 103) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 104 && pgc == 103) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi104.jpg"));
			kara.setIcon(icon);

			no1 = "とのこいろ";
			no2 = "あかくちばいろ";
			no3 = "あいてついろ";
			no4 = "うつぶしいろ";
			no5 = "きはだいろ";
			no6 = "ときいろ";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "無理しなくていいんだからね・・・？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 104 && kirikae == 104) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 104 && kirikae == 104) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 104 && kirikae == 104) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 104 && kirikae == 104) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 104 && kirikae == 104) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 104 && kirikae == 104) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 105 && pgc == 104) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi105.jpg"));
			kara.setIcon(icon);

			no1 = "さんかい";
			no2 = "てっかい";
			no3 = "きょかい";
			no4 = "ろんかい";
			no5 = "じゅっかい";
			no6 = "ゆうかい";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "無理しなくていいんだからね・・・？";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 105 && kirikae == 105) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 105 && kirikae == 105) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 105 && kirikae == 105) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 105 && kirikae == 105) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 105 && kirikae == 105) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 105 && kirikae == 105) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 106 && pgc == 105) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi106.jpg"));
			kara.setIcon(icon);

			systemmess.setText("準" + kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "膃肭臍";
			no2 = "猟虎";
			no3 = "海栗";
			no4 = "海星";
			no5 = "栄螺";
			no6 = "海豹";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "意外なものも漢字で書けるんです";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 106 && kirikae == 106) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 106 && kirikae == 106) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 106 && kirikae == 106) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 106 && kirikae == 106) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 106 && kirikae == 106) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 106 && kirikae == 106) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 107 && pgc == 106) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi107.jpg"));
			kara.setIcon(icon);

			no1 = "蝦蟇";
			no2 = "飛蝗";
			no3 = "天牛";
			no4 = "蜉蝣";
			no5 = "鼈";
			no6 = "玳瑁";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "意外なものも漢字で書けるんです";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 107 && kirikae == 107) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 107 && kirikae == 107) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 107 && kirikae == 107) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 107 && kirikae == 107) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 107 && kirikae == 107) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 107 && kirikae == 107) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 108 && pgc == 107) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi108.jpg"));
			kara.setIcon(icon);

			no1 = "信天翁";
			no2 = "鷺";
			no3 = "梟";
			no4 = "鴛鴦";
			no5 = "鶺鴒";
			no6 = "翡翠";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "意外なものも漢字で書けるんです";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 108 && kirikae == 108) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 108 && kirikae == 108) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 108 && kirikae == 108) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 108 && kirikae == 108) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 108 && kirikae == 108) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 108 && kirikae == 108) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 109 && pgc == 108) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi109.jpg"));
			kara.setIcon(icon);

			no1 = "愛愛";
			no2 = "厭厭";
			no3 = "與與";
			no4 = "綏綏";
			no5 = "欣欣";
			no6 = "忽忽";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "意外なものも漢字で書けるんです";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 109 && kirikae == 109) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 109 && kirikae == 109) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 109 && kirikae == 109) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 109 && kirikae == 109) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 109 && kirikae == 109) {
			setsumei3 = "正解！凄いよ！";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 109 && kirikae == 109) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 110 && pgc == 109) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi110.jpg"));
			kara.setIcon(icon);

			no1 = "胤胤";
			no2 = "儘儘";
			no3 = "燦燦";
			no4 = "惚惚";
			no5 = "耀耀";
			no6 = "輝輝";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "意外なものも漢字で書けるんです";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 110 && kirikae == 110) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 110 && kirikae == 110) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 110 && kirikae == 110) {
			setsumei3 = "正解・・・準1級クリアです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
			hantei++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 110 && kirikae == 110) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 110 && kirikae == 110) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 110 && kirikae == 110) {
			Fuseikai();
		}
		//準一級終了

		if (cmd.equals("次へ") && kirikae == 111 && pgc == 110)
		{
			Ikkyu();
		}

		if (cmd.equals("回答") && status1 == true && pgc == 111 && kirikae == 111) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 111 && kirikae == 111) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 111 && kirikae == 111) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 111 && kirikae == 111) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 111 && kirikae == 111) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 111 && kirikae == 111) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 112 && pgc == 111) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi112.jpg"));
			kara.setIcon(icon);

			no1 = "エスカレーター";
			no2 = "エレベーター";
			no3 = "バス";
			no4 = "タクシー";
			no5 = "フォーク";
			no6 = "サービス";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "何でも漢字で書けちゃう時代";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 112 && kirikae == 112) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 112 && kirikae == 112) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 112 && kirikae == 112) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 112 && kirikae == 112) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 112 && kirikae == 112) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 112 && kirikae == 112) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 113 && pgc == 112) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi113.jpg"));
			kara.setIcon(icon);

			no1 = "アイロン";
			no2 = "オルガン";
			no3 = "テニス";
			no4 = "サッカー";
			no5 = "ビリヤード";
			no6 = "バドミントン";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "何でも漢字で書けちゃう時代";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 113 && kirikae == 113) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 113 && kirikae == 113) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 113 && kirikae == 113) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 113 && kirikae == 113) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 113 && kirikae == 113) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 113 && kirikae == 113) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 114 && pgc == 113) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi114.jpg"));
			kara.setIcon(icon);

			no1 = "トンネル";
			no2 = "ケーブル";
			no3 = "ファイバー";
			no4 = "マリンロード";
			no5 = "シルクロード";
			no6 = "ハビタブルゾーン";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "何でも漢字で書けちゃう時代";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 114 && kirikae == 114) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 114 && kirikae == 114) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 114 && kirikae == 114) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 114 && kirikae == 114) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 114 && kirikae == 114) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 114 && kirikae == 114) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 115 && pgc == 114) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi115.jpg"));
			kara.setIcon(icon);

			no1 = "マルク";
			no2 = "ウォン";
			no3 = "ルピー";
			no4 = "ドル";
			no5 = "ポンド";
			no6 = "ユーロ";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "何でも漢字で書けちゃう時代";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 115 && kirikae == 115) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 115 && kirikae == 115) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 115 && kirikae == 115) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 115 && kirikae == 115) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 115 && kirikae == 115) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 115 && kirikae == 115) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 116 && pgc == 115) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi116.jpg"));
			kara.setIcon(icon);

			systemmess.setText(kyu + "級 書き");

			hint = "次の下線部の書きを答えなさい";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			kara2.setText(hint);

			no1 = "粁";
			no2 = "粉";
			no3 = "糎";
			no4 = "粍";
			no5 = "粨";
			no6 = "籵";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "無理矢理漢字にしてる感半端ない";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 116 && kirikae == 116) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status2 == true && pgc == 116 && kirikae == 116) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 116 && kirikae == 116) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 116 && kirikae == 116) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 116 && kirikae == 116) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 116 && kirikae == 116) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 117 && pgc == 116) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi117.jpg"));
			kara.setIcon(icon);

			no1 = "情人節言語";
			no2 = "老練家言語";
			no3 = "鋸刺鮭言語";
			no4 = "臙脂色言語";
			no5 = "超文本標記言語";
			no6 = "電脳病毒言語";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "漢字にITなど関係ない！";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 117 && kirikae == 117) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 117 && kirikae == 117) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 117 && kirikae == 117) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 117 && kirikae == 117) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 117 && kirikae == 117) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status6 == true && pgc == 117 && kirikae == 117) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 118 && pgc == 117) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi118.jpg"));
			kara.setIcon(icon);

			no1 = "土瀝青";
			no2 = "輪盤賭";
			no3 = "瀏覧器";
			no4 = "機器人";
			no5 = "雨外套";
			no6 = "天鵞絨";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "漢字にITなど関係ない！";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 118 && kirikae == 118) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 118 && kirikae == 118) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 118 && kirikae == 118) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("回答") && status4 == true && pgc == 118 && kirikae == 118) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 118 && kirikae == 118) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 118 && kirikae == 118) {
			Fuseikai();
		}

		if (cmd.equals("次へ") && kirikae == 119 && pgc == 118) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi119.jpg"));
			kara.setIcon(icon);

			no1 = "人間代理機";
			no2 = "分岐判断機";
			no3 = "命令処理機";
			no4 = "機器管理機";
			no5 = "文字出力機";
			no6 = "数字計算機";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "漢字にITなど関係ない！";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 119 && kirikae == 119) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 119 && kirikae == 119) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status3 == true && pgc == 119 && kirikae == 119) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 119 && kirikae == 119) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 119 && kirikae == 119) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 119 && kirikae == 119) {
			setsumei3 = "正解・・・実は文系？";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);
			kirikae++;
		}

		if (cmd.equals("次へ") && kirikae == 120 && pgc == 119) {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(cl.getResource("mondai/toi120.jpg"));
			kara.setIcon(icon);

			no1 = "閉機";
			no2 = "關機";
			no3 = "綏機";
			no4 = "屡機";
			no5 = "裳機";
			no6 = "蔚機";

			radio1.setText(no1);
			radio2.setText(no2);
			radio3.setText(no3);
			radio4.setText(no4);
			radio5.setText(no5);
			radio6.setText(no6);

			setsumei3 = "漢字にITなど関係ない！";
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));
			shintyu3.setForeground(Color.black);
			shintyu3.setText(setsumei3);

			pgc++;

		}

		if (cmd.equals("回答") && status1 == true && pgc == 120 && kirikae == 120) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status2 == true && pgc == 120 && kirikae == 120) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "全ての問題を解いた！";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "1級 文系大学卒業レベルです";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}

		if (cmd.equals("回答") && status3 == true && pgc == 120 && kirikae == 120) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status4 == true && pgc == 120 && kirikae == 120) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status5 == true && pgc == 120 && kirikae == 120) {
			Fuseikai();
		}

		if (cmd.equals("回答") && status6 == true && pgc == 120 && kirikae == 120) {
			Fuseikai();
		}

		if (hp == 0 && hantei == 0) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "10級未満 小学1年以下です。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		//10級未満
		if (hp == 0 && hantei == 1) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "10級 小学1年レベルです";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		//10級レベル
		if (hp == 0 && hantei == 2) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "9級 小学2年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		//9級レベル
		if (hp == 0 && hantei == 3) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "8級 小学3年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		//8
		if (hp == 0 && hantei == 4) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "7級 小学4年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		//7
		if (hp == 0 && hantei == 5) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "6級 小学5年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		if (hp == 0 && hantei == 6) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "5級 小学6年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}

		if (hp == 0 && hantei == 7) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "4級 中学1～2年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		if (hp == 0 && hantei == 8) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "3級 中学1卒業レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}

		if (hp == 0 && hantei == 9) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "準2級 高校1～2年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}

		if (hp == 0 && hantei == 10) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "2級 高校卒業レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}

		if (hp == 0 && hantei == 11) {
			bgm.close();
			bgm = new MyAudioPlayer("music/ED.mp3", false);
			
			setsumei = "GAME OVER";
			shintyu.setText(setsumei);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 50));

			setsumei2 = "あなたの漢字力は・・・";
			shintyu2.setText(setsumei2);
			shintyu2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 20));

			setsumei3 = "準1級 文系大学1～3年レベルです。";
			shintyu3.setText(setsumei3);
			shintyu3.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			shintyu3.setForeground(Color.red);

			pgc = 1000;
		}
		if (pgc == 1000) {
			hint = "「次へ」をクリックで再走できます";
			kara2.setFont(new Font("ＭＳ 明朝", Font.CENTER_BASELINE, 30));
			kara2.setText(hint);
		}
		if (cmd.equals("次へ") && pgc == 1000) {
			kyu = 10;
			kirikae = 1;
			pgc = 0;
			hp = 10;
			hantei = 0;
			clay.first(dodai);
			bgm.close();
		}
	}
}