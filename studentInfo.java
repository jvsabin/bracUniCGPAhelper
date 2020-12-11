public class studentInfo {
    String[] spText;
    public void set(String[] txt){
        spText=txt;
    }
    public Double info(){

        System.out.println(spText[1]);
        System.out.println(spText[0]);
        System.out.println(spText[2]);
        String[] courseComplete = spText[spText.length - 5].split(" ");
        //System.out.println("Number of Course Completed: " + Math.floor((Double.parseDouble(courseComplete[0]) / 3))); //course complete
        System.out.println("Credit Obtained: " + courseComplete[0]);
        System.out.println("CGPA: " + courseComplete[1]+" (from gradesheet)");
        return Double.parseDouble(courseComplete[1]);
    }
    //spText[3]="";
}
