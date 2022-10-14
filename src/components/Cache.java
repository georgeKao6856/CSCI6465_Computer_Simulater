package components;

public class Cache {
	
	MemoryData[] mem;
	int max_length = 2048;
	
	public Cache() {
		mem = new MemoryData[max_length];
	}
	
	public void put(int addr, int value) {
		MemoryData current = new MemoryData(addr, value);
		mem[addr] = current;
	}
	
	public int get(int addr) {
		if(mem[addr] == null) {
			return 0;
		}else {
			return mem[addr].getData();
		}	
	}
	
	public void clear() {
		for(int i = 0; i < mem.length;i++) {
			mem[i] = null;
		}
	}

}
