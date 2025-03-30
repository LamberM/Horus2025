package org.LamberM.service;

import org.LamberM.samplecreator.FolderCreator;
import org.LamberM.samplecreator.MultiFolderCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FolderCabinetTest {
    FolderCabinet folderCabinet;
    FolderCreator folderCreator = new FolderCreator();
    MultiFolderCreator multiFolderCreator = new MultiFolderCreator();

    @Nested
    class FindFolderBySize {
        @Test
        void givenFoldersMultiFolderAndNull_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(null);
            folderList.add(folderCreator.createSample("LARGE"));
            folderList.add(multiFolderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("LARGE");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());

        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSample());
            folderList.add(folderCreator.createSample("SMALL"));
            folderList.add(folderCreator.createSampleWithNullName());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("SMALL");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSample());
            folderList.add(folderCreator.createSample("LARGE"));
            folderList.add(folderCreator.createSampleWithNullSize());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("LARGE");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenNullFoldersWithNullSizeAndNameMultiFolderWithFoldersWithNullSizeAndName_whenFindFoldersBySize_thenEmptyResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullSize());
            folderList.add(multiFolderCreator.createSampleWithNullName());
            folderList.add(multiFolderCreator.createSampleWithFoldersWithNulls());
            folderList.add(multiFolderCreator.createSampleWithNullFolders());
            folderList.add(null);
            folderList.add(folderCreator.createSampleWithNullName());
            folderList.add(folderCreator.createSampleWithNullSize());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("MEDIUM");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullSize());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("MEDIUM");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFoldersBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullName());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("MEDIUM");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullFolderList_whenFindFolderBySize_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithFoldersWithNulls());
            folderList.add(folderCreator.createSample("SMALL"));
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFoldersBySize("SMALL");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolder_whenFindFoldersBySize_thenThrowException() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(folderCreator.createSample());
            folderList.add(multiFolderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            //then
            var result = Assertions.assertThrows(IllegalArgumentException.class, () -> folderCabinet.findFoldersBySize("EXTRALARGE"));
            Assertions.assertEquals("Size does not equals: SMALL,MEDIUM OR RANGE", result.getMessage());
        }
    }

    @Nested
    class FindFolderByName {
        @Test
        void givenFoldersMultiFolderAndNull_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(null);
            folderList.add(folderCreator.createSample());
            folderList.add(multiFolderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFolderWithNullNameMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSample());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSampleWithNullName());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFolderWithNullSizeMultiFolder_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSample());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSampleWithNullSize());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenNullFoldersWithNullSizeAndNameMultiFolderWithFoldersWithNullSizeAndName_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullSize());
            folderList.add(multiFolderCreator.createSampleWithNullName());
            folderList.add(multiFolderCreator.createSampleWithFoldersWithNulls());
            folderList.add(multiFolderCreator.createSampleWithNullFolders());
            folderList.add(null);
            folderList.add(folderCreator.createSampleWithNullName());
            folderList.add(folderCreator.createSampleWithNullSize());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullSize_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullSize());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullName_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullName());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolderWithNullFolderList_whenFindFolderByName_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithFoldersWithNulls());
            folderList.add(folderCreator.createSample());
            folderList.add(folderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertFalse(result.isEmpty());
        }

        @Test
        void givenFoldersMultiFolder_whenFindFolderByName_thenEmptyResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(folderCreator.createSample());
            folderList.add(multiFolderCreator.createSample());
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.findFolderByName("test0000");
            //then
            Assertions.assertNotNull(result);
            Assertions.assertTrue(result.isEmpty());
        }
    }

    @Nested
    class Count {
        @Test
        void givenTwoFoldersMultiFolderNull_whenCount_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            var multiFolder = multiFolderCreator.createSample();
            folderList.add(multiFolder);
            folderList.add(folderCreator.createSample());
            folderList.add(null);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.count();
            //then
            //expected value got -1, because folderList will get filter where throw away nulls
            var expectedValue = multiFolder.getFolders().size() + folderList.size() - 1;
            Assertions.assertEquals(expectedValue, result);
        }

        @Test
        void givenEveryNullPossibilities_whenCount_thenGetResult() {
            //given
            List<Folder> folderList = new ArrayList<>();
            folderList.add(multiFolderCreator.createSampleWithNullFolders());
            folderList.add(multiFolderCreator.createSampleWithFoldersWithNulls());
            folderList.add(multiFolderCreator.createSampleWithNullName());
            folderList.add(multiFolderCreator.createSampleWithNullSize());
            folderList.add(folderCreator.createSampleWithNullName());
            folderList.add(folderCreator.createSampleWithNullSize());
            folderList.add(null);
            //when
            folderCabinet = new FolderCabinet(folderList);
            var result = folderCabinet.count();
            //then
            //expected value gave two MultiFolder, because their List<Folders> or list values were null, but MultiFolders get correct value
            var expectedValue = 2;
            Assertions.assertEquals(expectedValue, result);
        }
    }
}