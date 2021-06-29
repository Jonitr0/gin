package gin.edit.transplant;

import com.github.javaparser.ast.stmt.Statement;
import gin.SourceFile;
import gin.SourceFileTree;
import gin.edit.Edit;
import gin.edit.statement.StatementEdit;

import java.util.List;
import java.util.Random;

public class InsertTransplantStatement extends StatementEdit implements TransplantEdit {

    protected SourceFile donorFile;
    protected int donorStatement;

    protected String destinationFilename;
    protected int destinationBlock;
    protected int destinationStatement;

    public InsertTransplantStatement(SourceFile sourceFile, SourceFile donorFile, Random rng) {
        SourceFileTree sf = (SourceFileTree)sourceFile;
        List<Integer> targetMethodBlocks = sf.getBlockIDsInTargetMethod();
        int insertBlock = targetMethodBlocks.get(rng.nextInt(targetMethodBlocks.size()));
        int insertStatementID = sf.getRandomInsertPointInBlock(insertBlock, rng);
        if (insertStatementID < 0) {
            insertStatementID = 0; // insert at start of empty block
        }

        this.destinationFilename = sourceFile.getFilename();
        this.destinationBlock = insertBlock;
        this.destinationStatement = insertStatementID;

        this.donorFile = donorFile;

        SourceFileTree dsf = (SourceFileTree)donorFile;
        donorStatement = dsf.getRandomStatementID(true, rng);
    }

    @Override
    public void setDonor(SourceFile file) {
        donorFile = file;
    }

    @Override
    public SourceFile getDonor() {
        return donorFile;
    }

    @Override
    public SourceFile apply(SourceFile sourceFile) {
        SourceFileTree sf = (SourceFileTree)sourceFile;

        Statement statement = ((SourceFileTree)donorFile).getStatement(donorStatement);

        sf.insertStatement(destinationBlock, destinationStatement, statement);

        return sf;
    }

    @Override
    public String toString() {
        //TODO
        return this.getClass().getCanonicalName() + " \"" + donorFile.getFilename() + "\":" + donorStatement + " -> \"" + destinationFilename + "\":" + destinationStatement;
    }

    public static Edit fromString() {
        //TODO
        return new InsertTransplantStatement(null, null, null);
    }
}
