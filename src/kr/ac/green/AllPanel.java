package kr.ac.green;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;

public class AllPanel extends JPanel{
	
	private JPanel pnlAll;
	
	private JLabel lblNumber = new JLabel("2");
	private JLabel lblState;
	
	private MyLotto myNum;
	
	private JButton btnMenual;
	private JButton btnAuto;
	private JButton btnCancel;

	private int gameNum = 0;
	
	private Buy buy;
	
	public JLabel getLblNumber() {
		return lblNumber;
	}

	public void setLblNumber(JLabel lblNumber) {
		this.lblNumber = lblNumber;
	}
	
	
	public AllPanel() {
		init();
		setDisplay();
		addListener();
	}
	
	public void setLabelText(int num) {
		lblNumber.setText(num+"");
	}
	
	public void init() {
		myNum = new MyLotto();
		lblState = new JLabel();
		btnMenual = new JButton("수동");
		btnAuto = new JButton("자동");
		btnCancel = new JButton("삭제");
		
	}
	
	public void setDisplay() {
		

		setBackground(Color.WHITE);
	
		add(lblNumber);
		add(lblState);
		lblState.setPreferredSize(new Dimension(10,10));
		add(myNum);
		add(btnMenual);
		add(btnAuto);
		add(btnCancel);
		
		btnAuto.setPreferredSize(new Dimension(60, 30));
		btnCancel.setPreferredSize(new Dimension(60, 30));
		// pnlAll.add(pnlOne);
	}
	
	public void addListener() {
		
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnMenual == e.getSource()) {	
					new Button(buy);
				}
				if(btnCancel == e.getSource()) {
					int num = DataCenter.getInstance().getBuyNum();
					
				}
			}
		};
		btnMenual.addActionListener(aListener);
		btnCancel.addActionListener(aListener);
	}
	
}
