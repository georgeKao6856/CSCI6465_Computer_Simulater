package components;

public class MemoryData {
	private int address;
	private int value;
	
	public MemoryData(int add, int val) {
		this.address = add;
		this.value = val;
	}
	
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getData() {
		return value;
	}
	public void setValue(short value) {
		this.value = value;
	}
}
