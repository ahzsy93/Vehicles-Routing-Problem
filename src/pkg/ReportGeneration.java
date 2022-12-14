package pkg;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.ArrayList;

public class ReportGeneration {

    static double maintenanceCost;
    static int numberTrucks;
    static double yearlyCost;
    static double totalTravelDistance;
    ArrayList<Integer> truckPath;
    
    public ReportGeneration(){
        
    }
    
    private static String FILE= "Report.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void main() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            addMetaData(document);
            addTitlePage(document);
            addContent(document);

           document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void addMetaData(Document document) {
        document.addTitle("VRPTW Report");
        document.addSubject("Solution Analysis");
        document.addKeywords("VRP, Genetic, Heuristic, BCRC, PMX, GreedyPopulation");
        document.addAuthor("VRPTW Group");
        document.addCreator("iText, Phy Pharma");
    }
    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 10);
        // Lets write a big header
        preface.add(new Paragraph("Report Generation", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        document.add(preface);
        document.newPage();
    }
    private static void addContent(Document document) throws DocumentException {
        Paragraph subPara;
        for(int i=0; i<10; i++){
         subPara = new Paragraph("Solution "+(i+1), subFont);
         createList(subPara,i);
        document.add(subPara);
        }
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
        private static void createList(Paragraph subPara,int i) {
        VrpData gr = new VrpData();
        gr.readFromFile();
        Candidate can = gr.tmpCandidates.get(i);
        List list = new List(true, false, 10);
        list.add(new ListItem("Quaterly Maintainance cost: "+gr.getMaintenanceCost(can, 3)));
        list.add(new ListItem("Yearly Variable cost: "+gr.getVariableCost(can, 365)));
        list.add(new ListItem("No. of trucks to buy: "+gr.getTotalTrucks(can)));
        list.add(new ListItem("Total distance travelled by trucks: "+gr.getTotalDistance(can)));
        subPara.add(list);
         if(i==6)
         {
             Paragraph para = new Paragraph();
             addEmptyLine(para, 4);
             subPara.add(para);
         }
    }
}
