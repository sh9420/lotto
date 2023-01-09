package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;
import kr.ac.green.model.Lotto;
import kr.ac.green.ui.Buy;
import kr.ac.green.ui.ManualButton;


/** 
 * 
 * 로또 한줄에 대한 패널 정보 
 */
public class LottoPanel extends JPanel{
	
	private JLabel lblNumber;
	private JLabel lblState;
	
	private MyLotto myNum;
	
	private JButton btnManual;
	private JButton btnAuto;
	private JButton btnCancel;
	
	private Buy buy;
	private int index;

	private Lotto lotto;
	
	private DataCenter dataCenter;
	
	
	public LottoPanel(int index, Buy buy) {
		this.dataCenter = DataCenter.getInstance();
		this.index = index;
		this.buy = buy;
	
		init();
		setDisplay();
		addListener();
	}
	
	public void init() {

	
		this.lotto = dataCenter.getLottoList().get(index);
		this.lblNumber = new JLabel(index+1+".");
		this.myNum = new MyLotto(index);
		this.lblState = new JLabel(lotto.getState(), JLabel.CENTER);
		this.btnManual = new JButton("수동");
		this.btnAuto = new JButton("자동");
		this.btnCancel = new JButton("삭제");
		
		lblNumber.setPreferredSize(new Dimension(20,10));
	}
	
	public void setDisplay() {
		setBackground(Color.WHITE);
		
		add(lblNumber);
		add(lblState);
		lblState.setPreferredSize(new Dimension(40,20));
		add(myNum);
		add(btnManual);
		add(btnAuto);
		add(btnCancel);
		
		btnManual.setPreferredSize(new Dimension(60, 30));
		btnAuto.setPreferredSize(new Dimension(60, 30));
		btnCancel.setPreferredSize(new Dimension(60, 30));
	}
	
	public void addListener() {

		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnManual == e.getSource()) {
					new ManualButton(index, buy);
				}
				else if(btnAuto == e.getSource()) {
					buy.autoBuy(index);
				}
				else if(btnCancel == e.getSource()) {
					if(dataCenter.getLottoList().size() != 1) {
						dataCenter.getLottoList().remove(index);
						buy.updatePanel();
					}
					else {
						JOptionPane.showMessageDialog(LottoPanel.this, "1개 미만으로는 삭제가 불가능 합니다.");
					}
				}
			}
		};
		
		btnManual.addActionListener(aListener);
		btnAuto.addActionListener(aListener);
		btnCancel.addActionListener(aListener);
	}
}
