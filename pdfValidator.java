public class pdfValidator {
    String[] spText;

    public void validate(String[] txt){
        spText=txt;

        if (spText[0].contains("Student ID:")&&spText[1].contains("Name:")&&spText[2].contains("PROGRAM :")){
            System.out.println("Yes");
        }
        else{
            System.out.println("NO");
            System.out.println("WRONG PDF. CHECK PDF. Try again");
            System.out.println(spText[spText.length-1]);
            System.out.println(spText[0]);
            System.out.println(spText[1]);
            System.out.println(spText[2]);
            System.exit(0);
        }



    }


}
