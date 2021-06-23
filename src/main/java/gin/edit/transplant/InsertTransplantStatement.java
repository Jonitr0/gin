package gin.edit.transplant;

import gin.SourceFile;
import gin.edit.statement.StatementEdit;

import javax.swing.undo.StateEdit;

public class InsertTransplantStatement extends StatementEdit implements TransplantEdit {
    SourceFile donorFile;

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
        return null;
    }
}
