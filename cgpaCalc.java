import java.util.Map;
import java.util.Set;

public class cgpaCalc {
    private static Map<String, String> resultMap;
    //Map <String, String> resultMap;
    public static void set(Map<String, String> map){
        resultMap=map;
    }
    public static Double getCgpa(){
        Double cgpa=0.0;
        Double credit=0.0;
        Double gradePoint=0.0;
        Set< Map.Entry< String, String> > qt = resultMap.entrySet();
        for (Map.Entry< String, String> me:qt){
            String value=me.getValue();
            String[] splitValue = value.split(" ");
            credit+=Double.parseDouble(splitValue[1]);
            gradePoint+=Double.parseDouble(splitValue[0])*Double.parseDouble(splitValue[1]);

            //System.out.println(me.getValue());

        }
        cgpa=gradePoint/credit;
        return cgpa;
    }
}
