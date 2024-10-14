package com.example.vietisbaitapbuoi3.services;

import com.example.vietisbaitapbuoi3.DAO.entities.Account;
import com.example.vietisbaitapbuoi3.DAO.entities.Department;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.AccountRequestDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.dtos.DepartmentResponseDTO;
import com.example.vietisbaitapbuoi3.DAO.entities.enums.Level;
import com.example.vietisbaitapbuoi3.DAO.repositories.AccountRepository;
import com.example.vietisbaitapbuoi3.DAO.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentSeviceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        department = Department.builder()
                .name("Kiem toan")
                .code("VIP")
                .id(1L)
                .build();
    }


    @Test
    public void testGetAllDepartment() {
        when(accountRepository.findAll()).thenReturn(List.of());
        ResponseEntity<List<DepartmentResponseDTO>> result = departmentService.getAllDepartment();
        assertNotNull(result);
    }

    @Test
    public void testGetAllLevel() {
        when(Level.AllValue()).thenReturn(List.of(new String()));
        ResponseEntity<List<String>> result = departmentService.getAllLevel();
        assertNotNull(result);
    }

    @Test
    public void testCreateDepartment_Success() {

        when(departmentRepository.save(department)).thenReturn(department);

        ResponseEntity<DepartmentResponseDTO> result = departmentService.createDepartment(department);

        assertNotNull(result);
        verify(departmentRepository, times(2)).save(department);
    }

    @Test
    public void testCreateDepartment_Failure() {
        when(departmentRepository.save(department)).thenThrow(new IllegalArgumentException("Invalid department data"));

        ResponseEntity<DepartmentResponseDTO> result = null;
        try {
            result = departmentService.createDepartment(department);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("Invalid department data", e.getMessage());
        }

        assertNull(result);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void updateDepartment_Success() {

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        ResponseEntity<DepartmentResponseDTO> result = departmentService.updateDepartment(department);

        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).save(any(Department.class));

    }

    @Test
    public void updateDepartment_Failure_NotFound() {

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        ResponseEntity<DepartmentResponseDTO> result = departmentService.updateDepartment(department);

        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void updateDepartment_Failure_NotSaved() {

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenThrow(new IllegalArgumentException("Invalid department data"));

        ResponseEntity<DepartmentResponseDTO> result;
        try {
            result = departmentService.updateDepartment(department);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("Invalid department data", e.getMessage());
        }
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testDeleteDepartment_Success() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        ResponseEntity<DepartmentResponseDTO> result = departmentService.deleteDepartment(department.getId());

        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(accountRepository, times(1)).clearDepartmentFromAccounts(anyLong());
        verify(departmentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteDepartment_Failure() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<DepartmentResponseDTO> result = departmentService.deleteDepartment(department.getId());

        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(accountRepository, never()).clearDepartmentFromAccounts(anyLong());
        verify(departmentRepository, never()).deleteById(anyLong());
    }


}
