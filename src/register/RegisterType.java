package register;

public enum RegisterType {
	/**
	 *
	 * Enum defines types of register and number of bits in the register
	 */

	GeneralPurposeRegister("GPR", 16),
    IndexRegister("IXR", 16),
    InstructionRegister("IR", 16),
    MemoryAddressRegister("MAR", 12),
    MemoryFaultRegister("MFR", 4),
    MemoryBufferRegister("MBR", 16),
    ProgramCounter("PC", 12),
    FloatingPointRegister("FR", 16),
    ConditionCode("CC", 1);

    private String type;
    private int size;

    RegisterType(String type, int size) {
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
