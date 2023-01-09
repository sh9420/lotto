package kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Confirm extends JFrame{
	
	private int lotto[]; // ���� �迭 ��
	private int lottoBonus;
	private String[] lottoNumber;
	
	private JLabel lblWinText;
	private JLabel lblPlus;
	private JLabel lblBonusNum;
	
	private JLabel[] lblWinNum;
	private JLabel lblMyLottoInfo;
	private JLabel lblWinNumInfo;
	private JLabel lblRankInfo;
	
	private JButton btnRaffle;
	private JButton btnWinConfirm;
	private JButton btnTracking;
	private JButton btnMain;
	
	private Dimension sizeOf = new Dimension(300,30);
	
	private int index;
	
	int color = 0x4682B4;

	
	
	public Confirm() {
		//this.index = index;
		init();
        setDisplay();
        addListener();
        showFrame();
	}
	
	public void init() {
		// pnlNorth.add
		lblWinText = new JLabel("��÷ ��ȣ");
		lotto = new int[6];
		lblWinNum = new JLabel[8];
		lottoNumber = new String[8];
		lblPlus = new JLabel("+");
		lblBonusNum = new JLabel();
		lblBonusNum.setPreferredSize(new Dimension(20,20));
		lblBonusNum.setBorder(new LineBorder(Color.BLACK));
		// pnlCenter.add
		lblMyLottoInfo = getLabel("���� ��ȣ");
		lblWinNumInfo = getLabel("��÷ ��ȣ");
		lblRankInfo = getLabel("��÷ ���");
		// pnlSouth.add
		
		btnRaffle = new JButton("��÷");
		btnWinConfirm = new JButton("��÷ Ȯ��");
		btnTracking = new JButton("����");
		btnMain = new JButton("����ȭ��");
		
		
		
	}
	public void setDisplay() {
	// pnlNorth.add
		JPanel pnlNorth = getPanel();
		JPanel pnlWinNum = getPanel();

	// pnlCenter.add
		JPanel pnlCenter = getPanel();
		pnlCenter.setBorder(new LineBorder(Color.BLACK));
		JPanel pnlCWest = getPanel();
		pnlCWest.setLayout(new GridLayout(0,1));
		JPanel pnlCCenter = getPanel();
		pnlCCenter.setLayout(new GridLayout(0,1));
		JPanel pnlCEast = getPanel();
		pnlCEast.setLayout(new GridLayout(0,1));
		

	// pnlSouth.add
		JPanel pnlSouth = getPanel();
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
	// Panel North
		
		for(int i=0; i<8; i++) {	//��÷ ��ȣ + ���ʽ� ��ȣ
			lblWinNum[i] = new JLabel();
			if(i==6) {
				lblWinNum[i].setText("+");
			}
			lblWinNum[i].setPreferredSize(new Dimension(20,20));
			lblWinNum[i].setBorder(new LineBorder(Color.BLACK));
			pnlWinNum.add(lblWinNum[i]);
		}
		
		pnlNorth.add(lblWinText); 		// ��÷ ��ȣ �ؽ�Ʈ
		pnlNorth.add(pnlWinNum);		// �������� ���� ��÷ ��ȣ (��ȣ 6�ڸ� + ���ʽ��ѹ�)
		
		pnlNorth.add(btnRaffle);		// ��÷ ��ư
		pnlNorth.add(btnWinConfirm);	// ��÷Ȯ�� ��ư
		
	// pnlCenter.add
		pnlCWest.add(lblMyLottoInfo); 	// ���� ������ ��ȣ
		pnlCCenter.add(lblWinNumInfo);	// ���� ��ȣ �� ��÷��ȣ
		pnlCEast.add(lblRankInfo);		// ��÷ ����	
		
	// pnlCenter.Center
		for(int idx=0; idx < 10;/*���� ����*/ idx++) {				// ���� ������ ������ŭ �ݺ������� �������� ���̺� 
			JPanel lblMyLotto = getPanel();					// ���� ��ȣ index�� ������� �̾ƿ�?	
			lblMyLotto.setPreferredSize(sizeOf);
			lblMyLotto.setBorder(new LineBorder(Color.BLACK,1));
			
			JPanel lblWinNumber = getPanel();				// ���� ��ȣ�� ��÷��ȣ ���ؼ� ���̺� �߰�
			lblWinNumber.setPreferredSize(sizeOf);
			lblWinNumber.setBorder(new LineBorder(Color.RED,1));
			
			JPanel lblRank = getPanel();						// �� ��÷ �Ǿ����� Ȯ��.	
			lblRank.setPreferredSize(new Dimension(200,30));
			lblRank.setBorder(new LineBorder(Color.BLUE,1));
			
			pnlCWest.add(lblMyLotto);
			pnlCCenter.add(lblWinNumber);
			pnlCEast.add(lblRank);
		}
		pnlCenter.add(pnlCWest, BorderLayout.WEST);
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCenter.add(pnlCEast, BorderLayout.EAST);
		
		// pnlSouth
		pnlSouth.add(btnTracking);	// ���� ��ư
		pnlSouth.add(btnMain);		// ����ȭ������ �̵� ��ư
		
		//


		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	
	public JPanel getPanel() {
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.WHITE);
		return pnl;
	}
	
	public JLabel getLabel(String name) {
		JLabel lbl = new JLabel(name, JLabel.CENTER);
		return lbl;
	}
	
	public void addListener() {

		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnRaffle == e.getSource()) {
					for (int i = 0; i < 6; i++)
					{	
						Random r = new Random();
						lotto[i] = r.nextInt(45)+1;
						for (int j = 0; j < i; j++) {
							if (lotto[j] == lotto[i]) {
								i--;
							}
						}
					} 
					Arrays.sort(lotto); // ������������ ����
					for(int i=0; i<7; i++) { 
						lottoNumber[i] = Integer.toString(lotto[i]);
						if(i == 7) {
							i++;
							lblWinNum[i].setText(lottoNumber[i]);
						}
						lblWinNum[i].setText(lottoNumber[i]);
						pack();
						setBackground(Color.WHITE);
					}
					System.out.println(Arrays.toString(lotto)); // System.out.println(lotto)�� ������(�ּҰ�) ���
					btnRaffle.setEnabled(false);
				}
				else if(btnWinConfirm == e.getSource()) {
					
				}
				else if(btnTracking == e.getSource()) {
					
				}
				else if(btnMain == e.getSource()) {
					
				}
			}
		};
		
		btnRaffle.addActionListener(aListener);
		btnWinConfirm.addActionListener(aListener);
		btnTracking.addActionListener(aListener);
		btnMain.addActionListener(aListener);
	}
	public void showFrame() {
		setTitle("������Բ�");
		pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
}
