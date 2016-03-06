import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TransAn extends GUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String path = "";
	protected static ArrayList<double[]> gpaCalc_list = new ArrayList<double[]>();

	
	public static void main(String[] ar){
		new GUI();
	}
	
	
	public static void readTranscript(){
		if(!new File("settings.txt").exists() ){
			//String msg = "So, I need you to do something for me,%nGo to your transcript, view page source (Ctrl+U in Chrome)%nCopy all, and paste it in notepad,%nWhen you do that Press OK to locate that file";
			JOptionPane.showMessageDialog(null, String.format("So, I need you to do something for me,%nGo to your transcript (Preferrably UnderGrad.)%n, view page source (Ctrl+U in Chrome)%nCopy all, and paste it in notepad,%nWhen you do that Press OK to locate that file"), "Setup", JOptionPane.INFORMATION_MESSAGE);
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int check = fc.showDialog(null, "This File");
			if(check == JFileChooser.APPROVE_OPTION){
				File trans = fc.getSelectedFile();
				path = trans.getPath();
				PrintWriter p;
				try {
					p = new PrintWriter(new FileOutputStream("Settings.txt"));
					p.printf("Path: %s%n",path);
					p.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
				//<TH COLSPAN="12" CLASS="ddlabel" scope="row" ><SPAN class="fieldOrangetextbold"> %s </SPAN></TH>
				//<TH COLSPAN="12" CLASS="ddlabel" scope="row" ><SPAN class="fieldOrangetextbold">Term: %s </SPAN></TH>
			}else{
				try {
					GUI.trans.clear();
					Scanner z = new Scanner(new FileInputStream("settings.txt"));
					path = z.nextLine();
					path = path.substring(path.indexOf(":") + 1 ).trim();
					z.close();
					
					Scanner s = new Scanner(new FileInputStream(path));
					
					double term_quality_hours = 0;
					double term_earned_hours = 0;
					double total_hours = 0;
					double total_quality_hours = 0;
					double cgpa1 = 0.0;
					double cgpa2 = 0.0;
					while(s.hasNextLine()){
						JPanel trans_panel = new JPanel();
						JLabel sem_lbl = new JLabel("");
						JLabel cgpa_lbl = new JLabel("");
						JLabel gpa_lbl = new JLabel("");
						JLabel change_lbl = new JLabel("");
						String line = s.nextLine();
						
						
						
						
						if(line.contains("<TH COLSPAN=\"12\" CLASS=\"ddlabel\" scope=\"row\" ><SPAN class=\"fieldOrangetextbold\">Term:")){
							String sem = line.substring(line.indexOf(":")+ 1, line.indexOf("</SPAN"));
							//System.out.println(sem);
							if(sem.contains("First")){
								sem = sem.substring(sem.lastIndexOf("20")+ 2, sem.indexOf("-")).trim() + "1";
								//System.out.println(sem);
							}else if(sem.contains("Second")){
								sem = sem.substring(sem.lastIndexOf("20")+ 2, sem.indexOf("-")).trim() + "2";
								//System.out.println(sem);
							}else if(sem.contains("Summer")){
								int year = Integer.parseInt(sem.substring(sem.lastIndexOf("20") + 2).trim());
								year--;
								String years = "" + year;
								sem = "" + years.substring(years.indexOf("0") + 1) + 3;
								//System.out.println(sem);
							}
							
							System.out.printf("%s%n", sem);
							sem_lbl.setText(sem+"        ");
							line = s.nextLine();
							String cgpa = "";
							
							while(!line.contains("<TH COLSPAN=\"12\" CLASS=\"ddlabel\" scope=\"row\" ><SPAN class=\"fieldOrangetextbold\">Term:")){
								if(line.contains("Cumulative:")){
									double[] gpaCalc = new double[4];
									String attempt_hours_string = s.nextLine().trim();
									String earned_hours_string = s.nextLine().trim();
									String gpa_hours_string = s.nextLine().trim();
									String quality_hours_string = s.nextLine().trim();
									
									double attempt_hours = Double.parseDouble(attempt_hours_string.substring(attempt_hours_string.lastIndexOf("\">") + 3, attempt_hours_string.lastIndexOf("</p")).trim());
									double earned_hours = Double.parseDouble(attempt_hours_string.substring(earned_hours_string.lastIndexOf("\">") + 3, earned_hours_string.indexOf("</p")).trim());
									double gpa_hours = Double.parseDouble(gpa_hours_string.substring(attempt_hours_string.lastIndexOf("\">") + 3, gpa_hours_string.indexOf("</p")).trim());
									double quality_hours = Double.parseDouble(quality_hours_string.substring(attempt_hours_string.lastIndexOf("\">") + 3, quality_hours_string.indexOf("</p")).trim());
									
									//System.out.println("at"+attempt_hours);
									//System.out.println(earned_hours);
									//System.out.println(gpa_hours);
									//System.out.println(quality_hours);
									
									term_earned_hours = earned_hours - total_hours;
									term_quality_hours = quality_hours - total_quality_hours;
									System.out.printf("%.2f//%.2f%n", term_quality_hours,term_earned_hours);
									
									total_hours = earned_hours;
									total_quality_hours = quality_hours;
									gpaCalc[0] = attempt_hours;
									gpaCalc[1] = earned_hours;
									gpaCalc[2] = gpa_hours;
									gpaCalc[3] = quality_hours;
									
									gpaCalc_list.add(gpaCalc);
									
									
									double term_gpa = term_quality_hours/term_earned_hours;
									if(term_gpa == 4.00){
										gpa_lbl.setForeground(new Color(49, 105, 138));
										
									}
									if(term_gpa >= 3.75 && term_gpa < 4.0){
										gpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(term_gpa >= 3.5 && term_gpa < 3.75){
										gpa_lbl.setForeground(new Color(0,255,0));
										gpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(term_gpa >= 3.00 && term_gpa < 3.5){
										gpa_lbl.setForeground(new Color(102,204,0));
										gpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(term_gpa >= 2.5 && term_gpa < 3.00){
										gpa_lbl.setForeground(new Color(204,204,0));
										gpa_lbl.setForeground(new Color(255,128,0));
									}
									if(term_gpa >= 2.00 && term_gpa < 2.50){
										gpa_lbl.setForeground(new Color(255,128,0));
									}
                                    if(term_gpa >= 2.00 && term_gpa < 2.50){
                                    	gpa_lbl.setForeground(new Color(255,0,0));
                                    	gpa_lbl.setForeground(new Color(255,128,0));
									}

                                    if(term_gpa >= 1.00 && term_gpa < 2.00){
                                    	gpa_lbl.setForeground(new Color(153,0,0));
									}

                                    if(term_gpa >= 0.00 && term_gpa < 1.0){
                                    	gpa_lbl.setForeground(new Color(0,0,0));
									}
                                    
									gpa_lbl.setText(String.format("%.2f   ", term_gpa));
									//System.out.println(""+term_gpa);
									
								}
								if(line.contains("<TD COLSPAN=\"2\" CLASS=\"dddefault\"><p class=\"rightaligntext\">")){
									cgpa = line.substring(line.indexOf("text\">") + 6 , line.indexOf("</p></TD>")).trim();
									
									cgpa2 = Double.parseDouble(cgpa.trim());
									double change = 0;
									if(cgpa1 != 0 && cgpa2 != 0)
										change = (cgpa2 - cgpa1)/cgpa1;
									else
										change = 0.0;
									
									if(change > 0){
										change_lbl.setForeground(new Color(2, 154 ,8));
									}else if(change < 0){
										change_lbl.setForeground(new Color(153,0,0));
									}else
										change_lbl.setForeground(Color.BLACK);
									
									change_lbl.setText(String.format("    %.3f %%",change*100));
									cgpa1 = cgpa2;
									
									double cgpa_num = Double.parseDouble(cgpa.trim());
									System.out.printf("%s%n", cgpa);
									if(cgpa_num == 4.00){
										cgpa_lbl.setForeground(new Color(49, 105, 138));
									}
									if(cgpa_num >= 3.75 && cgpa_num < 4.0){
										cgpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(cgpa_num >= 3.5 && cgpa_num < 3.75){
										cgpa_lbl.setForeground(new Color(0,255,0));
										cgpa_lbl.setForeground(new Color(0,255,128));
										cgpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(cgpa_num >= 3.00 && cgpa_num < 3.5){
										cgpa_lbl.setForeground(new Color(102,204,0));
										cgpa_lbl.setForeground(new Color(0,255,128));
										cgpa_lbl.setForeground(new Color(2, 154 ,8));
									}
									if(cgpa_num >= 2.5 && cgpa_num < 3.00){
										cgpa_lbl.setForeground(new Color(204,204,0));
										cgpa_lbl.setForeground(new Color(255,128,0));
										
									}
									if(cgpa_num >= 2.00 && cgpa_num < 2.50){
										cgpa_lbl.setForeground(new Color(255,128,0));
									}
                                    if(cgpa_num >= 2.00 && cgpa_num < 2.50){
                                    	cgpa_lbl.setForeground(new Color(255,0,0));
                                    	cgpa_lbl.setForeground(new Color(255,128,0));
									}

                                    if(cgpa_num >= 1.00 && cgpa_num < 2.00){
                                    	cgpa_lbl.setForeground(new Color(153,0,0));
									}

                                    if(cgpa_num >= 0.00 && cgpa_num < 1.0){
                                    	cgpa_lbl.setForeground(new Color(0,0,0));
									}
									cgpa_lbl.setText(cgpa);
									break;
								}
								line = s.nextLine();
							}
							
							trans_panel.add(sem_lbl);
							trans_panel.add(gpa_lbl);
							trans_panel.add(cgpa_lbl);
							trans_panel.add(change_lbl);
							trans_panel.setBorder(new LineBorder(Color.BLACK, 1));
							GUI.trans.add(trans_panel);
						}
					}
					if(GUI.trans.size() == 0){
						JOptionPane.showMessageDialog(null, "Hmmm, Couldn't find anything, make sure that the file contains the Page Source && and it's a Text File");
					}
					s.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}


	
	
	/*public static void Graph(){
		TransAn t = new TransAn();
		Graph g = t.new Graph();
		Stage z = new Stage();
		z = g.getStage();
		z.show();
	}

	class Graph extends Application{

		@Override
		public void start(Stage stage) throws Exception {
			stage.setTitle("Cumulitive GPA");
			final CategoryAxis sem = new CategoryAxis();
			final NumberAxis gpa = new NumberAxis();
			final BarChart<String,Number> bc = new BarChart<String,Number>(sem,gpa);
			bc.setTitle("Cumulitive GPA vs. Semesters");
	        sem.setLabel("Semester");       
	        gpa.setLabel("CGPA");
	        
	        XYChart.Series series1 = new XYChart.Series();
	        for(JPanel j : trans){       
	        series1.getData().add(new XYChart.Data(((JLabel) j.getComponent(0)).getText(), Double.parseDouble(((JLabel) j.getComponent(1)).getText())));
	        }
	        
	        Scene scene  = new Scene(bc,800,600);
	        bc.getData().addAll(series1);
	        stage.setScene(scene);
	        //stage.show();
			
		}
		
		public Stage getStage(){
			return this.getStage();
		}
		
		public void main(){
			Graph.launch();
		}
		
		
	}*/
	
	}


