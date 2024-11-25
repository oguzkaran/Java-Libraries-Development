package org.csystem.util.data.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IRepositoryTest {

    private final IRepository<String> mockRepository = Mockito.mock(IRepository.class);

    @Test
    void testCount() {
        when(mockRepository.count()).thenReturn(10L);

        assertEquals(10L, mockRepository.count());
        verify(mockRepository, times(1)).count();
    }

    @Test
    void testDelete() {
        String entity = "Entity1";
        doNothing().when(mockRepository).delete(entity);

        mockRepository.delete(entity);

        verify(mockRepository, times(1)).delete(entity);
    }

    @Test
    void testDeleteAll() {
        doNothing().when(mockRepository).deleteAll();

        mockRepository.deleteAll();

        verify(mockRepository, times(1)).deleteAll();
    }

    @Test
    void testDeleteAllEntities() {
        List<String> entities = Arrays.asList("Entity1", "Entity2", "Entity3");
        doNothing().when(mockRepository).deleteAll(entities);

        mockRepository.deleteAll(entities);

        verify(mockRepository, times(1)).deleteAll(entities);
    }

    @Test
    void testFindAll() {
        List<String> entities = Arrays.asList("Entity1", "Entity2", "Entity3");
        when(mockRepository.findAll()).thenReturn(entities);

        Iterable<String> result = mockRepository.findAll();
        assertEquals(entities, result);
    }

    @Test
    void testSave() {
        String entity = "Entity1";
        when(mockRepository.save(entity)).thenReturn(entity);

        String result = mockRepository.save(entity);
        assertEquals(entity, result);
    }

    @Test
    void testSaveAll() {
        List<String> entities = Arrays.asList("Entity1", "Entity2");
        when(mockRepository.saveAll(entities)).thenReturn(entities);

        Iterable<String> result = mockRepository.saveAll(entities);
        assertEquals(entities, result);
    }
}