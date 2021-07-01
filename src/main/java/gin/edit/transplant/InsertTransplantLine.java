package gin.edit.transplant;


import gin.SourceFile;
//import gin.SourceFileTree;
import gin.SourceFileLine;
//import gin.edit.Edit;
import gin.edit.line.LineEdit;

import java.util.List;
import java.util.Random;

public class InsertTransplantLine extends LineEdit {

    SourceFile donorSourceFile;

    public String sourceFile;
    public int sourceLine;
    public String destinationFile;
    public int destinationLine;

    public String donorSourceFileName;
    public int donorLine;

    public InsertTransplantLine(SourceFile sourceFile, SourceFile donorFile, Random rng) {
        SourceFileLine sf = (SourceFileLine)sourceFile;
        List<Integer> allLines = sf.getLineIDsNonEmptyOrComments(false);
        List<Integer> targetMethodLines = sf.getLineIDsNonEmptyOrComments(true);

        SourceFileLine sd = (SourceFileLine)donorFile;
        List<Integer> allLinesDonor = sd.getLineIDsNonEmptyOrComments(false);


        this.sourceFile = sourceFile.getFilename();
        this.sourceLine = allLines.get(rng.nextInt(allLines.size()));
        this.destinationFile = sourceFile.getFilename();
        this.destinationLine = targetMethodLines.get(rng.nextInt(targetMethodLines.size()));

        this.donorSourceFile = donorFile;
        this.donorSourceFileName = donorFile.getFilename();
        this.donorLine = allLinesDonor.get(rng.nextInt(targetMethodLines.size()));
    }

    public InsertTransplantLine(String donorSourceFile, int donorLine, String destinationFile, int destinationLine) {
        this.sourceFile = donorSourceFile;
        this.sourceLine = donorLine;
        this.destinationFile = destinationFile;
        this.destinationLine = destinationLine;
    }

    @Override
    public SourceFile apply(SourceFile sourceFile) {

        SourceFileLine sf = (SourceFileLine)sourceFile;
        SourceFileLine sd = (SourceFileLine)donorSourceFile;

        String line = (sd).getLine(donorLine);

        sf.insertLine(destinationLine, line);

        return sf;
    }

    /*
    @Override
    public String toString() {

        return "";
    }

    public static Edit fromString() {

        return new InsertTransplantLine(null, null, null);
    }
     */

}
