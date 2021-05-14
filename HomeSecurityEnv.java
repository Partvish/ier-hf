// Environment code for project homesec.mas2j



import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.architecture.*;
import jason.asSemantics.ActionExec;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;

import java.util.logging.*;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.DefaultListModel;
import javax.swing.JList; 
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomeSecurityEnv extends Environment {
    private Logger logger = Logger.getLogger("homesec.mas2j."+HomeSecurityEnv.class.getName());
	private HomeSecurityGui gui;
	
	public static String TEXT= SelectorOption.SensorInside;
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
		gui = new HomeSecurityGui(getOkListener(), getPoliceListener());
		gui.initComponents();
    }
	
	
	private void clean(){
		clearPercepts("robber");
		clearPercepts("sensor2");
		clearPercepts("sensor1");
		clearPercepts("alarm");
		clearPercepts("central");
		clearPercepts("latches");
	}


    @Override
    public boolean executeAction(String agName, Structure action) {

        logger.info(TEXT);

        if (action.equals(Literal.parseLiteral("robber_done"))) { // you may improve this condition
			gui.getButton().setEnabled(true);
			return true;
        }
		if(action.equals(Literal.parseLiteral("wait_for_police"))){
			gui.getPoliceButton().setEnabled(true);
			return true;
		}
		if(action.equals(Literal.parseLiteral("police_round_done"))){
			gui.getPoliceButton().setEnabled(false);
			return true;
		}

        return true; // the action was executed with success
    }



    /** Called before the end of MAS execution */

    @Override

    public void stop() {

        super.stop();

    }
	
	public ActionListener getOkListener(){
		return new ActionListener() {public void actionPerformed(ActionEvent e) {
                clean();
                Literal goal = ASSyntax.createLiteral("restart");
                addPercept("robber", goal);
				String s="";
				switch(TEXT){
					case SelectorOption.SensorInside:
						s = "trigger_sensor2";
						break;
					case SelectorOption.SensorOutside:
						s= "trigger_sensor1";
						break;
					default:
						s= "trigger_sensor1";
						break;
				}
				addPercept("robber", Literal.parseLiteral(s));
				addPercept("sensor1", Literal.parseLiteral("reset"));
				addPercept("sensor2", Literal.parseLiteral("reset"));
				gui.getButton().setEnabled(false);
            }
		};
		
	}
	
	public ActionListener getPoliceListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					addPercept("robber", Literal.parseLiteral("caught"));
					gui.getPoliceButton().setEnabled(false);
			}
		};
	}
}

class SelectorOption{
	public static final String SensorOutside = "Tresspassing";
	public static final String SensorInside = "Breaking and Entering";
}

class HomeSecurityGui extends JFrame{
	private ActionListener okActionListener;
	private ActionListener policeActionListener;
	
    	public HomeSecurityGui(ActionListener a, ActionListener p){
    		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		setSize(new Dimension(300, 300));
    		setResizable(false);
			okActionListener = a;
			policeActionListener = p;
    	}
    	FlowLayout layout;
		
        JButton bOk;
		JButton bPolice;
		
		JList<String> optionsList;
    	public void initComponents() {                                                                               
        	layout = new FlowLayout();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			listModel.addElement(SelectorOption.SensorOutside);
			listModel.addElement(SelectorOption.SensorInside);
			
			optionsList = new JList<>(listModel);
			//optionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			optionsList.addListSelectionListener(new ListSelectionListener() {
			@Override
    		public void valueChanged(ListSelectionEvent e)
			{
				if(!e.getValueIsAdjusting()) {
					final List<String> selectedValuesList = optionsList.getSelectedValuesList();
						HomeSecurityEnv.TEXT = selectedValuesList.get(0);
					}
				}
			});
    	    this.setLayout(layout);
    	    
    	    bOk =  new JButton("Reset");
    	    bOk.addActionListener(okActionListener);
			bOk.setEnabled(false);
			bPolice = new JButton("Police arrived");
			bPolice.setEnabled(false);
			bPolice.addActionListener(policeActionListener);
    	    optionsList.setPreferredSize(new Dimension(280,100));
			this.add(optionsList);
    	    this.add(bOk);
			this.add(bPolice);
            setVisible(true);
            repaint();
        }
		
		public JButton getButton(){
			return bOk;
		}
		public JButton getPoliceButton(){
			return bPolice;
		}
    }


