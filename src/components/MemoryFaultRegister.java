package components;

import register.*;

public class MemoryFaultRegister extends Register  {
	
	public MemoryFaultRegister(int registerNumber) {
		super(RegisterType.MemoryFaultRegister);
        setRegisterNumber(registerNumber);
    }
	
}
