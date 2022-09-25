package components;

import register.*;

public class InstructionRegister extends Register  {
	
	public InstructionRegister(int registerNumber) {
		super(RegisterType.InstructionRegister);
        setRegisterNumber(registerNumber);
    }
	
	public int getIRAddrValue() {
		String addr = getValue().substring(12);
		int value = Integer.parseInt(addr,2);
		return value;
	}
	
	public int getIRGPRValue() {
		String addr = getValue().substring(6,8);
		int value = Integer.parseInt(addr,2);
		return value;
	}
}
