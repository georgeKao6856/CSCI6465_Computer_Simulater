package components;

import register.*;

public class MemoryAddressRegister extends Register  {
	
	public MemoryAddressRegister(int registerNumber) {
		super(RegisterType.MemoryAddressRegister);
        setRegisterNumber(registerNumber);
    }
	
	public void addOne() {
		int current = getCurrentValue();
		current += 1;
		setCurrentValue(current);
		setBinaryValue(current);
	}
}
