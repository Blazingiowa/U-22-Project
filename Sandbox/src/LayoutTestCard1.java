import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LayoutTestCard1 extends JFrame implements ItemListener {

  JPanel cardPanel;
  
  public LayoutTestCard1(){
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(200,150);

    Container contentPane = getContentPane();
    
    //カードレイアウトで切り替える部分
    cardPanel = new JPanel();
    cardPanel.setLayout(new CardLayout());

    JPanel card1 = new JPanel();
    card1.add(new JButton("ABC"));
    card1.add(new JButton("XYZ"));
    
    JPanel card2 = new JPanel();
    card2.add(new JTextField("Test field on Card2"));

    cardPanel.add(card1, "Card1");
    cardPanel.add(card2, "Card2");
    
    //コンボボックスを配置する部分
    JPanel topPanel = new JPanel();
    
    String comboboxStrings[] = { "Card1", "Card2" };
    JComboBox comboBox = new JComboBox(comboboxStrings);
    comboBox.addItemListener(this);
    
    topPanel.add(comboBox);
    
    //コンテントペインにパネルを追加
    contentPane.add(topPanel, BorderLayout.NORTH);
    contentPane.add(cardPanel, BorderLayout.CENTER);
  }
  
  public static void main(String[] args) {    
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        createAndShowGUI();
      }
    });
  }

  protected static void createAndShowGUI() {
    LayoutTestCard1 frame = new LayoutTestCard1();
    frame.setVisible(true);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    CardLayout cl = (CardLayout)(cardPanel.getLayout());
      cl.show(cardPanel, (String)e.getItem());    
  }

}