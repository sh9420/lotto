package kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;
import kr.ac.green.ui.component.LottoPanel;

public class Buy extends JDialog {
	
	private JLabel lblImage;
	private JLabel lblCount;
	private JButton btnCheck;
	private JButton btnAdd;
	
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JPanel pnlSouth;
	
	private DataCenter dataCenter;
	
	
	public Buy(Start owner) {
		super(owner,"BUY",true);
        init();
        setDisplay();
        addListener();
        showFrame();
	}
	
	public void init() {
		this.dataCenter=DataCenter.getInstance();
		
		Font font = new Font("���� ���", Font.BOLD, 15); // �۲� ���߿�
    	Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("logo.png");
		Image back = kit.getImage("Back.png");
		ImageIcon icon = new ImageIcon(img);
		ImageIcon iconback = new ImageIcon(back);
		Image newImage = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		
		
		lblImage = new JLabel(new ImageIcon(newImage));
		lblImage.setPreferredSize(new Dimension(100,100));
		
		lblCount = new JLabel(" ���� ����   :   " + dataCenter.getLottoList().size());
		lblCount.setPreferredSize(new Dimension(200,30));
		lblCount.setFont(font);
		btnCheck = new JButton("��÷ Ȯ��");
		btnCheck.setFont(font);
		btnAdd = new JButton("�߰� ����");
		btnAdd.setFont(font);
	}
	
	public void setDisplay() {
		pnlNorth = new JPanel();
		pnlSouth = new JPanel();
		pnlCenter = new JPanel(new GridLayout(0,1));
		
		pnlNorth.add(lblImage);
		pnlNorth.add(lblCount);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		buy();
		
		pnlSouth.add(btnCheck);
		pnlSouth.add(btnAdd);
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
		
		
		pnlNorth.setBackground(Color.WHITE);
		pnlCenter.setBackground(Color.WHITE);
		pnlSouth.setBackground(Color.WHITE);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	public void buy() {
		for(int i = 0 ; i < dataCenter.getLottoList().size() ; i++) {
			// �ζ� ���ٿ� ���� Panel
			pnlCenter.add(new LottoPanel(i,this));
		}
	}

	public void addListener() {
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = dataCenter.addLottoList();
				if(index < 10) {
					pnlCenter.add(new LottoPanel(index,Buy.this));
					lblCount.setText(" ���� ����   :   " + (index+1) + "");
					pack();
			        setVisible(true);
				}
			}
		};
		btnAdd.addActionListener(aListener);
	}
	
	
	/**
	 * �г� UPDATE (�ζ� ������ ������ �ǰų�, �����Ǵ� ���)
	 */
	public void updatePanel() {
		lblCount.setText(" ���� ����   :   " + dataCenter.getLottoList().size());
		
		remove(pnlNorth);
		remove(pnlCenter);
		remove(pnlSouth);
		
		setDisplay();
		pack();
        setVisible(true);
	}
	
    public void showFrame() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
