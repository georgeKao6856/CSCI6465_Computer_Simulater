package components;

import register.*;

public class InstructionRegister extends Register  {
	
	public InstructionRegister(int registerNumber) {
		super(RegisterType.InstructionRegister);
        setRegisterNumber(registerNumber);
    }
	
	public int getAddrValue() {
		String addr = getValue().substring(12);
		int value = Integer.parseInt(addr,2);
		return value;
	}
	
	public int getGPRValue() {
		String gprValue = getValue().substring(6,8);
		int value = Integer.parseInt(gprValue,2);
		return value;
	}
	
	public int getIXRValue() {
		String ixrValue = getValue().substring(8,10);
		int value = Integer.parseInt(ixrValue,2);
		return value;
	}
	
	public int getIndirectValue() {
		String iValue = getValue().substring(10,11);
		int value = Integer.parseInt(iValue,2);
		return value;
	}
	
	public int getOperation() {
		String iValue = getValue().substring(0,6);
		int value = Integer.parseInt(iValue,2);
		return value;
	}
}
