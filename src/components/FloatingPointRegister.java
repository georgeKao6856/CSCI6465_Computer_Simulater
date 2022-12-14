package components;

import register.*;

public class FloatingPointRegister extends Register  {
	
	public FloatingPointRegister(int registerNumber) {
		super(RegisterType.FloatingPointRegister);
        setRegisterNumber(registerNumber);
    }
	
	public String getMantissaString() {
        return getValue().substring(8, 16);
    }
	
	public int getMantissaInteger() {
        return Integer.parseInt(getValue().substring(8, 16), 2);
    }
	
	public int getExponentInteger() {
        return Integer.parseInt(getValue().substring(1, 8), 2);
    }

    public String getExponentString() {
        return getValue().substring(1, 8);
    }
    
    public int getSignInteger() {
        int sign = Character.getNumericValue(getValue().charAt(0));
        if (sign == 1) {
            return -1;
        }else{
        	return 1;
        }
    }
    
    public String getSignString() {
        return String.valueOf(getValue().charAt(0));
    }
}
