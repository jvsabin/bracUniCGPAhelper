import java.util.*;

public class dataPrepare {
    String[] spText;

    public Map<String, String> get (String[] txt){
        Arrays.fill(aCourses,""); //for arrays
        spText=txt;
        splitToQueue();
        //System.out.println("hoise afer q");
        queueToArray();
        //System.out.println("hoise afer q2a");
        courseNumber();
        cleanArray();
        arrayToMap();
        sortedMap();
        return sortedResultMap;
    }

    /*public Map<String, Double> get(){

        return sortedResultMap;
    }*/

    //Queue from splited text
    Queue<String> courses = new LinkedList<>();
    public void splitToQueue(){
        for (int i = 0; i < spText.length - 2; i++) {
            if (spText[i].startsWith("SEMESTER") && !spText[i].startsWith("SEMESTER Cre")) {
                while (!spText[i + 1].startsWith("SEMESTER Credits") && i < spText.length - 4) {
                    courses.add(spText[i + 1]);
                    i++;
                }
            }
        }
    }

    // queue to array
    String[] aCourses = new String[60];
    int c = 0;
    public void queueToArray(){
        while (courses.size() > 0) {
            String temp = courses.poll();
            try {
                String[] tmp = temp.split(" ");
                Double.parseDouble(tmp[0]);
                aCourses[c - 2] += " " + aCourses[c - 1] + " " + temp;
                aCourses[c - 1] = ""; //vvvvvvvvvvvvvvvvvvvvvvvvvv
                c -= 2;
            } catch (Exception e) {
                aCourses[c] = temp;
            }
            c++;
        }
    }

    //Completed Course Count
    int count=0;
    public void courseNumber(){
        for (int i=0; i<aCourses.length; i++){
            //System.out.println(aCourses[i]);
            if(aCourses[i].equals("")){
                //System.out.println(count);
                System.out.println("Number of Course Completed: "+count);
                break;
            }
            else if(aCourses[i].contains("(NT)") || aCourses[i].contains("ENG091") ){
                count--;
            }
            count++;
        }
        /*for (int i=0; i<aCourses.length; i++){
            System.out.println(aCourses[i]);
        }*/

    }
    //array to array for clean array
    String[] finalCourses;
    public void cleanArray(){
        finalCourses = new String[count];
        for (int i = 0, j=0; i <finalCourses.length ; i++,j++) {
            //System.out.println("tgy");
            if(!(aCourses[j].contains("(NT)") || aCourses[j].contains("ENG091"))){
                finalCourses[i]=aCourses[j];
            }
            else{
                i--;
            }
        }
        /*for (int i=0; i<finalCourses.length; i++) {
            System.out.println(finalCourses[i]);
        }*/
    }

    //result from Clean Array to Linked HashMap
    Map<String, String> resultMap = new LinkedHashMap<String, String>();
    public void arrayToMap(){
        for (int i=0; i<finalCourses.length; i++){
            String[] check= finalCourses[i].split(" ");
            if(finalCourses[i].contains("(NT)")){

            }
            else{
                if(finalCourses[i].contains("(RT)")) {
                    resultMap.put(check[0], check[check.length - 1] + " " + check[check.length - 4]);
                }
                else {
                    resultMap.put(check[0], check[check.length - 1] + " " + check[check.length - 3]);
                }
            }
        }

       /* resultMap.put("MAT216","3.7 3.0");
        resultMap.put("MAT110","4.0 3.0");
        resultMap.put("CSE330","3.7 3.0");
        resultMap.put("CSE370","4.0 3.0");
        resultMap.put("PHY111","4.0 3.0");*/
        //resultMap.put("MAT216","4.0 3.0");
        //resultMap.put("PHY111","4.0 3.0");

       /* resultMap.put("SOC101","4.0 3.0");
        resultMap.put("CSE461","4.0 3.0");
        resultMap.put("MGT211","4.0 3.0");
        resultMap.put("EC0101","4.0 3.0");
        resultMap.put("CSE400","4.0 4.0");*/






    }

    //Sorted Linked HashMap
    Map <String, String> sortedResultMap = new LinkedHashMap <String, String>();
    public void sortedMap(){
        resultMap.entrySet().stream()
                .sorted((k1, k2) -> -k2.getValue().compareTo(k1.getValue()))
                .forEach(k -> sortedResultMap.put(k.getKey(), k.getValue()));
    }

}














/*

    Map<String, Double> resultMap = new LinkedHashMap<String, Double>();
    public void arrayToMap(){
        for (int i=0; i<finalCourses.length; i++){
            String[] check= finalCourses[i].split(" ");
            if(finalCourses[i].contains("(NT)")){

            }
            else{
                resultMap.put(check[0],Double.parseDouble(check[check.length-1]));
            }
        }
    }

    //Sorted Linked HashMap
    Map <String, Double> sortedResultMap = new LinkedHashMap <String, Double>();
    public void sortedMap(){
        resultMap.entrySet().stream()
                .sorted((k1, k2) -> -k2.getValue().compareTo(k1.getValue()))
                .forEach(k -> sortedResultMap.put(k.getKey(), k.getValue()));
    }*/
