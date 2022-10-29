package components;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache {
	
	public static final Logger logger = LoggerFactory.getLogger("CSCI6465.logger");
	private Vector<MemoryData> LCache;
	
	public Cache() {
		LCache = new Vector<MemoryData>(16);
	}
	
	public void addElement(int addr, int value) {
		MemoryData newData = new MemoryData(addr, value);
		LCache.add(0,newData);
		LCache.setSize(16);
	}
	
	public void clear() {
		LCache.clear();
		LCache.setSize(16);
	}
	
	public int getElement(int addr) {
		try { //First run will have NullPoint exception error, so use try catch to avoid.
			for(int i=0; i < LCache.size();i++) {
				if(LCache.get(i).getAddress() == addr) {
					logger.info("Cache hit!");
					return LCache.get(i).getData();
				}
			}
			logger.info("Cache miss!");
			return 0;
		}catch(Exception e){
			logger.info("Cache miss!");
			return 0;
		}
		
	}

}
