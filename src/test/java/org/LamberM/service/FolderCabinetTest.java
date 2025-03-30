package org.LamberM.service;

import org.LamberM.samplecreator.FolderCreator;
import org.LamberM.samplecreator.MultiFolderCreator;
import org.LamberM.samplecreator.TestFolder;
import org.LamberM.samplecreator.TestMultiFolder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.LamberM.service.FolderCabinet.LARGE;
import static org.LamberM.service.FolderCabinet.MEDIUM;
import static org.LamberM.service.FolderCabinet.SMALL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FolderCabinetTest {
    FolderCabinet folderCabinet;
    FolderCreator folderCreator = new FolderCreator();
    MultiFolderCreator multiFolderCreator = new MultiFolderCreator();

    @Nested
    class FindFolderBySize {
        @Test
        void givenMultiFolderWithMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folder1 = new TestFolder("test1", LARGE);
            List<Folder> multiFolders1 = List.of(folder1);
            var multiFolder1 = new TestMultiFolder("test0", LARGE, multiFolders1);
            var folder2 = new TestFolder("test2", SMALL);
            List<Folder> multiFolders2 = List.of(folder2, multiFolder1);
            var multiFolder2 = new TestMultiFolder("test00", MEDIUM, multiFolders2);
            folders.add(multiFolder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(LARGE);
            System.out.println(result);
            //then
            assertTrue(result.contains(folder1));
            assertTrue(result.contains(multiFolder1));
            assertEquals(2, result.size());
        }

        @Test
        void givenFoldersMultiFolderAndNull_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test2", LARGE);
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), folderInList);
            var multiFolder = new TestMultiFolder("test0", SMALL, multiFolderList);
            var folder = new TestFolder("test", LARGE);
            folders.add(multiFolder);
            folders.add(folder);
            folders.add(null);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(LARGE);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder));
            assertTrue(result.contains(folderInList));
        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test2", LARGE);
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), folderInList);
            var multiFolder = new TestMultiFolder("test0", LARGE, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder(null, LARGE);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(LARGE);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertTrue(result.contains(multiFolder));
            assertTrue(result.contains(folderInList));
            assertFalse(result.contains(folder2));

        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test1", SMALL);
            List<Folder> multifolders = List.of(folderInList, new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", MEDIUM, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", null);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(SMALL);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folderInList));
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test2", LARGE);
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), folderInList);
            var multiFolder = new TestMultiFolder("test0", null, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(SMALL);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder2));
            assertFalse(result.contains(folderInList));
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test2", MEDIUM);
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), folderInList);
            var multiFolder = new TestMultiFolder("test12", null, multifolders);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(MEDIUM);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertFalse(result.contains(folderInList));
        }

        @Test
        void givenFoldersMultiFolderWithNullfolders_whenFindFolderBySize_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var multiFolder = new TestMultiFolder("test12", MEDIUM, null);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFoldersBySize(MEDIUM);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertTrue(result.contains(multiFolder));
        }

        @Test
        void givenFoldersMultiFolder_whenFindFoldersBySize_thenThrowException() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", SMALL, multifolders);
            var folder = new TestFolder("test", LARGE);
            folders.add(multiFolder);
            folders.add(folder);
            //when
            folderCabinet = new FolderCabinet(folders);
            //then
            var result = assertThrows(IllegalArgumentException.class, () -> folderCabinet.findFoldersBySize("EXTRALARGE"));
            assertEquals("Size does not equals: SMALL,MEDIUM or LARGE", result.getMessage());
        }
    }

    @Nested
    class FindFolderByName {
        @Test
        void givenMultiFolderWithMultiFolder_whenFindFoldersByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folder1 = new TestFolder("test1", LARGE);
            List<Folder> multiFolders1 = List.of(folder1);
            var multiFolder1 = new TestMultiFolder("test0", LARGE, multiFolders1);
            var folder2 = new TestFolder("test2", SMALL);
            List<Folder> multiFolders2 = List.of(folder2, multiFolder1);
            var multiFolder2 = new TestMultiFolder("test00", MEDIUM, multiFolders2);
            folders.add(multiFolder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test1");
            //then
            assertEquals(Optional.of(folder1), result);
        }

        @Test
        void givenFoldersMultiFolderAndNull_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test", SMALL, multifolders);
            var folder = new TestFolder("test", LARGE);
            folders.add(multiFolder);
            folders.add(folder);
            folders.add(null);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(multiFolder), result);
        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", LARGE, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder(null, LARGE);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(folder1), result);
        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var folderInList = new TestFolder("test1", SMALL);
            List<Folder> multifolders = List.of(folderInList, new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", MEDIUM, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", null);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test1");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(folderInList), result);
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", null, multifolders);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test2");
            //then
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", MEDIUM));
            var multiFolder = new TestMultiFolder(null, LARGE, multifolders);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test3");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(folder2), result);
        }

        @Test
        void givenFoldersMultiFolderWithNullfolders_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var multiFolder = new TestMultiFolder("test12", MEDIUM, null);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folders.add(multiFolder);
            folders.add(folder1);
            folders.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test12");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(multiFolder), result);
        }

        @Test
        void givenFoldersMultiFolder_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", SMALL, multifolders);
            var folder = new TestFolder("test", LARGE);
            folders.add(multiFolder);
            folders.add(folder);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.findFolderByName("test0000");
            //then
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class Count {
        @Test
        void givenTwoFoldersMultiFolderNull_whenCount_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            var multiFolder = multiFolderCreator.createSample("ttest", LARGE, List.of());
            folders.add(multiFolder);
            folders.add(folderCreator.createSample("test1", LARGE));
            folders.add(null);
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.count();
            //then
            //expected value got -1, because folders will get filter where throw away nulls
            var expectedValue = 2;
            assertEquals(expectedValue, result);
        }

        @Test
        void givenEveryNullPossibilities_whenCount_thenGetResult() {
            //given
            List<Folder> folders = new ArrayList<>();
            List<Folder> multifolders = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            List<Folder> multifoldersWithNulls = List.of(new TestFolder("test12", null), new TestFolder(null, LARGE));
            folders.add(null);
            folders.add(new TestFolder(null, LARGE));
            folders.add(new TestFolder("Test", null));
            folders.add(new TestMultiFolder("Test", null, multifolders));
            folders.add(new TestMultiFolder(null, LARGE, multifolders));
            folders.add(new TestMultiFolder("test", LARGE, null));
            folders.add(new TestMultiFolder("test", LARGE, multifoldersWithNulls));
            //when
            folderCabinet = new FolderCabinet(folders);
            var result = folderCabinet.count();
            //then
            //expected value gave two MultiFolder, because their List<Folders> or list values were null, but MultiFolders get correct value
            var expectedValue = 2;
            assertEquals(expectedValue, result);
        }
    }
}