package Day7;

import java.util.ArrayList;

public class CalibrationEquation {
    private long targetValue;
    private ArrayList<Integer> values;
    private String[] operations = { "add", "multi" };

    public CalibrationEquation(long targetValue, ArrayList<Integer> values) {
        this.targetValue = targetValue;
        this.values = values;
    }

    public long getTargetValue() {
        return targetValue;
    }

    public Integer[] getValues() {
        return values.toArray(new Integer[values.size()]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Target Value: ").append(targetValue).append("\nValues: ");
        for (int value : values) {
            sb.append(value).append(", ");
        }
        return sb.toString();
    }

    public boolean isCalibrated() {
        if (values.stream().mapToLong(Long::valueOf).sum() == targetValue) {
            return true;
        } else if ((targetValue % values.get(values.size() - 1) == 0)
                && (values.stream().reduce(1, (a, b) -> a * b) == targetValue)) {
            return true;
        }

        ArrayList<Long> results = new ArrayList<>();

        calculateAllCombinations(results, values.get(0), 1);

        return results.contains(targetValue);

    }

    // recursive function to calculate all possible combinations of the values
    private void calculateAllCombinations(ArrayList<Long> results, long accumulator, int index) {
        if (index == values.size()) {
            results.add(accumulator);
            return;
        }

        for (String operation : operations) {
            long rNum = values.get(index);
            calculateAllCombinations(results, calculate(accumulator, rNum, operation), index + 1);
        }
    }

    // Calculate the result of the operation
    private long calculate(long accumulator, long rNum, String operation) {
        if (operation.equals("add")) {
            return accumulator + rNum;
        } else if (operation.equals("multi")) {
            return accumulator * rNum;
        }
        return 0;
    }

}
