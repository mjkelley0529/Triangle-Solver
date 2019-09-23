import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TriangleSolver extends JFrame implements ActionListener, KeyListener {
	//Static Variables
	private static final long serialVersionUID = -4198600644269193361L;
	private static final int frameH=400, frameW=600;
	private static final String displayString[]= {"Side A=", "Angle A=", "Side B=","Angle B=","Side C=","Angle C="}, 
			DEFIN="", TITLE="Triangle Solver", VERID="Pre-Alpha 0.1";
	//Swing/UI Variables
	private JTextArea[] inputs = new JTextArea[6], displays=new JTextArea[6];
	private JButton reset=new JButton("Reset"), enter=new JButton("Run");
	private JPanel pane=new JPanel(), spacers[]=new JPanel[21];
	private GridLayout mainLay=new GridLayout(5,7,0,10);

	//Functional Variables
	private boolean inputBool[]= {false,false,false,false,false,false}, logicRan=false;

	//Functional Methods
	TriangleSolver(){
		setSize(frameW,frameH);
		setLocation((1920-frameW)/2,(1080-frameH)/2);
		setTitle(TITLE+", "+VERID);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pane.setLayout(mainLay);

		for(int i=0;i<inputs.length;i++) {
			inputs[i]=new JTextArea();
			inputs[i].setText(DEFIN);
			inputs[i].addKeyListener(this);
		}
		for(int i=0;i<displays.length;i++) {
			displays[i]=new JTextArea();
			displays[i].setText(displayString[i]);
			displays[i].setEditable(false);
			displays[i].setFocusable(false);
		}
		for(int i=0;i<spacers.length;i++) {
			spacers[i]=new JPanel();
			spacers[i].setFocusable(false);
		}
		enter.addActionListener(this);
		enter.addKeyListener(this);
		reset.addActionListener(this);
		reset.addKeyListener(this);
		for(int i=0;i<8;i++)
			pane.add(spacers[i]);
		pane.add(displays[0]);
		pane.add(inputs[0]);
		pane.add(spacers[8]);
		pane.add(displays[1]);
		pane.add(inputs[1]);
		for(int i=9;i<11;i++)
			pane.add(spacers[i]);
		pane.add(displays[2]);
		pane.add(inputs[2]);
		pane.add(spacers[11]);
		pane.add(displays[3]);
		pane.add(inputs[3]);
		for(int i=12;i<14;i++)
			pane.add(spacers[i]);
		pane.add(displays[4]);
		pane.add(inputs[4]);
		pane.add(spacers[14]);
		pane.add(displays[5]);
		pane.add(inputs[5]);
		for(int i=15;i<20;i++)
			pane.add(spacers[i]);
		pane.add(enter);
		pane.add(reset);
		pane.add(spacers[20]);

		add(pane);
		setVisible(true);
	}//init
	private void runLogic() {
		/*for(JTextArea i:inputs)
			i.setEditable(false);
		logicRan=true;*/
	}//runLogic
	private void reset() {
		logicRan=false;
		for(int i=0;i<inputs.length;i++) {
			inputs[i].setText(DEFIN);
			inputBool[i]=false;
			inputs[i].setEditable(true);
		}//for
	}//reset
	private double[] sort(double[]inputNums) {
		ArrayList<Double> outputNums=new ArrayList<Double>();
		for(double i:inputNums)
			outputNums.add(i);
		outputNums.sort(Comparator.naturalOrder());
		double[] returnNums={outputNums.get(0),outputNums.get(1),outputNums.get(2)};
		return returnNums;
	}
	public static void main(String[]args) {
		new TriangleSolver();
	}//main

	//Variable Methods
	private boolean intCheck(char checkChar) {
		System.err.println(checkChar);
		boolean returnValue=false;
		switch(checkChar) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '0':
			returnValue=true;
			break;
		default:
			returnValue=false;
		}//switch
		return returnValue;
	}//intCheck(char)
	private boolean intCheck(String checkString) {
		boolean returnValue=false;
		if(checkString.length()==0) {
			return returnValue;
		}//if
		for(int i=0;i<checkString.length();i++) {
			if(intCheck(checkString.charAt(i))) {
				returnValue=true;
			} else {
				returnValue=false;
				break;
			}
		}//for
		return returnValue;
	}//intCheck(String)
	private boolean logicRunnable() {
		boolean returnValue;
		int trueCount=0;
		for(boolean i:inputBool) {
			System.err.println(i);
			if(i)
				trueCount++;
		}
		boolean A3=inputBool[1]&&inputBool[3]&&inputBool[5], S3=inputBool[0]&&inputBool[2]&&inputBool[4];
		if(!A3&&!S3&&trueCount>=3) {
			returnValue=true;
			System.err.println("Case 1 True");
		} else if (A3) {
			double inputNums[]= {Double.parseDouble(inputs[1].getText()),Double.parseDouble(inputs[3].getText()),Double.parseDouble(inputs[5].getText())};
			System.err.println("Case 2");
			if(inputNums[0]+inputNums[1]+inputNums[2]==180) {
				returnValue=true;
				System.err.println("Case 2 True");
			} else {
				returnValue=false;
				System.err.println("Case 2 False: Angles do not equal 180 degrees.");
			}
		} else if (S3) {
			System.err.println("Case 3");
			double inputNums[]= {Double.parseDouble(inputs[0].getText()),Double.parseDouble(inputs[2].getText()),Double.parseDouble(inputs[4].getText())};
			double sortedNums[]=sort(inputNums);
			if(sortedNums[2]<=sortedNums[0]+sortedNums[1]) {
				System.err.println("Case 3 True");
				returnValue=true;
			} else {
				returnValue=false;
				System.err.println("Case 3 False: Given side lengths cannot form a triange.");
			}
		} else {
			System.err.println("Case 1 False");
			returnValue=false;
		}
		return returnValue;
	}//logicRunnable

	//Inherited Methods
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==reset) {
			reset();
		}
		if(evt.getSource()==enter&&logicRunnable()&&!logicRan) {
			runLogic();
		}
	}//actionPerformed
	@Override
	public void keyTyped(KeyEvent e) {
	}//keyTyped
	@Override
	public void keyPressed(KeyEvent e) {
	}//keyPressed
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER&&logicRunnable()&&!logicRan) {
				enter.doClick();
		} else {
			for(int i=0;i<inputs.length;i++) {
				System.err.println("\""+inputs[i].getText()+"\"");
				inputBool[i]=intCheck(inputs[i].getText().toLowerCase());
			}//for
		}//if
	}//keyReleased
}//class
