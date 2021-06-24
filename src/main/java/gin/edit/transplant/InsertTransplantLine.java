package gin.edit.transplant;

import gin.SourceFile;
import gin.edit.line.LineEdit;

public class InsertTransplantLine extends LineEdit {
    @Override
    public SourceFile apply(SourceFile sourceFile) {
        return sourceFile;
    }
}
