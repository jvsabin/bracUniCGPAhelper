import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class readPDF {
    public static void main(String[] args) {
        //Loading an existing document
        studentInfo studentInfo = new studentInfo();
        dataPrepare dataPrepare = new dataPrepare();
        retakeDecider retakeDecider = new retakeDecider();
        cgpaCalc cgpaCalc = new cgpaCalc();
        pdfValidator pdf=new pdfValidator();
        Scanner key=new Scanner(System.in);
        String text = "";
        Double cgpa=0.0;
        Double expectedCPGA=3.5;
        String retakeExemption=" ";
        try {
            File file = new File("D://result//GSFI.pdf");
            PDDocument document = PDDocument.load(file);
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //Retrieving text from PDF document
            text = pdfStripper.getText(document);
            document.close();
           /* while(expectedCPGA<2.0 || expectedCPGA>4.0) {
                System.out.println("Please enter your expected CGPA (range is 2.00 - 4.00)");
                expectedCPGA = key.nextDouble();
            }*/
           System.out.println("Enter courses which you dont want to retakes are(if there none leave it empty, otherwise input all )");
           //retakeExemption=key.next();
            retakeExemption=retakeExemption.toUpperCase();
            retakeExemption=retakeExemption.replaceAll("\\s+", "");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (InputMismatchException e){
            System.out.println("Provide valid expected CGPA in number such as 3.5");
        }
        catch (Exception e) {
            System.out.println("File cant read " + e);

        }
        //Data Pre Processing
        String[] spText = text.split("\n");
        for (int i = 0; i < spText.length; i++) {
            spText[i] = spText[i].trim();
            //System.out.println(spText[i]);
        }
        pdf.validate(spText);


        studentInfo.set(spText);
        cgpa= studentInfo.info();
        Map <String, String> sortedResultMap =dataPrepare.get(spText);
        cgpaCalc.set(sortedResultMap);
        retakeDecider.set(sortedResultMap,expectedCPGA,retakeExemption);



        Set< Map.Entry< String, String> > qt = sortedResultMap.entrySet();
        for (Map.Entry< String, String> me:qt){
            System.out.print(me.getKey()+": ");
            System.out.println(me.getValue());
        }
        Double actualCGPA=cgpaCalc.getCgpa();
        System.out.println("Current CGPA " + actualCGPA +" (Actual Calculation)");
        System.out.println("Expected CGPA " + expectedCPGA);


        if(expectedCPGA<=cgpa){
            System.out.println("Your current CGPA is already in sync with the desired CGPA. No need to retake." );
        }
        else{

        }
       retakeDecider.decider(0);
    }
}