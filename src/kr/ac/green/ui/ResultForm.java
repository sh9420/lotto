package kr.ac.green.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import kr.ac.green.data.DataCenter;
import kr.ac.green.ui.component.MyLottoPanel;
import kr.ac.green.ui.component.WinLottoPanel;


public class ResultForm extends JFrame {
	private JLabel lblWinText;		//추첨 번호 텍스트 "추첨 번호"
	private JLabel[] lblWinNum;	 	//추첨번호
	private JLabel lblMyLottoInfo;	//구매한 로또 정보 "구매 번호"
	private JLabel lblWinNumInfo;	//당첨 번호 정보 "당첨 번호"
	private JLabel lblRankInfo;		//등수 "등수"	
	private JLabel plusLogo;		//추첨 번호 + 이미지
	
	private ArrayList<Integer> resultList = new ArrayList<Integer>();
	private ArrayList<Integer> myLottoList = new ArrayList<Integer>();
	private ArrayList<ArrayList> winNumList = new ArrayList<ArrayList>();
	
	
	private String rank;

	private JButton btnTracking;	//추적 버튼
	private JButton btnMain;		//메인으로 돌아가는 버튼

	private String imageSet;		//숫자에 따른 이미지 세팅
	
	private Dimension sizeOf;
	private DataCenter dataCenter;

	public ResultForm() {
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	public void init() {
		sizeOf = new Dimension(300, 30);
		this.dataCenter=DataCenter.getInstance();
		// pnlNorth.add
		lblWinText = new JLabel("추첨 번호");
		lblWinNum = new JLabel[7];
		
		lblMyLottoInfo = getLabel("구매 번호");
		lblWinNumInfo = getLabel("당첨 번호");
		lblRankInfo = getLabel("등수");
	
		btnTracking = new JButton("추적");
		btnMain = new JButton("메인화면");
		
		int[] result = DataCenter.getInstance().resultAuto();
		
		for(int temp : result) {
			resultList.add(temp);
		}
		
		for(int i = 0 ; i < dataCenter.getLottoList().size() ; i++) {
			for(int temp : dataCenter.getLottoList().get(i).getLottoNumber()) {
				myLottoList.add(temp);
			}
			myLottoList.retainAll(resultList.subList(0, 6));
			winNumList.add(myLottoList);
			myLottoList = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < lblWinNum.length; i++) {
			if(result[i] == 0) {
				imageSet = "f";
			}else if(result[i] < 10) {
				imageSet = "a";
			}else if(result[i] < 20) {
				imageSet = "b";
			}else if(result[i] < 30) {
				imageSet = "c";
			}else if(result[i] < 40) {
				imageSet = "d";
			}else {
				imageSet = "e";
			}
			ImageIcon icon = new ImageIcon(imageSet + ".png");
			Image img = icon.getImage();
			Image newImage = img.getScaledInstance(25,25,Image.SCALE_SMOOTH);
			lblWinNum[i] = new JLabel(String.valueOf(result[i]), new ImageIcon(newImage), JLabel.CENTER);
			lblWinNum[i].setHorizontalTextPosition(JLabel.CENTER);
			lblWinNum[i].setPreferredSize(new Dimension(27,27));
			lblWinNum[i].setForeground(Color.WHITE);
		}
		plusLogo = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("plus.png")));
	
		dataCenter.getLottoList().get(0);
	}
	
	
	public void setDisplay() {
		JPanel pnlWinNum = getPanel();
		JPanel pnlNorth = getPanel();
		pnlNorth.add(lblWinText);

		for(int i = 0; i < lblWinNum.length - 1; i++) {
			pnlNorth.add(lblWinNum[i]);
		}
		pnlNorth.add(plusLogo);
		pnlNorth.add(lblWinNum[lblWinNum.length - 1]);

		JPanel pnlCenter = getPanel();
		pnlCenter.setBorder(new LineBorder(Color.BLACK));
		JPanel pnlCWest = getPanel();
		pnlCWest.setLayout(new GridLayout(0,1));
		JPanel pnlCCenter = getPanel();
		pnlCCenter.setLayout(new GridLayout(0,1));
		JPanel pnlCEast = getPanel();
		pnlCEast.setLayout(new GridLayout(0,1));
		JPanel pnlSouth = getPanel();
		pnlSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlNorth.add(pnlWinNum);		

		pnlCWest.add(lblMyLottoInfo); 	
		pnlCCenter.add(lblWinNumInfo);	
		pnlCEast.add(lblRankInfo);		
		
		//내가 구매한 목록 띄우기
		for(int idx=0; idx < dataCenter.getLottoList().size();idx++) {			
			JPanel lblMyLotto = getPanel();					
			lblMyLotto.add(new MyLottoPanel(idx));
			lblMyLotto.setPreferredSize(new Dimension(300,50));
			
			JPanel lblWinNumber = getPanel();			
			lblWinNumber.add(new WinLottoPanel(idx, this));
			lblWinNumber.setPreferredSize(new Dimension(300,50));
			
			JPanel lblRank = getPanel();					
			lblRank.setPreferredSize(new Dimension(300,50));
			JPanel lbl1 = getPanel();
			JPanel lbl2 = getPanel();
			lbl1.add(new JLabel(rankCheck(rank)));
			lbl2.add(lbl1);
			lblRank.add(lbl2);
			
			pnlCWest.add(lblMyLotto);
			pnlCCenter.add(lblWinNumber);
			pnlCEast.add(lblRank);
		}
		
		pnlCenter.add(pnlCWest, BorderLayout.WEST);
		pnlCenter.add(pnlCCenter, BorderLayout.CENTER);
		pnlCenter.add(pnlCEast, BorderLayout.EAST);

		pnlSouth.add(btnTracking);	
		pnlSouth.add(btnMain);		

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

	public String rankCheck(String rank) {
		rank = "낙첨되셨습니다..";
		if(winNumList.size() == 6) {
			rank = "1등 당첨";
		}else if(winNumList.size() == 5) {
			rank = "2등 당첨";
		}else if(winNumList.size() == 4) {
			rank = "4등 당첨";
		}else if(winNumList.size() == 5) {
			rank = "5등 당첨";
		}
		return rank;
	}
	
	public void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int exit = JOptionPane.showConfirmDialog(null, "종료할까요?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(exit == JOptionPane.YES_OPTION){
					//사용자가 yes를 눌렀을 떄
					setDefaultCloseOperation(EXIT_ON_CLOSE);
				} else{
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		
		ActionListener aListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnTracking == e.getSource()) {

				}
				else if(btnMain == e.getSource()) {

				}
			}
		};
		btnTracking.addActionListener(aListener);
		btnMain.addActionListener(aListener);
	}
	
	public void showFrame() {
		setTitle("RESULT");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public ArrayList<ArrayList> getWinNumList() {
		return winNumList;
	}

	public void setWinNumList(ArrayList<ArrayList> winNumList) {
		this.winNumList = winNumList;
	}
	
}