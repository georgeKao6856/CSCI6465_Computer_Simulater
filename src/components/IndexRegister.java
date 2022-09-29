package components;

import register.*;

public class IndexRegister extends Register  {
	
	public IndexRegister(int registerNumber) {
		super(RegisterType.IndexRegister);
        setRegisterNumber(registerNumber);
    }
	
}
