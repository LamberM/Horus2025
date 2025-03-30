package org.LamberM.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FolderCabinet implements Cabinet {
    public static final String LARGE = "LARGE";
    public static final String MEDIUM = "MEDIUM";
    public static final String SMALL = "SMALL";
    private final List<Folder> folders;

    public FolderCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size.equals(SMALL) || size.equals(MEDIUM) || size.equals(LARGE)) {
            return findFolderFromListBySize(folders, size);
        } else {
            throw new IllegalArgumentException("Size does not equals: %s,%s or %s".formatted(SMALL, MEDIUM, LARGE));
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
        return createOneList(folders).stream()
                .filter(folder -> folder.getName().equals(name))
                .findAny();
    }

    private List<Folder> findFolderFromListBySize(List<Folder> folders, String size) {
        return createOneList(folders).stream()
                .filter(folder -> folder.getSize().equals(size))
                .toList();
    }

    private List<Folder> filterNullInList(List<Folder> folders) {
        return folders.stream()
                .filter(Objects::nonNull)
                .filter(folder -> folder.getName() != null)
                .filter(folder -> folder.getSize() != null)
                .toList();
    }

    private List<Folder> createOneList(List<Folder> folders) {
        List<Folder> folderList = filterNullInList(folders);
        List<MultiFolder> multiFolders = new ArrayList<>();
        for (Folder folder : folderList) {
            if (folder instanceof MultiFolder multiFolder && multiFolder.getFolders() != null) {
                multiFolders.add(multiFolder);
            }
        }
        for (MultiFolder multiFolder : multiFolders) {
            folderList = Stream.concat(folderList.stream(), filterNullInList(multiFolder.getFolders()).stream()).toList();
        }
        return folderList;
    }
}
