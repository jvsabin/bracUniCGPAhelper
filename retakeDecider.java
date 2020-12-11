import java.util.Map;
import java.util.Set;

public class retakeDecider {
    Map<String, String> resultMap;
    Double expectedCGPA;
    String retakeExemption;

    public void set(Map<String, String> map, Double val, String retakeExempt) {
        resultMap = map;
        expectedCGPA = val;
        retakeExemption=retakeExempt;
    }

    public void decider(int val) {
        //for( int i=0; i<1 ;i++){
        Set<Map.Entry<String, String>> qt = resultMap.entrySet();
        int i = 0;
        //int val=3;
        String[] courseName = new String[val];
        String[] result = new String[val];
        for (Map.Entry<String, String> me : qt) {
            //System.out.print(me.getKey()+": ");
            //System.out.println(me.getValue());
            //System.out.println(me.getKey()+ " "+ me.getValue().charAt(4)+ me.getValue().charAt(5)+ me.getValue().charAt(6));
            String replacedValue = me.getValue(); //cgpa change line
            char[] replacedValueChar = replacedValue.toCharArray();
            replacedValueChar[0] = '4';
            replacedValueChar[2] = '0';
            replacedValue = String.valueOf(replacedValueChar); //cgpa change line
            //System.out.println(replacedValue);
            if (i < val) {
                if(!retakeExemption.contains(me.getKey())) {
                    courseName[i] = me.getKey();
                    result[i] = replacedValue;
                }
                else {
                    i--;
                }
            } else {
                break;
            }
            i++;
        }
        //}
        for (int c = 0; c < val; c++) {
            //System.out.println(courseName[c]);
            //System.out.println(result[c]);
            resultMap.put(courseName[c], result[c]);
        }
        cgpaCalc.set(resultMap);
        Double newCGPA = cgpaCalc.getCgpa();
        if (newCGPA > expectedCGPA) {
            System.out.println("NEW CGPA " + newCGPA + " (After Calculation)");
            System.out.println("Courses Need to be retaken are: ");
            for (int c = 0; c < val; c++) {
                if (c == val - 1) {
                    System.out.print(courseName[c] + ". (" + val + " courses)");
                } else {
                    System.out.print(courseName[c] + ", ");
                }
            }
        } else {
            decider(++val);
        }
    }
}
