package org.LamberM.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FolderCabinet implements Cabinet {
    private final List<Folder> folders;

    public FolderCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size.equals("SMALL") || size.equals("MEDIUM") || size.equals("LARGE")) {
            return findFolderFromListBySize(folders, size);
        } else {
            throw new IllegalArgumentException("Size does not equals: SMALL,MEDIUM OR RANGE");
        }
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return findFolderFromListByName(folders, name);
    }

    @Override
    public int count() {
        return createOneList(folders).size();
    }

    private Optional<Folder> findFolderFromListByName(List<Folder> folders, String name) {
        return createOneList(folders).stream().filter(folder -> folder.getName().equals(name)).findAny();
    }

    private List<Folder> findFolderFromListBySize(List<Folder> folders, String size) {
        return createOneList(folders).stream().filter(folder -> folder.getSize().equals(size)).toList();
    }

    private List<Folder> filterNullInList(List<Folder> folders) {
        List<Folder> folderList = new ArrayList<>();
        for (Folder folder : folders) {
            if (folder != null && folder.getName() != null && folder.getSize() != null) {
                folderList.add(folder);
            }
        }
        return folderList;
    }

    private List<Folder> createOneList(List<Folder> folders) {
        List<Folder> folderList = filterNullInList(folders);
        List<MultiFolder> multiFolders = new ArrayList<>();
        for (Folder folder : folderList) {
            if (folder instanceof MultiFolder multiFolder && multiFolder.getFolders() != null) {
                multiFolders.add(multiFolder);
            }

        }
        if (multiFolders.isEmpty()) {
            return folderList;
        } else {
            for (MultiFolder multiFolder : multiFolders) {
                folderList = Stream.concat(folderList.stream(), filterNullInList(multiFolder.getFolders()).stream()).toList();
            }
            return folderList;
        }
    }

}
