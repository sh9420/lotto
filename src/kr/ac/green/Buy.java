package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.green.data.DataCenter;

public class Buy extends JDialog {
	
	private JLabel lblImage;
	private JLabel lblCount;
	private JButton btnCheck;
	private JButton btnAdd;

	private int number = 0;
	
	// private String numChoice = "자동";
	
	private int num;

	private JButton btnAuto;
	private JButton btnCancel;
	
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	
	private JPanel pnlAdd;
	
	private MyLotto myLotto;
	
	private AllPanel allPanel;
	
	private ArrayList<JPanel> arrPanel = new ArrayList<JPanel>();
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public ArrayList<JPanel> getArrPanel() {
		return arrPanel;
	}
	public void setArrPanel(ArrayList<JPanel> arrPanel) {
		this.arrPanel = arrPanel;
	}

	public Buy(Start owner) {
		super(owner,"BUY",true);
        init();
        setDisplay();
        System.out.println(num);
        addListener();
        showFrame();
       
	}
	
	

	public void init() {
		Font font = new Font("맑은 고딕", Font.BOLD, 15); // 글꼴 나중에
    	Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("logo.png");
		Image back = kit.getImage("Back.png");
		ImageIcon icon = new ImageIcon(img);
		ImageIcon iconback = new ImageIcon(back);
		
		Image newImage = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		
		
		lblImage = new JLabel(new ImageIcon(newImage));
		lblImage.setPreferredSize(new Dimension(100,100));
		
	
		
		lblCount = new JLabel(" 선택 수량   :   " + DataCenter.getInstance().getBuyNum() + " ");
		lblCount.setPreferredSize(new Dimension(200,30));
		lblCount.setFont(font);
		btnCheck = new JButton("당첨 확인");
		btnCheck.setFont(font);
		btnAdd = new JButton("추가 구매");
		btnAdd.setFont(font);
		
	}
	
	public JLabel getLabel(String whatProduce) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		String fileName = whatProduce +".png";
		Image img = kit.getImage(fileName);
		ImageIcon icon = new ImageIcon(img);
		return new JLabel(icon, JLabel.CENTER);
	}
	
	public void setDisplay() {
		pnlNorth = new JPanel();
		JPanel pnlSouth = new JPanel();
		pnlCenter = new JPanel(new GridLayout(0,1));
		
		pnlNorth.add(lblImage);
		pnlNorth.add(lblCount);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		buy();
		pack();
		
		
		
		
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
		num = DataCenter.getInstance().getBuyNum();
		for(int i=0 ; i < num ; i++) {
			number++;
			allPanel = new AllPanel();
			arrPanel.add(allPanel);
			pnlCenter.add(arrPanel.get(i));
			allPanel.getLblNumber().setText(number+"");
		}
		
	}
	
	public void TableAdd() {
		int plusNum = DataCenter.getInstance().getBuyNum() +1 ;
		if(plusNum <=10) {
			DataCenter.getInstance().setBuyNum(plusNum);
			AllPanel allPanel = new AllPanel();
			pnlCenter.add(allPanel, BorderLayout.SOUTH);
		}
	}
	

	public void addListener() {
/*		
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnAdd == e.getSource()) {
					TableAdd();
					pack();
				}
				if(btnCancel == e.getSource()) {
					removeAll();
					pack();
				}

			}
		};
		btnAdd.addActionListener(aListener);
		btnCancel.addActionListener(aListener);
		*/
	}
	
	
    public void showFrame() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
   
}
