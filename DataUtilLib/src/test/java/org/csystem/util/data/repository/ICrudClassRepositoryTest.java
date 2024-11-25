package org.csystem.util.data.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ICrudClassRepositoryTest {

    private final ICrudClassRepository<String> mockRepository = Mockito.mock(ICrudClassRepository.class);

    @Test
    void testExistsForAssertTrue() {
        String entity = "Test Entity";
        when(mockRepository.exists(entity)).thenReturn(true);

        assertTrue(mockRepository.exists(entity));
    }

    @Test
    void testExistsForAssertFalse() {
        String entity = "Test Entity";when(mockRepository.exists(entity)).thenReturn(true);

        when(mockRepository.exists(entity)).thenReturn(false);
        assertFalse(mockRepository.exists(entity));
    }

    @Test
    void testFindBy() {
        String entity = "Test Entity";
        List<String> expectedEntities = Arrays.asList("Entity1", "Entity2");

        when(mockRepository.findBy(entity)).thenReturn(expectedEntities);

        Iterable<String> result = mockRepository.findBy(entity);
        assertEquals(expectedEntities, result);
    }

    @Test
    void testFindFirst() {
        String entity = "Test Entity";
        Optional<String> expectedEntity = Optional.of("Entity1");

        when(mockRepository.findFirst(entity)).thenReturn(expectedEntity);

        Optional<String> result = mockRepository.findFirst(entity);
        assertTrue(result.isPresent());
        assertEquals("Entity1", result.get());

        when(mockRepository.findFirst(entity)).thenReturn(Optional.empty());
        result = mockRepository.findFirst(entity);
        assertFalse(result.isPresent());
    }
}