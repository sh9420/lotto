package kr.ac.green.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	private JPanel pnlAll;
	
	private JLabel lblNumber;
	private JLabel lblState;
	
	private MyLotto myNum;
	
	private JButton btnMenual;
	private JButton btnAuto;
	private JButton btnCancel;
	
	private Buy buy;
	private ManualButton manualButton;
	private int index;
	private Lotto lotto;
	
	private DataCenter dataCenter;
	
	private ArrayList<Integer> lottoNumber = new ArrayList<Integer>();
	
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
		this.btnMenual = new JButton("수동");
		this.btnAuto = new JButton("자동");
		this.btnCancel = new JButton("삭제");
	}
	
	public void setDisplay() {
		setBackground(Color.WHITE);
		
		add(lblNumber);
		add(lblState);
		lblState.setPreferredSize(new Dimension(40,20));
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
					if(dataCenter.getLottoList().get(index).getLottoNumber()[0] == 0) {
						new ManualButton(index, buy);
					}else {
						for(int i = 0; i < 6 ; i++) {
							int num = dataCenter.getLottoList().get(index).getLottoNumber()[i];
							lottoNumber.add(num);
						}
						//manualButton.setSelectNum(lottoNumber);
					}
				}
				else if(btnAuto == e.getSource()) {
					Random r = new Random();
					Lotto lotto = dataCenter.getLottoList().get(index);
					int[] rNum = new int[6];
					for(int i = 0 ; i < 6 ; i++) {
						rNum[i] = r.nextInt(45)+1;
						for(int j = 0 ; j < i ; j++) {
							if(rNum[i] == rNum[j]) {
								i--;
							}
						}
					}
					
					Arrays.sort(rNum);
					lotto.setLottoNumber(rNum);
					lotto.setState("자동");
					dataCenter.updateLottoList(index, lotto);
                    buy.init();
                	buy.setDisplay();
                    buy.showFrame();
				}
			}
		};
		
		btnMenual.addActionListener(aListener);
		btnAuto.addActionListener(aListener);
		btnCancel.addActionListener(aListener);
	}
}
