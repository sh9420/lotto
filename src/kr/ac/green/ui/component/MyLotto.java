package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import kr.ac.green.data.DataCenter;
import kr.ac.green.model.Lotto;

public class MyLotto extends JPanel {
	
	private JLabel[] lblBalls;
	
	private int index;
	
	private DataCenter dataCenter;
	
	public MyLotto(int index) {
		this.index = index;
		init();
		BuyPanel();
	}
	
	public void init() {
		lblBalls = new JLabel[6];
		dataCenter = DataCenter.getInstance();
	}
	
	public void BuyPanel() {
	
		for(int i=0; i<6; i++) { //번호 들어가는 레이블 6
			String lottoNumber = dataCenter.getLottoList().get(index).getLottoNumber()[i] == 0 ? "" : dataCenter.getLottoList().get(index).getLottoNumber()[i]+""; 
			lblBalls[i] = new JLabel(lottoNumber, JLabel.CENTER);
			lblBalls[i].setPreferredSize(new Dimension(25,25));
			lblBalls[i].setBorder(new LineBorder(Color.LIGHT_GRAY,1));
			add(lblBalls[i]);
			setBackground(Color.WHITE);
		}
	}
}
