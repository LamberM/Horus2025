package org.LamberM.samplecreator;

import org.LamberM.service.Folder;
import org.LamberM.service.MultiFolder;

import java.util.List;

public class MultiFolderCreator {
    public MultiFolder createSample(String name, String size, List<Folder> folderList) {
        return new TestMultiFolder(name, size, folderList);
    }
}
