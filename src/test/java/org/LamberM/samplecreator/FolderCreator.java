package org.LamberM.samplecreator;

import org.LamberM.service.Folder;

public class FolderCreator {
    public Folder createSample(String name, String size) {
        return new TestFolder(name, size);
    }
}
