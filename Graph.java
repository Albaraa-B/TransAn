import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private JPanel g_panel = new JPanel();
	//private JFrame g_frame = new JFrame();
	
	
	/**
	 * 
	 */
	

	public Graph(){
		setBackground(Color.WHITE);
		setSize(1500, 600);
		setResizable(false);
		//setSize(getPreferredSize());
		repaint();
		setVisible(true);
		//g_frame.setBackground(Color.DARK_GRAY);
		//g_panel.setSize(1000, 600);
		//g_panel.repaint();
		//g_frame.add(g_panel);
		//g_frame.setSize(1000, 600);
		//g_frame.repaint();
		//g_frame.pack();
		//g_frame.setVisible(true);
		
		
		}
	
	/*public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawLine(50, 100, 50, g_panel.getHeight() - 100);
		g.drawLine(50, g_panel.getHeight() - 100, g_panel.getWidth() - 50, g_panel.getHeight() - 100 );
		
		int offset = 100;
		for( JPanel j : GUI.trans){
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
		
		public void paint(Graphics g){
			//super.paint(g);
			g.setColor(Color.BLACK);
			g.fillRect(this.getWidth() / 2 - 40 , 30 , 10, 10 );
			g.drawString("CGPA", this.getWidth() / 2 - 40 + 15, 40);
			g.setColor(Color.GREEN);
			g.fillRect(this.getWidth() / 2 + 40 , 30 , 10, 10 );
			g.setColor(Color.BLACK);
			g.drawString("GPA", this.getWidth() / 2 + 40 + 15, 40);
			
			Graphics2D g2d = (Graphics2D) g.create();

	        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	        g2d.setStroke(dashed);
	        g2d.setColor(Color.GREEN);
	        g2d.drawLine(this.getWidth() / 2 + 100, 35, this.getWidth() / 2 + 130 , 35);
	        g2d.dispose();
	        g.setColor(Color.BLACK);
	        g.drawString("W / NA",this.getWidth() / 2 + 135 , 40);
			
			g.drawLine(50, 100, 50, this.getHeight() - 100);
			g.drawLine(50, this.getHeight() - 100, this.getWidth() - 50, this.getHeight() - 100 );
			
			int voffset = 100;
			for(double i = 4.00; i >= 0 ; i -= 0.25, voffset += 25){
				g.drawString(""+i,10, voffset );
				
			}
			
			int offset = 100;
			for( JPanel j : GUI.trans){
				g.setColor(Color.BLACK);
				String sem = ((JLabel) j.getComponent(0)).getText().trim();
				//String semi = sem.substring(0, sem.indexOf(" "));
				//semi = semi.concat(sem.substring(sem.lastIndexOf(" ")));
				//sem = semi;
				System.out.println(sem);
				
				double cgpa = Double.parseDouble(((JLabel) j.getComponent(1)).getText());
				g.drawString(sem, offset, this.getHeight() - 50);
				if(cgpa >= 3.0  && cgpa <= 4.0){
					int yloc = this.getHeight() - 100 + ((int) Math.round(cgpa * -100));
					g.drawString(""+cgpa, offset,yloc);
					g.setColor(Color.GREEN);
					//g.drawRect(offset, this.getHeight() - 100 , 50 ,(int) Math.round(cgpa * -100));
				}
				if(cgpa >= 2.0 && cgpa < 3.0){
					int yloc = this.getHeight() - 100 + ((int) Math.round(cgpa * -100));
					g.drawString(""+cgpa, offset,yloc );
					g.setColor(Color.YELLOW);
					//g.drawRect(offset, this.getHeight() - 100 , 50 ,(int) Math.round(cgpa * -100));
				}
				if(cgpa >= 1.0 && cgpa < 2.0){
					int yloc = this.getHeight() - 100 + ((int) Math.round(cgpa * -100));
					g.drawString(""+cgpa, offset,yloc );
					g.setColor(Color.RED);
					//g.drawRect(offset, this.getHeight() - 100 , 50 ,(int) Math.round(cgpa * -100));
				}
				if(cgpa > 0.0 && cgpa < 1.0){
					int yloc = this.getHeight() - 100 + ((int) Math.round(cgpa * -100));
					g.drawString(""+cgpa, offset,yloc );
					g.setColor(Color.BLACK);
					//g.drawRect(offset, this.getHeight() - 100 , 50 ,(int) Math.round(cgpa * -100));
				}
				
				
				offset += 100;	
				
				
			}
			
			
			int loffset = 125;
			for(int i = 0; i < GUI.trans.size() - 1; i++){
				String sem = ((JLabel) GUI.trans.get(i).getComponent(0)).getText().trim();
				//String semi = sem.substring(0, sem.indexOf(" "));
				//semi = semi.concat(sem.substring(sem.lastIndexOf(" ")));
				//sem = semi;
				System.out.println(sem);
				g.setColor(Color.BLACK);
				double gpa1 = Double.parseDouble(((JLabel) GUI.trans.get(i).getComponent(1)).getText());					
				double cgpa1 = Double.parseDouble(((JLabel) GUI.trans.get(i).getComponent(2)).getText());
				double gpa2 = Double.parseDouble(((JLabel) GUI.trans.get(i + 1).getComponent(1)).getText());				
				double cgpa2 = Double.parseDouble(((JLabel) GUI.trans.get(i + 1).getComponent(2)).getText());
				int cyloc1 = this.getHeight() - 100 + ((int) Math.round(cgpa1 * -100));
				int cyloc2 = this.getHeight() - 100 + ((int) Math.round(cgpa2 * -100));
				int yloc1 = this.getHeight() - 100 + ((int) Math.round(gpa1 * -100));
				int yloc2 = this.getHeight() - 100 + ((int) Math.round(gpa2 * -100));
				g.setColor(Color.GREEN);
				if(yloc2 == this.getHeight() - 100){
					gpa2 = Double.parseDouble(((JLabel) GUI.trans.get(i + 2).getComponent(1)).getText());
					yloc2 = this.getHeight() - 100 + ((int) Math.round(gpa2 * -100));
					g2d = (Graphics2D) g.create();

			        dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
			        g2d.setStroke(dashed);
			        g2d.drawLine(loffset,yloc1 , loffset + 200  , yloc2);
			        g2d.dispose();
				        
				}else if(yloc1 == this.getHeight() - 100){
					gpa1 = Double.parseDouble(((JLabel) GUI.trans.get(i - 1).getComponent(1)).getText());
					yloc1 = this.getHeight() - 100 + ((int) Math.round(gpa1 * -100));
					g2d = (Graphics2D) g.create();

			        dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
			        g2d.setStroke(dashed);
			        g2d.drawLine(loffset - 100 ,yloc1 , loffset + 100 , yloc2);
			        g2d.dispose();
				        
				}else
					g.drawLine(loffset,yloc1 , loffset + 100 , yloc2);
				g.setColor(Color.BLACK);
				g.drawLine(loffset,cyloc1 , loffset + 100 , cyloc2);
				loffset += 100;
			}
			this.setSize(offset + 50, 600);
		
	}
	
	}

