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

public class TriangleSolver extends JFrame implements ActionListener {
	//Static Variables
	private static final long serialVersionUID = -4198600644269193361L;
	private static final int frameH=400, frameW=600;
	private static final String displayString[]= {"Side A=", "Angle A=", "Side B=","Angle B=","Side C=","Angle C="}, 
			DEFIN="0", TITLE="Triangle Solver", VERID="Pre-Alpha 0.1";
	//Swing/UI Variables
	private JTextArea[] inputs = new JTextArea[6], displays=new JTextArea[6];
	private JButton reset=new JButton("Reset");
	private JPanel pane=new JPanel(), spacers[]=new JPanel[23];
	private GridLayout mainLay=new GridLayout(5,7,10,10);
	private KeyListener kL=new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			//System.out.println("Typed: "+e.getKeyChar());
		}//keyTyped
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("Pressed: "+e.getKeyChar());
		}//keyPressed
		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("Released: "+e.getKeyChar());
			if(intCheck(e.getKeyChar())) {
				for(int i=0;i<inputs.length;i++) {
					inputBool[i]=intCheck(inputs[i].getText());
				}//for
				if(logicRunnable()&&!logicRan) {
					runLogic();
				}//if
			}//if
		}//keyReleased
	};//KeyListener dec
	//Functional Variables
	private boolean inputBool[]= {false,false,false,false,false,false}, logicRan=false;

	//Functional Methods
	TriangleSolver(){
		setSize(frameW,frameH);
		setLocation((1920-frameW)/2,(1080-frameH)/2);
		setTitle(TITLE+", "+VERID);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(kL);

		pane.setLayout(mainLay);

		for(int i=0;i<inputs.length;i++) {
			inputs[i]=new JTextArea();
			inputs[i].setText(DEFIN);
			inputs[i].addKeyListener(kL);
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
		reset.addActionListener(this);
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
		for(int i=15;i<21;i++)
			pane.add(spacers[i]);
		pane.add(reset);
		pane.add(spacers[21]);

		add(pane);
		setVisible(true);
	}//init
	private void runLogic() {
		for(JTextArea i:inputs)
			i.setEditable(false);
		logicRan=true;
	}//runLogic
	private void reset() {
		logicRan=false;
		for(int i=0;i<inputs.length;i++) {
			inputs[i].setText("0");
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
	private boolean intCheck(String checkString) {
		boolean returnValue=false;
		if(checkString.length()==0||(checkString.length()==1&&checkString.contains("0"))) {
			return returnValue;
		}//if
		for(int i=0;i<checkString.length();i++) {
			switch(checkString.charAt(i)) {
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
				return false;
			}//switch
		}//for
		return returnValue;
	}//intCheck(String)
	private boolean intCheck(char checkChar) {
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
	private boolean logicRunnable() {
		boolean returnValue;
		int trueCount=0;
		for(boolean i:inputBool)
			if(i)
				trueCount++;
		if(trueCount==3&&!(inputBool[3]&&inputBool[4]&&inputBool[5])) {
			returnValue=true;
		} else if (inputBool[3]&&inputBool[4]&&inputBool[5]) {
			double inputNums[]= {Double.parseDouble(inputs[3].getText()),Double.parseDouble(inputs[4].getText()),Double.parseDouble(inputs[5].getText())};
			if(inputNums[0]+inputNums[1]+inputNums[2]==180) {
				returnValue=true;
			} else {
				returnValue=false;
				System.err.println("Angles do not equal 180 degrees.");
			}
		} else if (inputBool[0]&&inputBool[1]&&inputBool[2]) {
			double inputNums[]= {Double.parseDouble(inputs[0].getText()),Double.parseDouble(inputs[1].getText()),Double.parseDouble(inputs[2].getText())};
			double sortedNums[]=sort(inputNums);
			if(sortedNums[2]>sortedNums[0]+sortedNums[1]) {
				returnValue=true;
			} else {
				returnValue=false;
				System.err.println("Given side lengths cannot form a triange.");
			}
		} else {
			returnValue=false;
		}
		return returnValue;
	}//logicRunnable

	//Inherited Methods
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==reset) {
			reset();
		}//if
	}//actionPerformed
}//class
