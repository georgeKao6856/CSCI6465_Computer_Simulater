package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import components.*;

import javax.swing.WindowConstants;

import swing.CustomButton;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainDashboard {

	private JFrame frame;
	private JTextField textFieldGpr0;
	private JTextField textFieldGpr1;
	private JTextField textFieldGpr2;
	private JTextField textFieldGpr3;
	private JTextField textFieldIxr1;
	private JTextField textFieldIxr2;
	private JTextField textFieldIxr3;
	private JTextField textFieldPC;
	private JTextField textFieldMAR;
	private JTextField textFieldMBR;
	private JTextField textFieldIR;
	private JTextField textFieldMFR;
	private ArrayList<CustomButton> currentStatus = new ArrayList<>();
	private MemoryAddressRegister mar = new MemoryAddressRegister(0);
	private MemoryBufferRegister mbr = new MemoryBufferRegister(0);
	private ProgramCounter pc = new ProgramCounter(0);
	private InstructionRegister ir = new InstructionRegister(0);
	private Memory mem = new Memory();
	private GeneralPurposeRegister gpr0 = new GeneralPurposeRegister(0);
	private GeneralPurposeRegister gpr1 = new GeneralPurposeRegister(1);
	private GeneralPurposeRegister gpr2 = new GeneralPurposeRegister(2);
	private GeneralPurposeRegister gpr3 = new GeneralPurposeRegister(3);
	private ArrayList<GeneralPurposeRegister> GPRList = new ArrayList<GeneralPurposeRegister>();
	private ArrayList<JTextField> textFieldGPRList = new ArrayList<JTextField>();
	private ArrayList<IndexRegister> IXRList = new ArrayList<IndexRegister>();
	private ArrayList<JTextField> textFieldIXRList = new ArrayList<JTextField>();
	private IndexRegister ixr0 = new IndexRegister(0);
	private IndexRegister ixr1 = new IndexRegister(1);
	private IndexRegister ixr2 = new IndexRegister(2);
	private IndexRegister ixr3 = new IndexRegister(3);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainDashboard window = new MainDashboard();
					window.frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.6
	 */
	private void initialize() {
		frame = new JFrame("CSCI 6461 Team 10 Computer Simulator");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 240, 245));
		frame.setBounds(100, 100, 1206, 603);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JLabel lblGpr0 = new JLabel("GPR 0");
		lblGpr0.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblGpr1 = new JLabel("GPR 1");
		lblGpr1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblGpr2 = new JLabel("GPR 2");
		lblGpr2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblGpr3 = new JLabel("GPR 3");
		lblGpr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblIxr1 = new JLabel("IXR 1");
		lblIxr1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblIxr2 = new JLabel("IXR 2");
		lblIxr2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblIxr3 = new JLabel("IXR 3");
		lblIxr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblPC = new JLabel("PC");
		lblPC.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblMAR = new JLabel("MAR");
		lblMAR.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblMBR = new JLabel("MBR");
		lblMBR.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblIR = new JLabel("IR");
		lblIR.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JLabel lblMFR = new JLabel("MFR");
		lblMFR.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		textFieldGpr0 = new JTextField();
		textFieldGpr0.setText("0000000000000000");
		textFieldGpr0.setEditable(false);
		textFieldGpr0.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldGpr0.setColumns(10);

		textFieldGpr1 = new JTextField();
		textFieldGpr1.setText("0000000000000000");
		textFieldGpr1.setEditable(false);
		textFieldGpr1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldGpr1.setColumns(10);

		textFieldGpr2 = new JTextField();
		textFieldGpr2.setText("0000000000000000");
		textFieldGpr2.setEditable(false);
		textFieldGpr2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldGpr2.setColumns(10);

		textFieldGpr3 = new JTextField();
		textFieldGpr3.setText("0000000000000000");
		textFieldGpr3.setEditable(false);
		textFieldGpr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldGpr3.setColumns(10);
		
		textFieldGPRList.add(textFieldGpr0); textFieldGPRList.add(textFieldGpr1); textFieldGPRList.add(textFieldGpr2); textFieldGPRList.add(textFieldGpr3);
		GPRList.add(gpr0); GPRList.add(gpr1); GPRList.add(gpr2); GPRList.add(gpr3);

		textFieldIxr1 = new JTextField();
		textFieldIxr1.setText("0000000000000000");
		textFieldIxr1.setEditable(false);
		textFieldIxr1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldIxr1.setColumns(10);

		textFieldIxr2 = new JTextField();
		textFieldIxr2.setText("0000000000000000");
		textFieldIxr2.setEditable(false);
		textFieldIxr2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldIxr2.setColumns(10);

		textFieldIxr3 = new JTextField();
		textFieldIxr3.setText("0000000000000000");
		textFieldIxr3.setEditable(false);
		textFieldIxr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldIxr3.setColumns(10);
			
		textFieldIXRList.add(textFieldIxr1); textFieldIXRList.add(textFieldIxr2); textFieldIXRList.add(textFieldIxr3);
		IXRList.add(ixr0); IXRList.add(ixr1); IXRList.add(ixr2); IXRList.add(ixr3);

		textFieldPC = new JTextField();
		textFieldPC.setText("000000000000");
		textFieldPC.setEditable(false);
		textFieldPC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldPC.setColumns(10);

		textFieldMAR = new JTextField();
		textFieldMAR.setText("000000000000");
		textFieldMAR.setEditable(false);
		textFieldMAR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldMAR.setColumns(10);

		textFieldMBR = new JTextField();
		textFieldMBR.setText("0000000000000000");
		textFieldMBR.setEditable(false);
		textFieldMBR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldMBR.setColumns(10);

		textFieldIR = new JTextField();
		textFieldIR.setText("0000000000000000");
		textFieldIR.setEditable(false);
		textFieldIR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldIR.setColumns(10);

		textFieldMFR = new JTextField();
		textFieldMFR.setText("0000");
		textFieldMFR.setEditable(false);
		textFieldMFR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldMFR.setColumns(10);
		
		CustomButton btnHaltStatus = new CustomButton();
		btnHaltStatus.setEnabled(false);
		btnHaltStatus.setRadius(50);
		btnHaltStatus.setBackground(new Color(0,255,0));
		btnHaltStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JButton btnGrp0 = new JButton("LD");
		btnGrp0.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnGrp0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnGrp1 = new JButton("LD");
		btnGrp1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnGrp2 = new JButton("LD");
		btnGrp2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnGrp3 = new JButton("LD");
		btnGrp3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnIxr1 = new JButton("LD");
		btnIxr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();
				int value = Integer.parseInt(status, 2);
				ixr1.setCurrentValue(value);
				ixr1.setBinaryValue(value);
				textFieldIxr1.setText(ixr1.getValue());	
				resetBackGround();
			}
		});
		btnIxr1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnIxr2 = new JButton("LD");
		btnIxr2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();
				int value = Integer.parseInt(status, 2);
				ixr2.setCurrentValue(value);
				ixr2.setBinaryValue(value);
				textFieldIxr2.setText(ixr2.getValue());	
				resetBackGround();
			}
		});
		btnIxr2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnIxr3 = new JButton("LD");
		btnIxr3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();
				int value = Integer.parseInt(status, 2);
				ixr3.setCurrentValue(value);
				ixr3.setBinaryValue(value);
				textFieldIxr3.setText(ixr3.getValue());	
				resetBackGround();
			}
		});
		btnIxr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnPC = new JButton("LD");
		btnPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();		
				if(status.substring(0, 4).equals("0000")) {
					int value = Integer.parseInt(status, 2);
					pc.setCurrentValue(value);
					pc.setBinaryValue(value);
					textFieldPC.setText(pc.getValue());
				}else {
					Alert alert = new Alert();
					alert.setLocation(frame.getSize().width/2, frame.getSize().height/2);
					alert.setVisible(true);
					textFieldMAR.setText(pc.getValue());
				}
				resetBackGround();
			}
		});
		btnPC.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnMBR = new JButton("LD");
		btnMBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();
				int value = Integer.parseInt(status, 2);
				mbr.setCurrentValue(value);
				mbr.setBinaryValue(value);
				textFieldMBR.setText(mbr.getValue());	
				resetBackGround();
			}
		});
		btnMBR.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.put(mar.getCurrentValue(), mbr.getCurrentValue());
				resetBackGround();
			}
		});
		btnStore.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMemorytoMBR();
				resetBackGround();
			}
		});
		btnLoad.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		JButton btnMAR = new JButton("LD");
		btnMAR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnMAR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String status = loadCurrentStatus();		
				if(status.substring(0, 4).equals("0000")) {
					int value = Integer.parseInt(status, 2);
					mar.setCurrentValue(value);
					mar.setBinaryValue(value);
					textFieldMAR.setText(mar.getValue());
				}else {
					Alert alert = new Alert();
					alert.setLocation(frame.getSize().width/2, frame.getSize().height/2);
					alert.setVisible(true);
					textFieldMAR.setText(mar.getValue());
				}
				resetBackGround();
			}
		});
		
		JPanel panel_Operation = new JPanel();
		panel_Operation.setBackground(SystemColor.activeCaption);
		
		JPanel panel_GPR = new JPanel();
		panel_GPR.setBackground(SystemColor.activeCaption);
		
		JPanel panel_IXR = new JPanel();
		panel_IXR.setBackground(SystemColor.activeCaption);
		
		JPanel panel_I = new JPanel();
		panel_I.setBackground(SystemColor.activeCaption);
		
		JPanel panel_Address = new JPanel();
		panel_Address.setBackground(SystemColor.activeCaption);
		
		JButton btnStorePlus = new JButton("S+");
		btnStorePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.put(mar.getCurrentValue(), mbr.getCurrentValue());
				mar.addOne();
				textFieldMAR.setText(mar.getValue());
				resetBackGround();
			}
		});
		btnStorePlus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pcvalue = pc.getCurrentValue();
				btnHaltStatus.setBackground(Color.WHITE);
				while(mem.get(pcvalue) != 0) {
					loadIR(pcvalue);
					int value = IXRList.get(ir.getIXRValue()).getCurrentValue();
					int addvalue = value + ir.getAddrValue();
					if(ir.getIValue() != 0) {
						int indirect = mem.get(addvalue);
						mar.setBinaryValue(indirect);
						mar.setCurrentValue(indirect);
					}else {
						mar.setBinaryValue(addvalue);
						mar.setCurrentValue(addvalue);
					}
					textFieldMAR.setText(mar.getValue());
					loadMemorytoMBR();
					loadGPR();
					pc.addOne();
					textFieldPC.setText(pc.getValue());
					pcvalue = pc.getCurrentValue();
				}
				loadIR(pcvalue);
				pc.addOne();
				textFieldPC.setText(pc.getValue());
				btnHaltStatus.setBackground(new Color(0,255,0));
			}
		});
		btnRun.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnSS = new JButton("SS");
		btnSS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pcvalue = pc.getCurrentValue();
				loadIR(pcvalue);
				if(mem.get(pcvalue) != 0) {
						int value = IXRList.get(ir.getIXRValue()).getCurrentValue();
						int addvalue = value + ir.getAddrValue();
						if(ir.getIValue() != 0) {
							int indirect = mem.get(addvalue);
							mar.setBinaryValue(indirect);
							mar.setCurrentValue(indirect);
						}else {
							mar.setBinaryValue(addvalue);
							mar.setCurrentValue(addvalue);
						}
						textFieldMAR.setText(mar.getValue());
						loadMemorytoMBR();
						loadGPR();
						pc.addOne();
						textFieldPC.setText(pc.getValue());
				}else {
					pc.addOne();
					textFieldPC.setText(pc.getValue());
				}
			}
		});
		btnSS.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		CustomButton btnRunStatus = new CustomButton();
		btnRunStatus.setEnabled(false);
		btnRunStatus.setRadius(50);
		btnRunStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JLabel lblHalt = new JLabel("Halt");
		lblHalt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblRun = new JLabel("Run");
		lblRun.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnInit = new JButton("Init");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i= 0;i < GPRList.size(); i++) {
					GPRList.get(i).setCurrentValue(0);
					GPRList.get(i).setBinaryValue(0);
					textFieldGPRList.get(i).setText(GPRList.get(i).getValue());
				}
				
				for(int i= 0;i < textFieldIXRList.size(); i++) {
					IXRList.get(i).setCurrentValue(0);
					IXRList.get(i).setBinaryValue(0);
					textFieldIXRList.get(i).setText(IXRList.get(i+1).getValue());
				}

				mar.setCurrentValue(0);
				mar.setBinaryValue(0);
				textFieldMAR.setText(mar.getValue());
				mbr.setCurrentValue(0);
				mbr.setBinaryValue(0);
				textFieldMBR.setText(mbr.getValue());
				pc.setCurrentValue(0);
				pc.setBinaryValue(0);
				textFieldPC.setText(pc.getValue());
				ir.setCurrentValue(0);
				ir.setBinaryValue(0);
				textFieldIR.setText(ir.getValue());
				mem.clear();
				JFileChooser fileChooser = new JFileChooser();
				String pwd = System.getProperty("user.dir");
				fileChooser.setCurrentDirectory(new File(pwd));
				int response = fileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						Scanner myReader = new Scanner(file);
						while (myReader.hasNextLine()) {
							String data = myReader.nextLine();
							String[] dataSperated = data.split(" ");
							int addr = Integer.parseInt(dataSperated[0],16);
							int value = Integer.parseInt(dataSperated[1],16);
							mar.setCurrentValue(addr);
							mar.setBinaryValue(addr);
							textFieldMAR.setText(mar.getValue());
							mbr.setCurrentValue(value);
							mbr.setBinaryValue(value);
							textFieldMBR.setText(mbr.getValue());
							mem.put(mar.getCurrentValue(), mbr.getCurrentValue());
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnInit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnInit.setBackground(Color.RED);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblGpr1)
										.addComponent(lblGpr0)
										.addComponent(lblGpr2)
										.addComponent(lblGpr3)
										.addComponent(lblIxr1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldIxr1, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnIxr1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(textFieldGpr1, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldGpr0, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnGrp0, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnGrp1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldGpr2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnGrp2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldGpr3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnGrp3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblIxr3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIxr2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldIxr3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnIxr3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldIxr2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnIxr2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))))
							.addGap(95)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPC, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMAR, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMBR, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIR, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMFR, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldIR, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldMFR, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldMBR, 238, 238, 238)
											.addGap(18)
											.addComponent(btnMBR, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldMAR, 238, 238, 238)
											.addGap(18)
											.addComponent(btnMAR, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldPC, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnPC, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnStore, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnStorePlus, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnInit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnSS, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblHalt)
												.addComponent(lblRun, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnHaltStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRunStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
											.addGap(63))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(26, Short.MAX_VALUE)
							.addComponent(panel_Operation, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_GPR, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_IXR, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_I, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_Address, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldGpr0, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGpr0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGrp0, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPC, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPC, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGpr1)
						.addComponent(textFieldGpr1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGrp1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMAR, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMAR, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMAR, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGpr2)
						.addComponent(textFieldGpr2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGrp2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMBR, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMBR, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMBR, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGpr3)
								.addComponent(textFieldGpr3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGrp3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIR, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIR, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMFR, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldMFR, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIxr1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIxr1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIxr1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIxr2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIxr2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIxr2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnInit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnStore, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnStorePlus, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnSS, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblHalt)
										.addComponent(btnHaltStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnRunStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRun, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIxr3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldIxr3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIxr3))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel_GPR, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(panel_Operation, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_IXR, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_I, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Address, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
					.addGap(146))
		);
		
		CustomButton btnb0 = new CustomButton();
		btnb0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb0);
			}
		});
		btnb0.setText("0");
		btnb0.setRadius(50);
		btnb0.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb0);
		
		CustomButton btnb1 = new CustomButton();
		btnb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb1);
			}
		});
		btnb1.setText("1");
		btnb1.setRadius(50);
		btnb1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb1);
		
		CustomButton btnb2 = new CustomButton();
		btnb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb2);
			}
		});
		btnb2.setText("2");
		btnb2.setRadius(50);
		btnb2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb2);
		
		CustomButton btnb3 = new CustomButton();
		btnb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb3);
			}
		});
		btnb3.setText("3");
		btnb3.setRadius(50);
		btnb3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb3);
		
		CustomButton btnb4 = new CustomButton();
		btnb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb4);
			}
		});
		btnb4.setText("4");
		btnb4.setRadius(50);
		btnb4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb4);
		
		CustomButton btnb5 = new CustomButton();
		btnb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb5);
			}
		});
		btnb5.setText("5");
		btnb5.setRadius(50);
		btnb5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb5);
		
		CustomButton btnb6 = new CustomButton();
		btnb6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb6);
			}
		});
		btnb6.setText("6");
		btnb6.setRadius(50);
		btnb6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb6);
		
		CustomButton btnb7 = new CustomButton();
		btnb7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb7);
			}
		});
		btnb7.setText("7");
		btnb7.setRadius(50);
		btnb7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb7);
		
		CustomButton btnb8 = new CustomButton();
		btnb8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb8);
			}
		});
		btnb8.setText("8");
		btnb8.setRadius(50);
		btnb8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb8);
		
		CustomButton btnb9 = new CustomButton();
		btnb9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb9);
			}
		});
		btnb9.setText("9");
		btnb9.setRadius(50);
		btnb9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb9);
		
		CustomButton btnb10 = new CustomButton();
		btnb10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb10);
			}
		});
		btnb10.setText("10");
		btnb10.setRadius(50);
		btnb10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb10);
		
		CustomButton btnb11 = new CustomButton();
		btnb11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb11);
			}
		});
		btnb11.setText("11");
		btnb11.setRadius(50);
		btnb11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb11);
		
		CustomButton btnb12 = new CustomButton();
		btnb12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb12);
			}
		});
		btnb12.setText("12");
		btnb12.setRadius(50);
		btnb12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb12);
		
		CustomButton btnb13 = new CustomButton();
		btnb13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb13);
			}
		});
		btnb13.setText("13");
		btnb13.setRadius(50);
		btnb13.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb13);
		
		CustomButton btnb14 = new CustomButton();
		btnb14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb14);
			}
		});
		btnb14.setText("14");
		btnb14.setRadius(50);
		btnb14.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		currentStatus.add(btnb14);
		
		CustomButton btnb15 = new CustomButton();
		btnb15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBackGround(btnb15);
			}
		});
		btnb15.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnb15.setRadius(50);
		btnb15.setText("15");
		currentStatus.add(btnb15);
		
		JLabel lbl_Address = new JLabel("Address");
		lbl_Address.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout gl_panel_Address = new GroupLayout(panel_Address);
		gl_panel_Address.setHorizontalGroup(
			gl_panel_Address.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Address.createSequentialGroup()
					.addGap(24)
					.addComponent(btnb4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnb3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnb2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnb1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnb0, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_Address.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addComponent(lbl_Address, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(118))
		);
		gl_panel_Address.setVerticalGroup(
			gl_panel_Address.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Address.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_Address.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnb4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb0, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(lbl_Address, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		panel_Address.setLayout(gl_panel_Address);
		
		
		
		JLabel lbl_I = new JLabel("I");
		lbl_I.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout gl_panel_I = new GroupLayout(panel_I);
		gl_panel_I.setHorizontalGroup(
			gl_panel_I.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_I.createSequentialGroup()
					.addGap(21)
					.addComponent(btnb5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_I.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addComponent(lbl_I, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_I.setVerticalGroup(
			gl_panel_I.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_I.createSequentialGroup()
					.addGap(19)
					.addComponent(btnb5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lbl_I, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		panel_I.setLayout(gl_panel_I);
				
				JLabel lbl_IXR = new JLabel("IXR");
				lbl_IXR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				GroupLayout gl_panel_IXR = new GroupLayout(panel_IXR);
				gl_panel_IXR.setHorizontalGroup(
					gl_panel_IXR.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_IXR.createSequentialGroup()
							.addGroup(gl_panel_IXR.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_IXR.createSequentialGroup()
									.addGap(22)
									.addComponent(btnb7, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnb6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_IXR.createSequentialGroup()
									.addGap(55)
									.addComponent(lbl_IXR, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(19, Short.MAX_VALUE))
				);
				gl_panel_IXR.setVerticalGroup(
					gl_panel_IXR.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_IXR.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel_IXR.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnb7, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnb6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lbl_IXR, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(30, Short.MAX_VALUE))
				);
				panel_IXR.setLayout(gl_panel_IXR);
		
		JLabel lbl_GPR = new JLabel("GPR");
		lbl_GPR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout gl_panel_GPR = new GroupLayout(panel_GPR);
		gl_panel_GPR.setHorizontalGroup(
			gl_panel_GPR.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_GPR.createSequentialGroup()
					.addGroup(gl_panel_GPR.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_GPR.createSequentialGroup()
							.addGap(20)
							.addComponent(btnb9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnb8, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_GPR.createSequentialGroup()
							.addGap(53)
							.addComponent(lbl_GPR)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_GPR.setVerticalGroup(
			gl_panel_GPR.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_GPR.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_GPR.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnb8, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb9, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lbl_GPR)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_GPR.setLayout(gl_panel_GPR);											
																
		JLabel lbl_Operation = new JLabel("Operation");
		lbl_Operation.setForeground(new Color(0, 0, 0));
		lbl_Operation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout gl_panel_Operation = new GroupLayout(panel_Operation);
		gl_panel_Operation.setHorizontalGroup(
			gl_panel_Operation.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_Operation.createSequentialGroup()
					.addGroup(gl_panel_Operation.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Operation.createSequentialGroup()
							.addGap(18)
							.addComponent(btnb15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnb14, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnb13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnb12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnb11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel_Operation.createSequentialGroup()
							.addContainerGap(145, Short.MAX_VALUE)
							.addComponent(lbl_Operation)
							.addGap(71)))
					.addComponent(btnb10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel_Operation.setVerticalGroup(
			gl_panel_Operation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Operation.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_Operation.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnb15, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
						.addComponent(btnb14, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb13, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnb12, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb11, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnb10, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lbl_Operation)
					.addGap(18))
		);
		panel_Operation.setLayout(gl_panel_Operation);
		frame.getContentPane().setLayout(groupLayout);
	}

	public void changeBackGround(CustomButton btn) {
		if(btn.getBackground().equals(Color.WHITE)) {
			btn.setBackground(btn.getColorOver());
		}else {
			btn.setBackground(Color.WHITE);
		}
	}
	
	public void resetBackGround() {
		for (CustomButton element : currentStatus) {
			element.setBackground(Color.WHITE);
		}
	}

	public String loadCurrentStatus() {
		String status="";
		for (CustomButton element : currentStatus) {
			status = (element.getBackground().equals(Color.WHITE))? "0" + status : "1" + status;
		}
		return status;
	}
	
	public void loadMemorytoMBR() {
		int mbrcurrent = mem.get(mar.getCurrentValue());
		mbr.setCurrentValue(mbrcurrent);
		mbr.setBinaryValue(mbrcurrent);
		textFieldMBR.setText(mbr.getValue());
	}
	
	public void loadIR(int pcvalue) {
		ir.setBinaryValue(mem.get(pcvalue));
		ir.setCurrentValue(mem.get(pcvalue));
		String instruction = ir.getValue();
		textFieldIR.setText(instruction);
	}
	
	public void loadGPR() {
		int gprValue = ir.getGPRValue();
		GPRList.get(gprValue).setCurrentValue(mbr.getCurrentValue());
		GPRList.get(gprValue).setBinaryValue(mbr.getCurrentValue());
		textFieldGPRList.get(gprValue).setText(GPRList.get(gprValue).getValue());;
	}
}