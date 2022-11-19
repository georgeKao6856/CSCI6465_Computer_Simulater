package register;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.stream.IntStream;


public abstract class Register extends BitSet {
	private int currentValue;
	private int sizeofRegister;
	private int registerNumber;
	private String registerType;
	
	private int deviceStringInput;
    private ArrayList<Integer> asciiValue = new ArrayList<Integer>();
	
	public Register() {
		super(16);
		sizeofRegister = 16;
		deviceStringInput = 0;
	}
	
	public Register(RegisterType x) {
		super(x.getSize());
		this.sizeofRegister = x.getSize();
		this.registerType = x.getType();
		this.currentValue = 0;
	}
	
	public BitSet getBitsetValue(){
		return this;
	}
	
	public String getValue() {
		StringBuilder buffer = new StringBuilder(sizeofRegister);
		IntStream.range(0, sizeofRegister).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
	}
	
	public void setBinaryValue(int value) {
		this.clear();
		//String bits = Integer.toBinaryString(binaryValue);
		//if (value < Math.pow(2, sizeofRegister) && value >= 0){
		if (value < Math.pow(2, sizeofRegister) ){ //allow negative number
			//BitSet bitSet = BitSet.valueOf(new long[] { value });
			int index = sizeofRegister-1;
	        while (value != 0 && index>=0) {
	            if (value % 2 != 0) {
	                this.set(index);
	            }
	            --index;
	            value = (short) (value >>> 1);
	        } 
		}
    }

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	public int getRegisterNumber() {
        return registerNumber;
    }
	
	public int getsizeofRegister() {
        return sizeofRegister;
    }
	
	public String getRegisterType() {
		return this.registerType;
	}
	
	public int getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(int value) {
		this.currentValue = value;
	}
	
	public int getdeviceStringInput() {
        return deviceStringInput;
    }
	
    public void setdeviceStringInput(int num) {
        deviceStringInput = num; 
    }
    
    public ArrayList<Integer> getasciiValue(){
        return asciiValue;
    }
    
    public void setasciiValue(ArrayList<Integer> asciiValue){
        this.asciiValue = asciiValue;
    }
}
