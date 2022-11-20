package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Main.Panels;

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
	private ConditionCode cc0 = new ConditionCode(0); //Overflow
	private ConditionCode cc1 = new ConditionCode(1); //Underflow
	private ConditionCode cc2 = new ConditionCode(2); //DivZero
	private ConditionCode cc3 = new ConditionCode(3); //EqualorNot
	private ArrayList<ConditionCode> CCList = new ArrayList<ConditionCode>();	
	private BigInteger maxINT = BigInteger.valueOf((long) Math.pow(2, 16));
	private MemoryFaultRegister mfr = new MemoryFaultRegister(0);
	private ArrayList<Integer> asciiValue = new ArrayList<Integer>();
	public static String keyboardInput;
	Panels panel;
	
	public CPU(Memory mem, Panels panel) {
		this.mem = mem;
		this.panel = panel;
		GPRList.add(gpr0); GPRList.add(gpr1); GPRList.add(gpr2); GPRList.add(gpr3);
		IXRList.add(ixr0); IXRList.add(ixr1); IXRList.add(ixr2); IXRList.add(ixr3);
		CCList.add(cc0); CCList.add(cc1); CCList.add(cc2); CCList.add(cc3);
		//decode opcode
		decoder.put(0, () -> HLT()); decoder.put(1, () -> LDR()); decoder.put(2, () -> STR());  decoder.put(3, () -> LDA()); decoder.put(10, () -> JZ());
		decoder.put(11, () -> JNE()); decoder.put(12, () -> JCC()); decoder.put(13, () -> JMA()); decoder.put(41, () -> LDX()); decoder.put(42, () -> STX());
		decoder.put(20, () -> MLT()); decoder.put(21, () -> DVD()); decoder.put(22, () -> TRR()); decoder.put(23, () -> AND()); decoder.put(24, () -> ORR());
		decoder.put(25, () -> NOT()); decoder.put(31, () -> SRC()); decoder.put(32, () -> RRC()); decoder.put(61, () -> IN()); decoder.put(62, () -> OUT());
		decoder.put(14, () -> JSR()); decoder.put(15, () -> RFSImmed()); decoder.put(16, () -> SOB()); decoder.put(17, () -> JGE()); decoder.put(04, () -> AMR()); 
        decoder.put(05, () -> SMR()); decoder.put(06, () -> AIR()); decoder.put(07, () -> SIR()); decoder.put(63, () -> CHK()); decoder.put(30, () -> TRAP());
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
	
	public void setMFR(int value) {
		mfr.setCurrentValue(value);
		mfr.setBinaryValue(value);
	}
	
	public String getBinaryMFR() {
		return mfr.getValue();
	}
	
	public int getIntMFR() {
		return mfr.getCurrentValue();
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
	
	public ArrayList<ConditionCode> getCCList() {
		return CCList;
	}
	
	public void Store() {
		if(mar.getCurrentValue() >= mem.getMaxLength()) {
			//mem.expandMemory();
			//logger.info("Memory expand to 4096 words.");
			mfr.setBinaryValue(8);  //Illegal Memory Address beyond 2048
			mfr.setCurrentValue(8);
		}else {
			if(mar.getCurrentValue()<=5 && mar.getCurrentValue()>=0) {
				mfr.setCurrentValue(1); //Illegal Memory Address to Reserved Locations
				mfr.setBinaryValue(1);
				logger.error("Cannot store value to the reserved locations.");
			}else {
				if(mar.getCurrentValue() >= mem.getMaxLength() || mbr.getCurrentValue() >= 65536) {
					logger.error("Invaild: Memory[" +  mar.getCurrentValue() + "(" + mar.getValue() + ")" + "]=>" + mbr.getCurrentValue() + "(" + mbr.getValue() + ")");
				}else { 
					cache.addElement(mar.getCurrentValue(), mbr.getCurrentValue());
					logger.info("Store {}({}) into cache address {}({})", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
					mem.set(mar.getCurrentValue(), mbr.getCurrentValue());
					logger.info("Store {}({}) into memory address {}({})", mbr.getCurrentValue(), mbr.getValue(), mar.getCurrentValue(), mar.getValue());
				}
			}
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
			mfr.setCurrentValue(4); //Illegal Operation Code
			mfr.setBinaryValue(4);
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
			mfr.setCurrentValue(4); //Illegal Operation Code
			mfr.setBinaryValue(4);
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
		
		for(int i=0; i < CCList.size();i++) {
			CCList.get(i).setCurrentValue(0);
			CCList.get(i).setBinaryValue(0);
		}

		cache.clear();
		mar.setCurrentValue(0);
		mar.setBinaryValue(0);
		mbr.setCurrentValue(0);
		mbr.setBinaryValue(0);
		mfr.setCurrentValue(0);
		mfr.setBinaryValue(0);
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
        if(GPRList.get(ir.getGPRValue()).getdeviceStringInput() == 0) {
            getEA();
            int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
            mbr.setBinaryValue(GPRvalue);
            mbr.setCurrentValue(GPRvalue);
            Store();
        }
        else {
            int EA = returnEA();
            int value;
            ArrayList<Integer> asciiValue = GPRList.get(ir.getGPRValue()).getasciiValue();
            for(int i =0; i<asciiValue.size(); i++) {
                value = asciiValue.get(i);
                mar.setBinaryValue(EA);
                mar.setCurrentValue(EA);
                mbr.setBinaryValue(value);
                mbr.setCurrentValue(value);
                Store();
                EA += 1;
            }
            GPRList.get(ir.getGPRValue()).setdeviceStringInput(0);
        }
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
		logger.info("JZ instruction start.");
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		if(GPRvalue == 0) {
			getEA();
			pc.setCurrentValue(mar.getCurrentValue());
			pc.setBinaryValue(mar.getCurrentValue());
		}else {
			pc.addOne();
		}
		logger.info("JZ instruction end.");
	}
	
	public void JNE() {
		logger.info("JNE instruction start.");
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		if(GPRvalue == 0) {
			pc.addOne();
		}else {
			getEA();
			pc.setCurrentValue(mar.getCurrentValue());
			pc.setBinaryValue(mar.getCurrentValue());
		}
		logger.info("JNE instruction end.");
	}
	
	public void JCC() {
		logger.info("JCC instruction start.");
		getEA();
		int ccValue = ir.getGPRValue();
		if(CCList.get(ccValue).getCurrentValue() == 1) {
			pc.setCurrentValue(mar.getCurrentValue());
			pc.setBinaryValue(mar.getCurrentValue());
		}else {
			pc.addOne();
		}
		logger.info("JCC instruction end.");
	}
	
	public void JMA() {
		logger.info("JMA instruction start.");
		getEA();
		pc.setCurrentValue(mar.getCurrentValue());
		pc.setBinaryValue(mar.getCurrentValue());
		logger.info("JMA instruction end.");
	}
	
	public void LDA() {
		logger.info("LDA instruction start.");
		getEA();
		GPRList.get(ir.getGPRValue()).setCurrentValue(mar.getCurrentValue());
		GPRList.get(ir.getGPRValue()).setBinaryValue(mar.getCurrentValue());
		pc.addOne();
		logger.info("LDA instruction end.");
	}
	
	public void DVD() {
		logger.info("DVD instruction start.");
		int rx = getRX();
		int ry = getRY();
		int contentRx = GPRList.get(rx).getCurrentValue();
		int contentRy = GPRList.get(ry).getCurrentValue();
		if (contentRy == 0) {
			//set CC2 to 1
			CCList.get(2).setCurrentValue(1);
			logger.info("DVD instruction DIVZERO flag.");
		}
		else if((rx == 0 || rx == 2)&&(ry==0 || ry==2) ) {
			GPRList.get(rx).setCurrentValue(contentRx / contentRy);
			GPRList.get(rx).setBinaryValue (contentRx / contentRy);
			GPRList.get(rx+1).setCurrentValue(contentRx % contentRy);
			GPRList.get(rx+1).setBinaryValue (contentRx % contentRy);
			logger.info("DVD instruction end.");
		}
		else {
			logger.info("DVD instruction- rx and ry must be 0 or 2.");
		}
		pc.addOne();
	}
	
	public void TRR() {
		logger.info("TRR instruction start.");
		int contentRx = GPRList.get(getRX()).getCurrentValue();
		int contentRy = GPRList.get(getRY()).getCurrentValue();
		if(contentRx == contentRy) {
			//cc3 = 1
			CCList.get(3).setCurrentValue(1);
		}else {
			//cc3 = 0
			CCList.get(3).setCurrentValue(0);
		}
		logger.info("TRR instruction end.");
		pc.addOne();
	}
	
	public void AND() {
		logger.info("AND instruction start.");
		int rx = getRX();
		int contentRx = GPRList.get(rx).getCurrentValue();
		int contentRy = GPRList.get(getRY()).getCurrentValue();
		GPRList.get(rx).setCurrentValue(contentRx & contentRy);
		GPRList.get(rx).setBinaryValue (contentRx & contentRy);
		logger.info("AND instruction end.");
		pc.addOne();
	}
	
	public void ORR() {
		logger.info("ORR instruction start.");
		int rx = getRX();
		int contentRx = GPRList.get(rx).getCurrentValue();
		int contentRy = GPRList.get(getRY()).getCurrentValue();
		GPRList.get(rx).setCurrentValue(contentRx | contentRy);
		GPRList.get(rx).setBinaryValue (contentRx | contentRy);
		logger.info("ORR instruction end.");
		pc.addOne();
	}
	
	public void NOT() {
		logger.info("NOT instruction start.");
        int rx = getRX();
        String contentRx = GPRList.get(rx).getValue();
        String notofContent = "";
        for(int i = 0; i<contentRx.length();i++) {
            if(contentRx.charAt(i)== '0') {
                notofContent = notofContent + "1";
            }
            else {
                notofContent = notofContent + "0";
            }
        }
        GPRList.get(rx).setBinaryValue(Integer.parseInt(notofContent,2));
        GPRList.get(rx).setCurrentValue(Integer.parseInt(notofContent,2));
        logger.info("NOT instruction end.");
		pc.addOne();
	}
	
	public void SRC() {
		logger.info("SRC instruction start.");
		int register = getRX();
		String contentRx = GPRList.get(register).getValue();
		int count = ir.getCountValue();
		int lr = ir.getLRValue();
		int al = ir.getALValue();
		String result = "";
		if (lr == 0) {	//right shift
			result = rightShift(contentRx,count,al);
		}
		else {	//left shift
			result = leftShift(contentRx,count);
		}
		
		GPRList.get(register).setCurrentValue(Integer.parseInt(result, 2));
		GPRList.get(register).setBinaryValue (Integer.parseInt(result, 2));
		logger.info("SRC instruction end.");
		pc.addOne();
	}
	
	public void RRC() {
		logger.info("RRC instruction start.");
		int register = getRX();
		String contentRx = GPRList.get(register).getValue();
		int count = ir.getCountValue();
		int lr = ir.getLRValue();
		int registerSize = GPRList.get(register).getsizeofRegister();
		if (lr == 0) {	//right shift
			for(int i=0; i<count; i++) {
				contentRx = contentRx.charAt(registerSize- 1) + contentRx.substring(0, registerSize - 1);
			}
		}else {	//left shift
			for(int i=0; i<count; i++) {
				contentRx = contentRx.substring(1) + contentRx.charAt(0);
			}
		}
		
		GPRList.get(register).setCurrentValue(Integer.parseInt(contentRx, 2));
		GPRList.get(register).setBinaryValue (Integer.parseInt(contentRx, 2));
		logger.info("RRC instruction end.");
		pc.addOne();
	}
	
	//input to register from device
	public void IN() {
		logger.info("IN instruction start.");
        int register = getRX();
        int devid = ir.getAddrValue();
        if(devid != 1) {
            String value = "";
            if(devid == 0) {
                value = JOptionPane.showInputDialog("Please type in your input for keyboard. ");
            }
            if(value.matches("\\d+")) { //checks if input has only numbers
                GPRList.get(register).setCurrentValue(Integer.valueOf(value));
                GPRList.get(register).setBinaryValue (Integer.valueOf(value));
            }
            else {
                GPRList.get(register).setdeviceStringInput(1);
                asciiValue.clear();
                for (int i= 0; i<value.length(); i++) {
                    asciiValue.add(Integer.valueOf(value.charAt(i)));
                }
                GPRList.get(register).setasciiValue(asciiValue);
                GPRList.get(register).setCurrentValue(Integer.valueOf(value.charAt(0)));
                GPRList.get(register).setBinaryValue (Integer.valueOf(value.charAt(0)));
            }

            logger.info("IN instruction end.");
        }else {
            logger.info("IN instruction end with no action.");
        }
        pc.addOne();
	}
	
	// to device from register 
	public void OUT() {
        logger.info("IN instruction start.");
        int register = getRX();
        int devid = ir.getAddrValue();
        if(devid != 0) {
            int value = GPRList.get(register).getCurrentValue();
            if(devid == 1) {
                if(value<10) {
                    panel.appendToConsole(String.valueOf(value));
                }
                else {
                    panel.appendToConsole(Character.toString((char)value));
                }
            }
            logger.info("OUT instruction end.");
        }else {
            logger.info("OUT instruction end with no action.");
        }
        pc.addOne();
    }
	
	public void MLT() {
		logger.info("MLT instruction start.");
		int rx = getRX();
		int ry = getRY();
		BigInteger contentRx = BigInteger.valueOf(GPRList.get(rx).getCurrentValue());
		BigInteger contentRy = BigInteger.valueOf(GPRList.get(ry).getCurrentValue());
		BigInteger product = contentRx.multiply(contentRy);
		if (product.abs().compareTo(maxINT) > 0) {
			//set CC0 to 1
			CCList.get(0).setCurrentValue(1);
			logger.info("MLT instruction Overflow flag.");
		}else if((rx == 0 || rx == 2)&&(ry==0 || ry==2) ) {
			GPRList.get(rx).setCurrentValue(product.divide(maxINT).intValue());
			GPRList.get(rx).setBinaryValue (product.divide(maxINT).intValue());
			GPRList.get(rx+1).setCurrentValue(product.mod(maxINT).intValue());
			GPRList.get(rx+1).setBinaryValue (product.mod(maxINT).intValue());
			logger.info("MLT instruction end.");
		}else {
			logger.info("DVD instruction- rx and ry must be 0 or 2.");
		}
		pc.addOne();
	}
	
	public String rightShift(String bitValue, int count, int ALvalue) {
		char sign;
		if (ALvalue == 0) {
			sign = bitValue.charAt(0);
		}else {
			sign = '0';
		}
        for (int i = 0; i < count; i++) {
        	bitValue = sign + bitValue.substring(0, gpr0.getsizeofRegister() - 1);
        }
        return bitValue;
	}
	
	public String leftShift(String bitValue, int count) {
		for (int i = 0; i < count; i++) {
        	bitValue = bitValue.substring(1, gpr0.getsizeofRegister()) + "0";
        }
        return bitValue;
	}
	
	public int getRX() {
		return ir.getGPRValue();
	}
	
	public int getRY() {
		return ir.getIXRValue();
	}
	
	public void AIR() {
        logger.info("AIR instruction start.");
        getEA();
        int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
        int immed=ir.getAddrValue();
        if(immed!=0) {
            if(GPRvalue + immed > 32767) {
                CCList.get(0).setCurrentValue(1);
                logger.info("Addition instruction Overflow flag.");
            }
            else
                GPRList.get(ir.getGPRValue()).setCurrentValue(GPRvalue+immed);
                GPRList.get(ir.getGPRValue()).setBinaryValue(GPRvalue+immed);
        }
        logger.info("AIR instruction end.");
        pc.addOne();
    }
	
    public void SIR() {
        logger.info("SIR instruction start.");
        getEA();
        int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
        int immed=ir.getAddrValue();
        if(immed!=0) {
            if(GPRvalue - immed < -32768) {
                CCList.get(1).setCurrentValue(1);
                logger.info("Subtraction instruction Underflow flag.");
            }else {
                GPRList.get(ir.getGPRValue()).setCurrentValue(GPRvalue-immed);
            }
            GPRList.get(ir.getGPRValue()).setCurrentValue(GPRvalue-immed);
            GPRList.get(ir.getGPRValue()).setBinaryValue(GPRvalue-immed);
        }
        logger.info("SIR instruction end.");
        pc.addOne();
    }
    
    public void JSR() {
		logger.info("JSR instruction start.");
		getEA();
		gpr3.setBinaryValue(getIntPC()+1);
		pc.setCurrentValue(mar.getCurrentValue());
		pc.setBinaryValue(mar.getCurrentValue());
		logger.info(" JSR instruction end.");
	}
    
	public void RFSImmed() {
		logger.info("RFSImmed instruction start.");
		getEA();
		setGPR0(ir.getAddrValue());
		setPC(getIntGPR3());
		logger.info(" RFSImmed instruction end.");
	}
	
	public void SOB() {
		logger.info("SOB instruction start.");
		getEA();
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		GPRList.get(ir.getGPRValue()).setCurrentValue(GPRvalue-1);
		GPRList.get(ir.getGPRValue()).setBinaryValue(GPRvalue-1);
		if(GPRvalue-1 > 0) {
			getEA();
			pc.setCurrentValue(mar.getCurrentValue());
			pc.setBinaryValue(mar.getCurrentValue());
		}else {
			pc.addOne();
		}
		logger.info("SOB instruction end.");
	}
	
	public void JGE() {
		logger.info("JGE instruction start.");
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		if(GPRvalue >= 0) {
			getEA();
			pc.setCurrentValue(mar.getCurrentValue());
			pc.setBinaryValue(mar.getCurrentValue());
		}
		else {
			pc.addOne();
		}
		
		logger.info("JGE instruction end.");
	}
	
	public void SMR() {
		logger.info("SMR instruction start.");
		getEA();
		Fetch();
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		int x= GPRvalue - (mbr.getCurrentValue());
		if(x < -32768) {
			CCList.get(1).setCurrentValue(1);
			logger.info("Subtraction instruction Underflow flag.");
		}
		else {
			GPRList.get(ir.getGPRValue()).setCurrentValue(x);
			GPRList.get(ir.getGPRValue()).setBinaryValue(x);
		}
		pc.addOne();
		logger.info("SMR instruction end.");
	}
	
	public void AMR() {
		logger.info("AMR instruction start.");
		getEA();
		Fetch();
		int GPRvalue = GPRList.get(ir.getGPRValue()).getCurrentValue();
		int x= GPRvalue +(mbr.getCurrentValue());
		if(x>32767) {
			CCList.get(0).setCurrentValue(1);
			logger.info("Addition instruction Overflow flag.");
		}
		else {
			GPRList.get(ir.getGPRValue()).setCurrentValue(x);
			GPRList.get(ir.getGPRValue()).setBinaryValue(x);
		}
		pc.addOne();
		logger.info("AMR instruction end.");
	}
	
	public int returnEA() {
        int IXRvalue = IXRList.get(ir.getIXRValue()).getCurrentValue();
        int addvalue = IXRvalue + ir.getAddrValue();
        if(ir.getIndirectValue() != 0) {    //Indirect Addressing Mode
            int indirect = mem.get(addvalue);
            return (indirect);
        }else {   //Direct Addressing mode
            return(addvalue);
        }
    }
	
	public void CHK() {
        logger.info("CHK instruction start.");
        int register = getRX();
        int devid = ir.getAddrValue();
        if (devid == 0) {
            // Check Status of the Keyboard.
            GPRList.get(register).setCurrentValue(1);
            GPRList.get(register).setBinaryValue (1);
        } else if (devid == 1) {
            // Check Status of the Console Printer.
            GPRList.get(register).setCurrentValue(1);
            GPRList.get(register).setBinaryValue (1);
        } else if (devid == 2) {
            // Check Status of the Card Reader.
            GPRList.get(register).setCurrentValue(1);
            GPRList.get(register).setBinaryValue (1);
        } else if (devid > 2 && devid < 32) {
            GPRList.get(register).setCurrentValue(1);
            GPRList.get(register).setBinaryValue (1);
        } else {
            logger.info("Invalid- DEVID>32");
        }
        pc.addOne();
        logger.info("CHK instruction end.");
    }
	
	public void TRAP() {
		logger.info("TRAP instruction start.");
        int TrapCode = ir.getTrapCodeValue();
        if(TrapCode>15 || TrapCode<0) {
        	mfr.setCurrentValue(2);  //Illegal TRAP code
        	mfr.setBinaryValue(2);
        }
        // Storing the PC+1 in memory location 2
        mem.set(2, getIntPC()+1);
        pc.setCurrentValue(mem.get(0)+ TrapCode);
        pc.setBinaryValue(mem.get(0)+ TrapCode);
        logger.info("TRAP instruction end.");

    }
}