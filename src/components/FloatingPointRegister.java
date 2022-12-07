package components;

import register.*;

public class FloatingPointRegister extends Register  {
	
	public FloatingPointRegister(int registerNumber) {
		super(RegisterType.FloatingPointRegister);
        setRegisterNumber(registerNumber);
    }
	
}
