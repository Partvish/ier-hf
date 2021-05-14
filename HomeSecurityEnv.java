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
	private HomeSecurityArch arch;
	
	public static String TEXT= "Tresspassing";
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
		arch = new HomeSecurityArch();
		gui = new HomeSecurityGui(getListener());
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

        logger.info("executing: "+action+", but not implemented!");

        if (action.equals(Literal.parseLiteral("robber_caught"))) { // you may improve this condition
			gui.getButton().setEnabled(true);
			return true;
        }

        return false; // the action was executed with success
    }



    /** Called before the end of MAS execution */

    @Override

    public void stop() {

        super.stop();

    }
	
		public ActionListener getListener(){
		return new ActionListener() {public void actionPerformed(ActionEvent e) {
                clean();
                Literal goal = ASSyntax.createLiteral("restart");
                addPercept("robber", goal);
				logger.info(TEXT);
				gui.getButton().setEnabled(false);
            }
		};
	}
}


class HomeSecurityGui extends JFrame{
	private ActionListener actionListener;
    	public HomeSecurityGui(ActionListener a){
    		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		setSize(new Dimension(300, 300));
    		setResizable(false);
			actionListener = a;
    	}
    	FlowLayout layout;
        JButton ok;
        JTextField ctext;
        JTextField ptext;
		JList<String> optionsList;
    	public void initComponents() {
        	layout = new FlowLayout();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			listModel.addElement("Tresspassing");
			listModel.addElement("Breaking and entering");
			
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
    	    
    	    ok =  new JButton("next step");
    	    ok.addActionListener(actionListener);
			ok.setEnabled(false);
    	    optionsList.setPreferredSize(new Dimension(280,100));
			this.add(optionsList);
    	    this.add(ok);
			
            setVisible(true);
            repaint();
        }
		
		public JButton getButton(){
			return ok;
		}
    }


