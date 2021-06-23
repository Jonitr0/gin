package gin.edit.transplant;

import gin.SourceFile;

public interface TransplantEdit {
    void setDonor(SourceFile file);
    SourceFile getDonor();

}
