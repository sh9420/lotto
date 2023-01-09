package  kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import kr.ac.green.data.DataCenter;

public class Start extends JFrame {
    private JComboBox cbChoice;
    private JButton btnBuy;
    private JLabel lblLogo;
    
    private String[] num = {"구매하실 수량을 선택하세요.", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	
	public Start(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        init();
        setDisplay();
        addListener();
        showFrame();
    }
	
    public void init(){
    	Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("logo.png");
		ImageIcon icon = new ImageIcon(img);
		
		
        cbChoice = new JComboBox(num);
        cbChoice.setPreferredSize(new Dimension(200,30));
        btnBuy = new JButton("구매");
        btnBuy.setPreferredSize(new Dimension(60,30));
        lblLogo = new JLabel(new ImageIcon(img));
        lblLogo.setPreferredSize(new Dimension(450,450));
    }

    public void setDisplay(){
        JPanel pnlCenter = new JPanel(new BorderLayout());
        JPanel pnlCCenter = new JPanel();
        JPanel pnlSouth = new JPanel();
        
        pnlSouth.add(cbChoice);
        pnlSouth.add(btnBuy);

        pnlSouth.setBackground(Color.WHITE);
        
        pnlSouth.setBorder(new EmptyBorder(0,0,20,0));
        
        pnlCCenter.add(lblLogo);
        pnlCCenter.setBackground(Color.WHITE);
        
        
        pnlCenter.add(pnlCCenter,BorderLayout.CENTER);
        pnlCenter.add(pnlSouth, BorderLayout.SOUTH);
        
        
        add(pnlCenter, BorderLayout.CENTER);
        btnBuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void addListener(){
    	ActionListener aListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					//사용자가 선택한 로또 구매 갯수
					int selectCnt = Integer.parseInt(cbChoice.getSelectedItem().toString());
					
					int next = JOptionPane.showConfirmDialog(
						null,
						selectCnt + "개 구매 할까요??",
						"구매",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
					);
					if(next == JOptionPane.YES_OPTION) {
						DataCenter.getInstance().setBuyLotto(selectCnt);
						dispose();
						new Buy(Start.this);
						System.exit(next);
					}
				}catch(NumberFormatException ae) {
					
				}
			}
		};
		btnBuy.addActionListener(aListener);
    }
    

    public void showFrame(){
        setTitle("LOTTO");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
   
    
    public static void main(String[] args) {
		new Start();
	}
}
