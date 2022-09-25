package components;

import register.*;

public class MemoryBufferRegister extends Register  {
	
	public MemoryBufferRegister(int registerNumber) {
		super(RegisterType.MemoryBufferRegister);
        setRegisterNumber(registerNumber);
    }
}
