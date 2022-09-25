package components;

import register.*;

public class GeneralPurposeRegister extends Register  {
	
	public GeneralPurposeRegister(int registerNumber) {
		super(RegisterType.GeneralPurposeRegister);
        setRegisterNumber(registerNumber);
    }
	
}
