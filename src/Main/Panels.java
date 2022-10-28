package Main;

import java.awt.EventQueue;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import swing.CustomOutputStream;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Panels extends JFrame {

	private JPanel contentPane;
	private PrintStream standardOut;
	private JTabbedPane tabbedPane;
	private JTextArea textAreaConsole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panels frame = new Panels();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Panels() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 484);
		setTitle("Panels");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel console = new JPanel();
        JScrollPane scrollPaneConsole = new JScrollPane();
        scrollPaneConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneConsole.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        textAreaConsole = new JTextArea();
        textAreaConsole.setEditable(false);
        scrollPaneConsole.setViewportView(textAreaConsole);
        
		tabbedPane.add(console,"Console");
		GroupLayout gl_console = new GroupLayout(console);
		gl_console.setHorizontalGroup(
			gl_console.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneConsole, GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
		);
		gl_console.setVerticalGroup(
			gl_console.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneConsole, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
		);
		console.setLayout(gl_console);
		
        JPanel log = new JPanel();
        JScrollPane scrollPaneLog = new JScrollPane();
        scrollPaneLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneLog.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        JTextArea textAreaLog = new JTextArea();
        textAreaLog.setEditable(false);
        scrollPaneLog.setViewportView(textAreaLog);
        
		PrintStream printStream = new PrintStream(new CustomOutputStream(textAreaLog));
		standardOut = System.out;
		System.setOut(printStream);
        System.setErr(printStream);
        log.add(scrollPaneLog);
        tabbedPane.add(log,"Log");
        GroupLayout gl_panelLog = new GroupLayout(log);
        gl_panelLog.setHorizontalGroup(
        	gl_panelLog.createParallelGroup(Alignment.TRAILING)
        		.addComponent(scrollPaneLog, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
        );
        gl_panelLog.setVerticalGroup(
        	gl_panelLog.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPaneLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        log.setLayout(gl_panelLog);
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void appendToConsole(String input) {
		textAreaConsole.append(input);
	}

}
