package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;



public class MyLotto extends JPanel {

	private JLabel[] lblBalls;

	private int index;
	private String imageSet;

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
			int lottoNum = dataCenter.getLottoList().get(index).getLottoNumber()[i] == 0 ? 0 : dataCenter.getLottoList().get(index).getLottoNumber()[i];

			if(lottoNum == 0) {
				imageSet = "f";
			}else if(lottoNum < 10) {
				imageSet = "a";
			}else if(lottoNum < 20) {
				imageSet = "b";
			}else if(lottoNum < 30) {
				imageSet = "c";
			}else if(lottoNum < 40) {
				imageSet = "d";
			}else {
				imageSet = "e";
			}
			ImageIcon icon = new ImageIcon(imageSet + ".png");
			Image img = icon.getImage();
			Image newImage = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
			lblBalls[i] = new JLabel(lottoNumber, new ImageIcon(newImage), JLabel.CENTER );
			lblBalls[i].setHorizontalTextPosition(JLabel.CENTER);
			lblBalls[i].setPreferredSize(new Dimension(27,27));
			lblBalls[i].setForeground(Color.WHITE);
			add(lblBalls[i]);



			setBackground(Color.WHITE);
		}
	}
}
