package components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public void set(int addr, int value) {
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
	
	public int getMaxLength() {
		return max_length;
	}

}
