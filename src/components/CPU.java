package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPU {
	public static final Logger logger = LoggerFactory.getLogger("CSCI6465.logger");
	private Memory mem;
	private GeneralPurposeRegister gpr0 = new GeneralPurposeRegister(0);
	private GeneralPurposeRegister gpr1 = new GeneralPurposeRegister(1);
	private GeneralPurposeRegister gpr2 = new GeneralPurposeRegister(2);
	private GeneralPurposeRegister gpr3 = new GeneralPurposeRegister(3);
	private ArrayList<GeneralPurposeRegister> GPRList = new ArrayList<GeneralPurposeRegister>();
	private IndexRegister ixr0 = new IndexRegister(0);
	private IndexRegister ixr1 = new IndexRegister(1);
	private IndexRegister ixr2 = new IndexRegister(2);
	private IndexRegister ixr3 = new IndexRegister(3);
	private ArrayList<IndexRegister> IXRList = new ArrayList<IndexRegister>();
	private MemoryAddressRegister mar = new MemoryAddressRegister(0);
	private MemoryBufferRegister mbr = new MemoryBufferRegister(0);
	private ProgramCounter pc = new ProgramCounter(0);
	private InstructionRegister ir = new InstructionRegister(0);
	private Map<Integer, Runnable> decoder = new HashMap<>();
	private Cache cache = new Cache();
	
	public CPU(Memory mem) {
		this.mem = mem;
		GPRList.add(gpr0); GPRList.add(gpr1); GPRList.add(gpr2); GPRList.add(gpr3);
		IXRList.add(ixr0); IXRList.add(ixr1); IXRList.add(ixr2); IXRList.add(ixr3);
		//decode opcode
		decoder.put(0, () -> HLT()); decoder.put(1, () -> LDR()); decoder.put(2, () -> STR()); decoder.put(41, () -> LDX()); decoder.put(42, () -> STX());
		
	}
	
	public void setMAR(int address) {
		mar.setCurrentValue(address);
		mar.setBinaryValue(address);
	}
	
	public String getBinaryMAR() {
		return mar.getValue();
	}
	
	public int getIntMAR() {
		return mar.getCurrentValue();
	}
	
	public void setMBR(int value) {
		mbr.setCurrentValue(value);
		mbr.setBinaryValue(value);
	}
	
	public String getBinaryMBR() {
		return mbr.getValue();
	}
	
	public int getIntMBR() {
		return mbr.getCurrentValue();
	}
	
	public void setPC(int value) {
		pc.setCurrentValue(value);
		pc.setBinaryValue(value);
	}
	
	public String getBinaryPC() {
		return pc.getValue();
	}
	
	public int getIntPC() {
		return pc.getCurrentValue();
	}
	
	public void setIXR1(int value) {
		ixr1.setCurrentValue(value);
		ixr1.setBinaryValue(value);
	}
	
	public String getBinaryIXR1() {
		return ixr1.getValue();
	}
	
	public int getIntIXR1() {
		return ixr1.getCurrentValue();
	}
	
	public void setIXR2(int value) {
		ixr2.setCurrentValue(value);
		ixr2.setBinaryValue(value);
	}
	
	public String getBinaryIXR2() {
		return ixr2.getValue();
	}
	
	public int getIntIXR2() {
		return ixr2.getCurrentValue();
	}
	
	public void setIXR3(int value) {
		ixr3.setCurrentValue(value);
		ixr3.setBinaryValue(value);
	}
	
	public String getBinaryIXR3() {
		return ixr3.getValue();
	}
	
	public int getIntIXR3() {
		return ixr3.getCurrentValue();
	}
	
	public void setIR(int value) {
		ir.setCurrentValue(value);
		ir.setBinaryValue(value);
	}
	
	public String getBinaryIR() {
		return ir.getValue();
	}
	
	public int getIntIR() {
		return ir.getCurrentValue();
	}
	
	public void setGPR0(int value) {
		gpr0.setCurrentValue(value);
		gpr0.setBinaryValue(value);
	}
	
	public String getBinaryGPR0() {
		return gpr0.getValue();
	}
	
	public int getIntGPR0() {
		return gpr0.getCurrentValue();
	}
	
	public void setGPR1(int value) {
		gpr1.setCurrentValue(value);
		gpr1.setBinaryValue(value);
	}
	
	public String getBinaryGPR1() {
		return gpr1.getValue();
	}
	
	public int getIntGPR1() {
		return gpr1.getCurrentValue();
	}
	
	public void setGPR2(int value) {
		gpr2.setCurrentValue(value);
		gpr2.setBinaryValue(value);
	}
	
	public String getBinaryGPR2() {
		return gpr2.getValue();
	}
	
	public int getIntGPR2() {
		return gpr2.getCurrentValue();
	}
	
	public void setGPR3(int value) {
		gpr3.setCurrentValue(value);
		gpr3.setBinaryValue(value);
	}
	
	public String getBinaryGPR3() {
		return gpr3.getValue();
	}
	
	public int getIntGPR3() {
		return gpr3.getCurrentValue();
	}
	
	public void Store() {
		if(mar.getCurrentValue() >= mem.getMaxLength()) {
			mem.expandMemory();
			logger.info("Memory expand to 4096 words.");
		}
		if(mar.getCurrentValue() >= mem.getMaxLength() || mbr.getCurrentValue() >= 65536) {
			logger.error("Invaild: Memory[" +  mar.getCurrentValue() + "(" + mar.getValue() + ")" + "]=>" + mbr.getCurrentValue() + "(" + mbr.getValue() + ")");
		}else {
			cache.addElement(mar.getCurrentValue(), mbr.getCurrentValue());
			logger.info("Store {}({}) into cache address {}({})", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
			mem.set(mar.getCurrentValue(), mbr.getCurrentValue());
			logger.info("Store {}({}) into memory address {}({})", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
		}
	}
	
	public void StorePlus() {
		Store();
		mar.addOne();
	}
	
	public void Fetch() {
		boolean control = true;
		int mbrcurrent = cache.getElement(mar.getCurrentValue());
		if(mbrcurrent == 0) {
			control = false;
			mbrcurrent = mem.get(mar.getCurrentValue());
		}
		mbr.setCurrentValue(mbrcurrent);
		mbr.setBinaryValue(mbrcurrent);
		if(control) {
			logger.info("Load {}({}) from cache address {}({}).", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
		}else {
			logger.info("Load {}({}) from memory address {}({}).", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
		}
	}
	
	public void loadIR() {
		ir.setBinaryValue(mem.get(pc.getCurrentValue()));
		ir.setCurrentValue(mem.get(pc.getCurrentValue()));
	}
	
	public ArrayList<GeneralPurposeRegister> getGPRList() {
		return GPRList;
	} 
	
	public ArrayList<IndexRegister> getIXRList() {
		return IXRList;
	} 
	
	public void loadGPR() {
		int gprValue = ir.getGPRValue();
		GPRList.get(gprValue).setCurrentValue(mbr.getCurrentValue());
		GPRList.get(gprValue).setBinaryValue(mbr.getCurrentValue());
		logger.info("Load MBR value " + mbr.getCurrentValue() + "(" + mbr.getValue() + ") into GPR " + gprValue);
	}
	
	public void SingleRun() {
		logger.info("Sinlge Run start.");
		loadIR();    //Load instruction.
		try {
			decoder.get(ir.getOperation()).run(); //Decode instruction and execute it.
		}catch(Exception e) {
			logger.error("There is no " + ir.getOperation() + " operation.");
		}
		
		logger.info("Sinlge Run end.");
	}
	
	public void Run() {
		logger.info("Run start.");
		loadIR(); //Load instruction.
		try {
			while(ir.getOperation() != 0) {
				decoder.get(ir.getOperation()).run(); //Decode instruction and execute it.
				loadIR(); //Get next instruction.
			}
			decoder.get(ir.getOperation()).run(); //Execute Halt instruction.
		}catch(Exception e) {
			logger.error("There is no " + ir.getOperation() + " operation.");
		}
		logger.info("Run end.");
	}
	
	public void Init() {
		logger.info("Init start.");
		for(int i= 0;i < GPRList.size(); i++) {
			GPRList.get(i).setCurrentValue(0);
			GPRList.get(i).setBinaryValue(0);
		}
		
		for(int i= 0;i < IXRList.size(); i++) {
			IXRList.get(i).setCurrentValue(0);
			IXRList.get(i).setBinaryValue(0);
		}

		cache.clear();
		mar.setCurrentValue(0);
		mar.setBinaryValue(0);
		mbr.setCurrentValue(0);
		mbr.setBinaryValue(0);
		pc.setCurrentValue(0);
		pc.setBinaryValue(0);
		ir.setCurrentValue(0);
		ir.setBinaryValue(0);
		mem.clear();
		logger.info("Init end.");
	}
	
	public void ReadFile() {
		logger.info("Read file start.");
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
					mbr.setCurrentValue(value);
					mbr.setBinaryValue(value);
					Store();
				}
				myReader.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		logger.info("Read file end.");
	}
	
	//Get Effective Address and set value to the MAR
	public void getEA() {
		int IXRvalue = IXRList.get(ir.getIXRValue()).getCurrentValue();
		int addvalue = IXRvalue + ir.getAddrValue();
		if(ir.getIndirectValue() != 0) {    //Indirect Addressing Mode
			int indirect = mem.get(addvalue);
			mar.setBinaryValue(indirect);
			mar.setCurrentValue(indirect);
		}else {   //Direct Addressing mode
			mar.setBinaryValue(addvalue);
			mar.setCurrentValue(addvalue);
		}
	}
	
	//Halt instruction process
	public void HLT() {
		pc.addOne();
	}
	
	//LDR instruction process
	public void LDR() {
		logger.info("LDR instruction start.");
		getEA();
		Fetch();    //Get MBR value from the current MAR address.
		loadGPR();   //Put the MBR value to the GPRs.
		pc.addOne();
		logger.info("LDR instruction end.");
	}
	
	public void STR() {
		logger.info("STR instruction start.");
		getEA();
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		mbr.setBinaryValue(GPRvalue);
		mbr.setCurrentValue(GPRvalue);
		Store();
		pc.addOne();
		logger.info("STR instruction end.");
	}
	
	public void LDX() {
		logger.info("LDX instruction start.");
		getEA();
		Fetch();
		IXRList.get(ir.getIXRValue()).setCurrentValue(mbr.getCurrentValue());
		IXRList.get(ir.getIXRValue()).setBinaryValue(mbr.getCurrentValue());
		logger.info("Store mbr value {}({}) into IXR {}.", mbr.getCurrentValue(), mbr.getValue(), ir.getIXRValue());
		pc.addOne();
		logger.info("LDX instruction end.");
	}
	
	public void STX() {
		logger.info("STX instruction start.");
		getEA();
		int IXRvalue = IXRList.get(ir.getIXRValue()).getCurrentValue();
		mbr.setCurrentValue(IXRvalue);
		mbr.setBinaryValue(IXRvalue);
		Store();
		pc.addOne();
		logger.info("STX instruction end.");
	}
	
	public void JZ() {
		
	}
}
