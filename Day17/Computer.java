package Day17;

import java.util.ArrayList;

public class Computer {

    int registerA, registerB, registerC;
    int oPoint;

    ArrayList<Byte> output = new ArrayList<>();

    public Computer() {
        this(729);
    }

    public Computer(int registerA) {
        this.registerA = registerA;
        registerB = 0;
        registerC = 0;
        oPoint = 0;
    }

    // get combo operand
    public int getComboOperand(byte input) {
        return switch (input) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            case 7 -> -1;
            default -> -1;
        };
    }

    // take command opcodes
    public void takeCommand(ArrayList<Byte> commands) {
        System.out.println("Taking commands... + " + commands);
        while (oPoint < commands.size() - 1) {
            byte opcode = commands.get(oPoint);
            byte literal = commands.get(oPoint + 1);

            int comboOperand = getComboOperand(literal);

            switch (opcode) {
                // The adv instruction (opcode 0) performs division. The numerator is the value
                // in the A register. The denominator is found by raising 2 to the power of the
                // instruction's combo operand. (So, an operand of 2 would divide A by 4 (2^2);
                // an operand of 5 would divide A by 2^B.) The result of the division operation
                // is truncated to an integer and then written to the A register.
                case 0 -> {
                    registerA /= Math.pow(2, comboOperand);
                    oPoint += 2;
                }

                // The bxl instruction (opcode 1) calculates the bitwise XOR of register B and
                // the instruction's literal operand, then stores the result in register B.
                case 1 -> {
                    registerB ^= literal;
                    oPoint += 2;
                }

                // The bst instruction (opcode 2) calculates the value of its combo operand
                // modulo 8 (thereby keeping only its lowest 3 bits), then writes that value to
                // the B register.
                case 2 -> {
                    registerB = comboOperand % 8;
                    oPoint += 2;
                }

                // The jnz instruction (opcode 3) does nothing if the A register is 0. However,
                // if the A register is not zero, it jumps by setting the instruction pointer to
                // the value of its literal operand; if this instruction jumps, the instruction
                // pointer is not increased by 2 after this instruction.
                case 3 -> {
                    if (registerA != 0) {
                        oPoint = (int) literal;
                    } else {
                        oPoint += 2;
                    }
                }

                // The bxc instruction (opcode 4) calculates the bitwise XOR of register B and
                // register C, then stores the result in register B. (For legacy reasons, this
                // instruction reads an operand but ignores it.)
                case 4 -> {
                    registerB ^= registerC;
                    oPoint += 2;
                }

                // The out instruction (opcode 5) calculates the value of its combo operand
                // modulo 8, then outputs that value. (If a program outputs multiple values,
                // they are separated by commas.)
                case 5 -> {
                    output.add((byte) (comboOperand % 8));
                    System.out.print((comboOperand % 8) + ",");
                    oPoint += 2;
                }

                // The bdv instruction (opcode 6) works exactly like the adv instruction except
                // that the result is stored in the B register. (The numerator is still read
                // from the A register.)
                case 6 -> {
                    registerB = (int) (registerA / Math.pow(2, comboOperand));
                    oPoint += 2;
                }

                // The cdv instruction (opcode 7) works exactly like the adv instruction except
                // that the result is stored in the C register. (The numerator is still read
                // from the A register.)
                case 7 -> {
                    registerC = (int) (registerA / Math.pow(2, comboOperand));
                    oPoint += 2;
                }
            }
        }
    }

}
