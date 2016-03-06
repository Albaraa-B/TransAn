import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static ArrayList<JPanel> trans = new ArrayList<JPanel>();
	
	 //GUI Definitions
	static JPanel main_panel = new JPanel();
	JButton read_button = new JButton("Read my transcript");
	JPanel reading_panel = new JPanel();
	JLabel gpa_lbl = new JLabel();
	JLabel sem_lbl = new JLabel();
	JPanel row_one = new JPanel();
	JLabel gpa_col_lbl = new JLabel("GPA");
	JLabel cgpa_col_lbl = new JLabel("CGPA");
	JLabel sem_col_lbl = new JLabel("Semester");
	JLabel change_col_lbl = new JLabel("Change %");
	JMenuBar mb = new JMenuBar();
	JMenuItem gen_graph = new JMenuItem("Generate Graph");
	JMenuItem about = new JMenuItem("About");
	
	JPanel change_panel = new JPanel();
	
	static JFrame g_frame = new JFrame();
	static JPanel g_panel = new JPanel();
	
	
	
	
	public GUI(){
		super("TransAn");
		mb.add(gen_graph);
		mb.add(about);
		about.addActionListener(menu_lis);
		gen_graph.addActionListener(menu_lis);
		setJMenuBar(mb);
		
		reading_panel.setLayout(new GridLayout(0,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		read_button.addActionListener(button_lis);
		add(read_button, BorderLayout.SOUTH);
		sem_col_lbl.setBorder(new LineBorder(Color.BLACK,1));
		gpa_col_lbl.setBorder(new LineBorder(Color.BLACK,1));
		cgpa_col_lbl.setBorder(new LineBorder(Color.BLACK,1));
		change_col_lbl.setBorder(new LineBorder(Color.BLACK,1));
		row_one.add(sem_col_lbl);
		row_one.add(gpa_col_lbl);
		row_one.add(cgpa_col_lbl);
		row_one.add(change_col_lbl);
		reading_panel.add(row_one);
		add(reading_panel);
		
		pack();
		setVisible(true);
		
		
	}
	
	
	ActionListener button_lis = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == read_button){
				
				TransAn.readTranscript();
				reading_panel.removeAll();
				reading_panel.add(row_one);
				for(JPanel t : trans){
					reading_panel.add(t);
				}
				reading_panel.updateUI();
				GUI.this.pack();
				
			}
		}

		
	};
	
	ActionListener menu_lis = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == gen_graph){
				genGraph();
			}else if(e.getSource() == about){
				JOptionPane.showMessageDialog(null, String.format("Ideas by : Mohammad Ameen Fari%nDeveloped by: Albaraa Ayman Bajnaid"),"About TransAn", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};
	
	
	
	
	public void genGraph(){
		if(trans.size() > 0){
			
			
			
				
				
				
			
			try {
				new Graph();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
				
			}else
				JOptionPane.showMessageDialog(null,"Need to read First");
			
		
	}
}
	
	
		
		/*public void paint(Graphics g){
			super.paint(g);
			g.drawLine(50, 100, 50, g_panel.getHeight() - 100);
			g.drawLine(50, g_panel.getHeight() - 100, g_panel.getWidth() - 50, g_panel.getHeight() - 100 );
			
			int offset = 100;
			for( JPanel j : trans){
				String sem = ((JLabel) j.getComponent(0)).getText();
				double cgpa = Double.parseDouble(((JLabel) j.getComponent(1)).getText());
				if(cgpa >= 3.0  && cgpa <= 4.0){
					g.setColor(Color.GREEN);
					g.drawRect(offset, g_panel.getHeight() - 100 , 50 ,(int) cgpa * -100);
				}
				if(cgpa >= 2.0 && cgpa < 3.0){
					g.setColor(Color.YELLOW);
					g.drawRect(offset, g_panel.getHeight() - 100 , 50 ,(int) cgpa * -100);
				}
				if(cgpa >= 1.0 && cgpa < 2.0){
					g.setColor(Color.RED);
					g.drawRect(offset, g_panel.getHeight() - 100 , 50 ,(int) cgpa * -100);
				}
				if(cgpa >= 0.0 && cgpa < 1.0){
					g.setColor(Color.BLACK);
					g.drawRect(offset, g_panel.getHeight() - 100 , 50 ,(int) cgpa * -100);
				}
				
				offset += 100;	
				
				
			}
		
	}*/
	
	

	


