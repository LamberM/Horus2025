package org.LamberM.samplecreator;

import org.LamberM.service.Folder;

public class FolderCreator {
    private final String name = "test";

    public Folder createSample(String size) {
        return new Folder() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getSize() {
                return size;
            }
        };
    }

    public Folder createSample() {
        return new Folder() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getSize() {
                return "MEDIUM";
            }
        };
    }

    public Folder createSampleWithNullName() {
        return new Folder() {
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

    public Folder createSampleWithNullSize() {
        return new Folder() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getSize() {
                return null;
            }
        };
    }

}
