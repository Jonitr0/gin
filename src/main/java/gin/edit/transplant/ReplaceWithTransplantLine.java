package gin.edit.transplant;

import java.util.List;
import java.util.Random;

import gin.SourceFile;
import gin.SourceFileLine;
import gin.edit.Edit;

import gin.edit.line.LineEdit;

//import javax.swing.undo.StateEdit;

public class ReplaceWithTransplantLine extends LineEdit {
    SourceFile donorSourceFile;

    public String sourceFile; 
    public int sourceLine;
    public String destinationFile;
    public int destinationLine;

    public String donorSourceFileName;
    public int donorLine;

    /** 
     * create a random ReplaceLine for the given SourceFile and the given donorFile, using the provided RNG
     * @param sourceFile to create an edit for
     * @param rng random number generator, used to choose the donor line and the target line
     * */
    public ReplaceWithTransplantLine(SourceFile sourceFile, SourceFile donorFile, Random rng) {
        SourceFileLine sf = (SourceFileLine)sourceFile;
        List<Integer> allLines = sf.getLineIDsNonEmptyOrComments(false);
        List<Integer> targetMethodLines = sf.getLineIDsNonEmptyOrComments(true);

        SourceFileLine sd = (SourceFileLine)donorFile;
        List<Integer> allLinesDonor = sd.getLineIDsNonEmptyOrComments(false);
        //List<Integer> targetMethodLinesDonor = sd.getLineIDsNonEmptyOrComments(true);

        this.sourceFile = sourceFile.getFilename();
        this.sourceLine = allLines.get(rng.nextInt(allLines.size()));
        this.destinationFile = sourceFile.getFilename();
        this.destinationLine = targetMethodLines.get(rng.nextInt(targetMethodLines.size()));

        this.donorSourceFile = donorFile;
        this.donorSourceFileName = donorFile.getFilename();
        this.donorLine = allLinesDonor.get(rng.nextInt(targetMethodLines.size()));
    }
    
    public ReplaceWithTransplantLine(String donorSourceFile, int donorLine, String destinationFile, int destinationLine) {
        this.sourceFile = donorSourceFile;
        this.sourceLine = donorLine;
        this.destinationFile = destinationFile;
        this.destinationLine = destinationLine;
    }
    
    @Override
    public SourceFile apply(SourceFile sourceFile) {
        // if (sourceLine == destinationLine) {
        //     return sourceFile; // no-op
        // }
        
        SourceFileLine sf = (SourceFileLine)sourceFile;
        SourceFileLine sd = (SourceFileLine)donorSourceFile;
        //String lineSource = sf.getLine(sourceLine);
        String lineDonor = sd.getLine(donorLine);
        String lineDestination = sf.getLine(sourceLine);
        if ((lineDonor != null) && (lineDestination != null)) { // neither source or target is already deleted
            sf = sf.removeLine(destinationLine);
            sf = sf.insertLine(destinationLine, lineDonor);
        }
            
        return sf;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + " \"" + donorSourceFileName + "\":" + donorLine + " -> \""
                + destinationFile + "\":" + destinationLine;
    }

    // public static Edit fromString(String description) {
    //         String[] tokens = description.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    //     String source = tokens[1];
    //     String destination = tokens[3];
    //     String[] sourceTokens = source.split(":");
    //     String sourceFile = sourceTokens[0].replace("\"", "");
    //     int sourceLine = Integer.parseInt(sourceTokens[1]);
    //     String[] destTokens = destination.split(":");
    //     String destFile = destTokens[0].replace("\"", "");
    //     int destLine = Integer.parseInt(destTokens[1]);
    //     return new ReplaceLine(sourceFile, sourceLine, destFile, destLine);
    // }


}
