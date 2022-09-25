package components;

import register.Register;
import register.RegisterType;

public class ProgramCounter extends Register {
	public ProgramCounter(int registerNumber) {
		super(RegisterType.ProgramCounter);
        setRegisterNumber(registerNumber);
    }
	
	public void addOne() {
		int current = getCurrentValue();
		current += 1;
		setCurrentValue(current);
		setBinaryValue(current);
	}
}
