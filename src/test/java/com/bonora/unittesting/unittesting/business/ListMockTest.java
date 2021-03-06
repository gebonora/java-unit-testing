package com.bonora.unittesting.unittesting.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {
    List mock = mock(List.class);


    @Test
    public void sizeBasic() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    @Test
    public void returnWithParameters() {
        when(mock.get(0)).thenReturn("gonzalo");
        assertEquals("gonzalo", mock.get(0));
        assertNull(mock.get(1));
    }

    @Test
    public void returnWithGenericParameters() {
        when(mock.get(anyInt())).thenReturn("gonzalo");
        assertEquals("gonzalo", mock.get(0));
        assertEquals("gonzalo", mock.get(1));
    }

    @Test
    public void verificationBasics() {
        mock.get(0);
        mock.get(1);

        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, never()).get(4);
        verify(mock, atLeast(2)).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, atLeastOnce()).get(0);
    }
}
