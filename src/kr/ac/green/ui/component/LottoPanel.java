package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.model.Lotto;
import kr.ac.green.ui.ManualButton;


/** 
 * @author qortmdgns15
 * 로또 한줄에 대한 패널 정보 
 */
public class LottoPanel extends JPanel{
	
	private JPanel pnlAll;
	
	private JLabel lblNumber;
	private JLabel lblState;
	
	private MyLotto myNum;
	
	private JButton btnMenual;
	private JButton btnAuto;
	private JButton btnCancel;
	private Lotto lotto;
	private int index;
	
	public LottoPanel(int index,Lotto lotto) {
		this.index = index;
		this.lotto = lotto;
		
		init();
		setDisplay();
		addListener();
	}
	
	public void init() {
		this.lblNumber = new JLabel(index+1+"");
		this.myNum = new MyLotto();
		this.lblState = new JLabel();
		this.btnMenual = new JButton("수동");
		this.btnAuto = new JButton("자동");
		this.btnCancel = new JButton("삭제");
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
				new ManualButton();
			}
		};
		btnMenual.addActionListener(aListener);
		btnCancel.addActionListener(aListener);
	}
	
}
