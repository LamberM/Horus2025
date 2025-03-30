package org.LamberM.service;

import org.LamberM.samplecreator.FolderCreator;
import org.LamberM.samplecreator.MultiFolderCreator;
import org.LamberM.samplecreator.TestFolder;
import org.LamberM.samplecreator.TestMultiFolder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FolderCabinetTest {
    public static final String LARGE = "LARGE";
    public static final String MEDIUM = "MEDIUM";
    public static final String SMALL = "SMALL";
    FolderCabinet folderCabinet;
    FolderCreator folderCreator = new FolderCreator();
    MultiFolderCreator multiFolderCreator = new MultiFolderCreator();

    @Nested
    class FindFolderBySize {
        @Test
        void givenFoldersMultiFolderAndNull_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", SMALL, multiFolderList);
            var folder = new TestFolder("test", LARGE);
            folderList.add(multiFolder);
            folderList.add(folder);
            folderList.add(null);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(LARGE);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder));
            assertTrue(result.contains(multiFolderList.get(1)));
        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", LARGE, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder(null, LARGE);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(LARGE);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertTrue(result.contains(multiFolder));
            assertTrue(result.contains(multiFolderList.get(1)));
            assertFalse(result.contains(folder2));

        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", MEDIUM, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", null);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(SMALL);
            //then
            assertNotNull(result);
            assertTrue(result.contains(multiFolderList.get(0)));
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", null, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(SMALL);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder2));
            assertFalse(result.contains(multiFolderList.get(1)));
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", MEDIUM));
            var multiFolder = new TestMultiFolder("test12", null, multiFolderList);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(MEDIUM);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertFalse(result.contains(multiFolderList.get(1)));
        }

        @Test
        void givenFoldersMultiFolderWithNullFolderList_whenFindFolderBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            var multiFolder = new TestMultiFolder("test12", MEDIUM, null);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize(MEDIUM);
            //then
            assertNotNull(result);
            assertTrue(result.contains(folder1));
            assertTrue(result.contains(multiFolder));
        }

        @Test
        void givenFoldersMultiFolder_whenFindFoldersBySize_thenThrowException() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", SMALL, multiFolderList);
            var folder = new TestFolder("test", LARGE);
            folderList.add(multiFolder);
            folderList.add(folder);
            //when
            folderCabinet = new FolderCabinet(folderList);
            //then
            var result = assertThrows(IllegalArgumentException.class, () -> folderCabinet.findFoldersBySize("EXTRALARGE"));
            assertEquals("Size does not equals: SMALL,MEDIUM or LARGE", result.getMessage());
        }
    }

    @Nested
    class FindFolderByName {
        @Test
        void givenFoldersMultiFolderAndNull_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test", SMALL, multiFolderList);
            var folder = new TestFolder("test", LARGE);
            folderList.add(multiFolder);
            folderList.add(folder);
            folderList.add(null);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(multiFolder), result);
        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", LARGE, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder(null, LARGE);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(folder1), result);
        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", MEDIUM, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", null);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test1");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(multiFolderList.get(0)), result);
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", null, multiFolderList);
            var folder1 = new TestFolder("test", LARGE);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test2");
            //then
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", MEDIUM));
            var multiFolder = new TestMultiFolder(null, LARGE, multiFolderList);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test3");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(folder2), result);
        }

        @Test
        void givenFoldersMultiFolderWithNullFolderList_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            var multiFolder = new TestMultiFolder("test12", MEDIUM, null);
            var folder1 = new TestFolder("test", MEDIUM);
            var folder2 = new TestFolder("test3", SMALL);
            folderList.add(multiFolder);
            folderList.add(folder1);
            folderList.add(folder2);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test12");
            //then
            assertNotNull(result);
            assertEquals(Optional.of(multiFolder), result);
        }

        @Test
        void givenFoldersMultiFolder_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            var multiFolder = new TestMultiFolder("test0", SMALL, multiFolderList);
            var folder = new TestFolder("test", LARGE);
            folderList.add(multiFolder);
            folderList.add(folder);
            //when
            folderCabinet = new FolderCabinet(folderList);
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
            List<Folder> folderList = new ArrayList<>();
            var multiFolder = multiFolderCreator.createSample("ttest", LARGE, List.of());
            folderList.add(multiFolder);
            folderList.add(folderCreator.createSample("test1", LARGE));
            folderList.add(null);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.count();
            //then
            //expected value got -1, because folderList will get filter where throw away nulls
            var expectedValue = multiFolder.getFolders().size() + folderList.size() - 1;
            assertEquals(expectedValue, result);
        }

        @Test
        void givenEveryNullPossibilities_whenCount_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            List<Folder> multiFolderList = List.of(new TestFolder("test1", SMALL), new TestFolder("test2", LARGE));
            List<Folder> multiFolderListWithNulls = List.of(new TestFolder("test12", null), new TestFolder(null, LARGE));
            folderList.add(null);
            folderList.add(new TestFolder(null, LARGE));
            folderList.add(new TestFolder("Test", null));
            folderList.add(new TestMultiFolder("Test", null, multiFolderList));
            folderList.add(new TestMultiFolder(null, LARGE, multiFolderList));
            folderList.add(new TestMultiFolder("test", LARGE, null));
            folderList.add(new TestMultiFolder("test", LARGE, multiFolderListWithNulls));
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.count();
            //then
            //expected value gave two MultiFolder, because their List<Folders> or list values were null, but MultiFolders get correct value
            var expectedValue = 2;
            assertEquals(expectedValue, result);
        }
    }
}