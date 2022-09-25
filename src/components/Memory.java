package components;

import java.util.Vector;

public class Memory {
	
	MemoryData[] mem;
	int max_length = 2048;
	
	public Memory() {
		mem = new MemoryData[max_length];
	}
	
	public void expandMemory() {
		max_length = 4096;
		MemoryData[] memExpand = new MemoryData[max_length];
		for(int i = 0; i < mem.length;i++) {
			memExpand[i] = mem[i];
		}
		mem = memExpand;
	}
	
	public void shrinkMemory() {
		max_length = 2048;
		MemoryData[] memShrink = new MemoryData[max_length];
		for(int i = 0; i < memShrink.length;i++) {
			memShrink[i] = mem[i];
		}
		mem = memShrink;
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

}
