package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.ui.ResultForm;

public class WinLottoPanel extends JPanel{
	
	private JLabel[] lblWinNum;
	private int index;
	private String imageSet;

	private JPanel pnl;
	private ResultForm resultForm;

	public WinLottoPanel(int index, ResultForm resultForm) {
		this.index = index;
		this.resultForm = resultForm;
		setBackground(Color.WHITE);
		init();
		showPanel();
	}
	
	public void init() {
		lblWinNum = new JLabel[resultForm.getWinNumList().size()];
		pnl = new JPanel();
	}
	
	public void showPanel() {
		if (resultForm.getWinNumList().get(index).size() != 0) {
			for (int i = 0; i < resultForm.getWinNumList().get(index).size(); i++) {
				System.out.println(resultForm.getWinNumList().get(index).get(i));
				int winNumber = (int) resultForm.getWinNumList().get(index).get(i);

				if (winNumber < 10) {
					imageSet = "a";
				} else if (winNumber < 20) {
					imageSet = "b";
				} else if (winNumber < 30) {
					imageSet = "c";
				} else if (winNumber < 40) {
					imageSet = "d";
				} else {
					imageSet = "e";
				}
				ImageIcon icon = new ImageIcon(imageSet + ".png");
				Image img = icon.getImage();
				Image newImage = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				lblWinNum[i] = new JLabel(winNumber + "", new ImageIcon(newImage), JLabel.CENTER);
				lblWinNum[i].setHorizontalTextPosition(JLabel.CENTER);
				lblWinNum[i].setPreferredSize(new Dimension(27, 27));
				lblWinNum[i].setForeground(Color.WHITE);
				pnl.setBackground(Color.WHITE);
				pnl.add(lblWinNum[i]);
			}
			add(pnl);
		}
		else {
			JLabel lblNo = new JLabel("일치하는 번호가 없습니다.", JLabel.CENTER);
			pnl.setBackground(Color.WHITE);
			pnl.add(lblNo);
			add(pnl);
		}
	}
}
