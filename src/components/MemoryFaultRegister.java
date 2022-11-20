package components;

import register.*;

public class MemoryFaultRegister extends Register  {
	
	private int id;
	
	public MemoryFaultRegister(int registerNumber) {
		super(RegisterType.MemoryFaultRegister);
        setRegisterNumber(registerNumber);
    }
	
	public void setID(int id) {
		this.id = id;
	} 
	
	public int getID() {
		return id;
	}
}
