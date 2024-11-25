package org.csystem.util.data.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ICrudRepositoryTest {

    private final ICrudRepository<String, Integer> mockRepository = Mockito.mock(ICrudRepository.class);

    @Test
    void testDeleteAllById() {
        List<Integer> ids = Arrays.asList(1, 2, 3);

        doNothing().when(mockRepository).deleteAllById(ids);
        mockRepository.deleteAllById(ids);

        verify(mockRepository, times(1)).deleteAllById(ids);
    }

    @Test
    void testDeleteById() {
        Integer id = 1;

        doNothing().when(mockRepository).deleteById(id);
        mockRepository.deleteById(id);

        verify(mockRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        Integer id = 1;
        when(mockRepository.existsById(id)).thenReturn(true);

        assertTrue(mockRepository.existsById(id));

        when(mockRepository.existsById(id)).thenReturn(false);
        assertFalse(mockRepository.existsById(id));
    }

    @Test
    void testFindAllById() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<String> expectedEntities = Arrays.asList("Entity1", "Entity2");

        when(mockRepository.findAllById(ids)).thenReturn(expectedEntities);

        Iterable<String> result = mockRepository.findAllById(ids);
        assertEquals(expectedEntities, result);
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Optional<String> expectedEntity = Optional.of("Entity1");

        when(mockRepository.findById(id)).thenReturn(expectedEntity);

        Optional<String> result = mockRepository.findById(id);
        assertTrue(result.isPresent());
        assertEquals("Entity1", result.get());

        when(mockRepository.findById(id)).thenReturn(Optional.empty());
        result = mockRepository.findById(id);
        assertFalse(result.isPresent());
    }
}