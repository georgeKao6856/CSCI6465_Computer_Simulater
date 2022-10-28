package components;

import register.Register;
import register.RegisterType;

public class ConditionCode extends Register  {
	public ConditionCode(int registerNumber) {
		super(RegisterType.ConditionCode);
        setRegisterNumber(registerNumber);
    }
}
