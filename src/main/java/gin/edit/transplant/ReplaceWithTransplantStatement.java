package gin.edit.transplant;

import java.util.Random;

import com.github.javaparser.ast.stmt.Statement;

import gin.SourceFile;
import gin.SourceFileTree;
import gin.edit.Edit;
import gin.edit.statement.StatementEdit;

public class ReplaceWithTransplantStatement extends StatementEdit {

    public String sourceFilename;
    public int sourceStatement;
    public String destinationFilename;
    public int destinationStatement;

    /** 
     * create a random replacestatement for the given sourcefile, using the provided RNG
     * @param sourceFile to create an edit for
     * @param rng random number generator, used to choose the target statements
     * */
    public ReplaceWithTransplantStatement(SourceFile sourceFile, SourceFile donorFile, Random rng) {
        SourceFileTree sf = (SourceFileTree)sourceFile;
	SourceFileTree df = (SourceFileTree)donorFile;
        
        sourceFilename = donorFile.getFilename();
        destinationFilename = sourceFile.getFilename();
        
        // source can be anywhere in the class
        sourceStatement = df.getRandomStatementID(false, rng);
        
        // target is in target method only
        destinationStatement = sf.getRandomStatementID(true, rng);
    }
    
    public ReplaceWithTransplantStatement(String sourceFilename, int sourceStatement, String destinationFilename, int destinationStatement) {
        this.sourceFilename = sourceFilename;
        this.sourceStatement = sourceStatement;
        this.destinationFilename = destinationFilename;
        this.destinationStatement = destinationStatement;
    }
    
    @Override
    public SourceFile apply(SourceFile sourceFile) {
        
        if (sourceStatement == destinationStatement) {
            return sourceFile; // no-op
        }
        
        SourceFileTree sf = (SourceFileTree)sourceFile;
        
        Statement source = sf.getStatement(sourceStatement);
        Statement destination = sf.getStatement(destinationStatement);
        
        if ((source == null) || (destination == null)) {
            return sf; // targeting a deleted location just does nothing.
        }
        
        
        // we clone the replacement node, so we don't end up getting confused between the two (that would prevent us replacing statements within the same parent node)
        try {
            return sf.replaceNode(destinationStatement, source.clone());
        } catch (ClassCastException e) { // JavaParser sometimes throws this if the statements don't match
            return null;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + " \"" + sourceFilename + "\":" + sourceStatement + " -> \"" + destinationFilename + "\":" + destinationStatement;
    }

    public static Edit fromString(String description) {
            String[] tokens = description.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        String[] srcTokens = tokens[1].split(":");
        String srcFilename = srcTokens[0].replace("\"", "");
        int source = Integer.parseInt(srcTokens[1]);
        String[] destTokens = tokens[3].split(":");
        String destFilename = destTokens[0].replace("\"", "");
        int destination = Integer.parseInt(destTokens[1]);
        return new ReplaceWithTransplantStatement(srcFilename, source, destFilename, destination);
    }
    
}
