package org.LamberM.samplecreator;

import org.LamberM.service.Folder;
import org.LamberM.service.MultiFolder;

import java.util.ArrayList;
import java.util.List;

public class MultiFolderCreator {
    public MultiFolder createSample() {
        List<Folder> folders = new ArrayList<>();
        FolderCreator folderCreator = new FolderCreator();
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        return new MultiFolder() {
            @Override
            public List<Folder> getFolders() {
                return folders;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getSize() {
                return "";
            }
        };
    }
    public MultiFolder createSampleWithNullFolders() {

        return new MultiFolder() {
            @Override
            public List<Folder> getFolders() {
                return null;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getSize() {
                return "";
            }
        };
    }

    public MultiFolder createSampleWithFoldersWithNulls() {
        List<Folder> folders = new ArrayList<>();
        FolderCreator folderCreator = new FolderCreator();
        folders.add(folderCreator.createSampleWithNullSize());
        folders.add(folderCreator.createSampleWithNullName());
        return new MultiFolder() {
            @Override
            public List<Folder> getFolders() {
                return folders;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getSize() {
                return "";
            }
        };
    }

    public MultiFolder createSampleWithNullName() {
        List<Folder> folders = new ArrayList<>();
        FolderCreator folderCreator = new FolderCreator();
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        return new MultiFolder() {
            @Override
            public List<Folder> getFolders() {
                return folders;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getSize() {
                return "";
            }
        };
    }

    public MultiFolder createSampleWithNullSize() {
        List<Folder> folders = new ArrayList<>();
        FolderCreator folderCreator = new FolderCreator();
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        folders.add(folderCreator.createSample());
        return new MultiFolder() {
            @Override
            public List<Folder> getFolders() {
                return folders;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getSize() {
                return null;
            }
        };
    }
}
