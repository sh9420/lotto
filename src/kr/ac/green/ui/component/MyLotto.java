package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyLotto extends JPanel {
	
	private JLabel[] lblBalls;
	
	public MyLotto() {
		init();
		BuyPanel();

	}
	
	public void init() {
		lblBalls = new JLabel[6];
	}
	
	public void BuyPanel() {
	
		for(int i=0; i<6; i++) { //번호 들어가는 레이블 6
			lblBalls[i] = new JLabel();
			lblBalls[i].setPreferredSize(new Dimension(50,50));
			lblBalls[i].setBorder(new LineBorder(Color.LIGHT_GRAY,1));
			add(lblBalls[i]);
			setBackground(Color.WHITE);
		}
	}
	

}
