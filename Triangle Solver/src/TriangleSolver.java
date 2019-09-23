import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
			DEFAULTINPUT="", TITLE="Triangle Solver", VERSIONID="Pre-Alpha 0.1";
	//Swing/UI Variables
	private JTextArea[] inputs = new JTextArea[6], displays=new JTextArea[6];
	private JButton reset=new JButton("Reset"), enter=new JButton("Run");
	private JPanel pane=new JPanel(), spacers[]=new JPanel[21];
	private GridLayout mainLay=new GridLayout(5,7,0,10);

	//Functional Variables
	private boolean inputBool[]= {false,false,false,false,false,false}, logicRan=false;

	//Functional Methods
	TriangleSolver(){
		//Sets up the frame and layout of how the GUI looks.
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(frameW,frameH);
		setLocation(((int)screenSize.getWidth()-frameW)/2,((int)screenSize.getHeight()-frameH)/2); //Sets the frame to appear centered in the screen.
		setTitle(TITLE+", "+VERSIONID);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pane.setLayout(mainLay);

		for(int i=0;i<inputs.length;i++) {
			inputs[i]=new JTextArea();
			inputs[i].setText(DEFAULTINPUT);
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
		//Will run the logic for solving the Triangle.
		//Assigns all inputs to appropriate variable lists.
		double[] inputSides=new double[3], inputAngles=new double[3], outputSides=new double[3], outputAngles=new double[3];
		for(int i=0, a=0;i<inputs.length;i+=2, a++) {
			if(inputBool[i]) {
				inputSides[a]=Double.parseDouble(inputs[i].getText());
			} else {
				inputSides[a]=0;
			}
		}//for
		for(int i=1, a=0;i<inputs.length;i+=2, a++) {
			if(inputBool[i]) {
				inputAngles[a]=Double.parseDouble(inputs[i].getText());
			} else {
				inputAngles[a]=0;
			}
		}//for
		boolean a1=inputBool[1], a2=inputBool[3], a3=inputBool[5], s1=inputBool[0], s2=inputBool[2], s3=inputBool[4];
		boolean aA=a1&&a2&&a3, aS=s1&&s2&&s3; //All Angles, All Sides.
		boolean a1a2=a1&&a2, a1a3=a1&&a3, a2a3=a2&&a3, s1s2=s1&&s2, s1s3=s1&&s3, s2s3=s2&&s3; //Angle Pairs, Side Pairs
		boolean al2A=a1a2||a1a3||a2a3, al2S=a1a2||a1a3||a2a3; //At Least 2 Angles, At Least 2 Sides.
		boolean as1=a1&&s1, as2=a2&&s2, as3=a3&&s3;
		boolean solved=aA&&aS;
		do {
			//Easy Cases
			if(!aA&&al2A) {
				if(a1a2) {
					outputAngles[0]=inputAngles[0];
					outputAngles[1]=inputAngles[1];
					outputAngles[2]=180-(outputAngles[0]+outputAngles[1]);
				} else if(a1a3) {
					outputAngles[0]=inputAngles[0];
					outputAngles[2]=inputAngles[2];
					outputAngles[1]=180-(outputAngles[0]+outputAngles[2]);
				} else if(a2a3) {
					outputAngles[1]=inputAngles[1];
					outputAngles[2]=inputAngles[2];
					outputAngles[0]=180-(outputAngles[1]+outputAngles[2]);
				}
				a1=a2=a3=aA=true;
			}//At least 2 Angles Case 
			else if (aA) {
				for(int i=0;i<inputAngles.length;i++) {
					outputAngles[i]=inputAngles[i];
				}
			}//All Angles Case
			if (aS) {
				for(int i=0;i<inputSides.length;i++) {
					outputSides[i]=inputSides[i];
				}
			}//All Sides Case
			//Not so Easy Cases
			//TODO Help.
			//Updating Appropriate Bools
			aA=a1&&a2&&a3; 
			aS=s1&&s2&&s3;
			solved=aA&&aS;
			print(aA+" "+outputAngles[0]+" "+outputAngles[1]+" "+outputAngles[2]);
			print(aS+" "+outputSides[0]+" "+outputSides[1]+" "+outputSides[2]);
			solved=true;
		} while(!solved);
	}//runLogic
	private void reset() {
		//Resets the window to where you can input a new triangle.
		//TODO Fix this.
		logicRan=false;
		for(int i=0;i<inputs.length;i++) {
			inputs[i].setText(DEFAULTINPUT);
			inputBool[i]=false;
			inputs[i].setEditable(true);
		}//for
	}//reset
	private double[] sort(double[]inputNums) {
		//Sorts a list of Doubles from least to greatest.
		ArrayList<Double> outputNums=new ArrayList<Double>();
		for(double i:inputNums)
			outputNums.add(i);
		outputNums.sort(Comparator.naturalOrder());
		double[] returnNums={outputNums.get(0),outputNums.get(1),outputNums.get(2)};
		return returnNums;
	}
	private <T> void print(T inT){
		System.out.println(inT);
	}//print
	private <T> void err(T inT) {
		System.err.println(inT);
	}
	public static void main(String[]args) {
		new TriangleSolver();
	}//main

	//Variable Methods
	private boolean intCheck(char checkChar, boolean isString) {
		//Checks a char to see if its a valid integer.
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
		case '.':
			if(isString)
				returnValue=true;
			else
				returnValue=false;
			break;
		default:
			returnValue=false;
		}//switch
		return returnValue;
	}//intCheck(char)
	private boolean intCheck(String checkString) {
		//Checks a string one char at a time using the prior intCheck method to see if the number in the input is a valid number.
		boolean returnValue=false;
		if(checkString.length()==0) {
			return returnValue;
		}//if
		for(int i=0;i<checkString.length();i++) {
			if(intCheck(checkString.charAt(i), true)) {
				returnValue=true;
			} else {
				returnValue=false;
				break;
			}
		}//for
		return returnValue;
	}//intCheck(String)
	private boolean logicRunnable() {
		//Checks to see if their is enough inputs and if those inputs are able to produce a valid triangle.
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
		//Listens for Button Presses
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