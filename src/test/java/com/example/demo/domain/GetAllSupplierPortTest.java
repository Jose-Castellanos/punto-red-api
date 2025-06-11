package com.example.demo.domain;

import com.example.demo.domain.api.usecase.GetAllSupplierPort;
import com.example.demo.domain.model.Supplier;
import com.example.demo.domain.spi.ISupplierClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllSupplierPortTest {

    private ISupplierClientPort supplierClientPort;
    private GetAllSupplierPort getAllSupplierPort;

    @BeforeEach
    void setUp() {
        supplierClientPort = mock(ISupplierClientPort.class);
        getAllSupplierPort = new GetAllSupplierPort(supplierClientPort);
    }

    @Test
    void getSuppliers_shouldReturnSupplierList() {

        Supplier supplier1 = new Supplier();
        supplier1.setId("SUP-001");
        supplier1.setName("Supplier A");
        Supplier supplier2 = new Supplier();
        supplier1.setId("SUP-002");
        supplier1.setName("Supplier B");
        List<Supplier> expectedSuppliers = Arrays.asList(supplier1, supplier2);

        when(supplierClientPort.getSuppliers()).thenReturn(expectedSuppliers);


        List<Supplier> actualSuppliers = getAllSupplierPort.getSuppliers();


        assertEquals(expectedSuppliers, actualSuppliers);
        verify(supplierClientPort).getSuppliers();
    }
}
