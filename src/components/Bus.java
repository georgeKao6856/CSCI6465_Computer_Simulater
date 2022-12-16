package components;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * The DataBus Class.The entire data path is implemented here.
 */
public class Bus {
    private final Logger logging = Logger.getLogger("Bus");
    private Memory dataMemory;
    private boolean isHalt = false;
    public boolean isMemoryExpanded;
    public String OutputString = "";
    public ArrayList<Integer> BreakPointList = new ArrayList<Integer>(100);

    public Bus( Memory dataMemory) {
        this.dataMemory = dataMemory;
        this.isMemoryExpanded = false;
    }

    public boolean getHaltStatus() {
        return isHalt;
    }
 
    public void setBreakPoint(int BreakPointPC) {
        BreakPointList.add(BreakPointPC);
    }

    public boolean BreakPointCheck(int PC) {
        for (int i = 0; i < BreakPointList.size(); i++) {
            if (BreakPointList.get(i) == PC) {
                return true;
            }
        }
        return false;
    }

    public void removeBreakPoint(int PC) {
        for (int i = 0; i < BreakPointList.size(); i++) {
            if (BreakPointList.get(i) == PC) {
                BreakPointList.remove(i);
                break;
            }
        }
    }

    public Object[] getBreakPointList() {
        return BreakPointList.toArray();
    }

    public void setHalt() {
        isHalt = true;
    }

    int pip_IR = 0;
    int pip_MBR = 3;
    int pip_MAR = 3;
    int BubbleInPipeline = 3;

    private void InsertBubbleInPipeline() {
        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Flushing(Bubble Insert)....................................................");
        System.out.println("=====================================================================================");
        pip_IR = 0;
        pip_MBR = 3;
        pip_MAR = 3;
        BubbleInPipeline = 3;
    }

    public void tik() {
        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_D(Parallel)..........................................................");
        System.out.println("=====================================================================================");

        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_C(Parallel)..........................................................");
        System.out.println("=====================================================================================");

        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_B(Parallel)..........................................................");
        System.out.println("=====================================================================================");

        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_A(Parallel)..........................................................");
        System.out.println("=====================================================================================");

        BubbleInPipeline -= 1;
    }


    public String ToBinaryString(int value, int length) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        if (a.length() == 32 && a.substring(0, 1).equals("1")) {
            // It is a negative number!
            return a.substring(a.length() - length);
        }
        String Stringlength = "" + length;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }
}
