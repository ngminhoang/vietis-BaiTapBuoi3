package com.example.vietisbaitapbuoi3.controllers;

import com.example.vietisbaitapbuoi3.entities.Department;
import com.example.vietisbaitapbuoi3.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

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
    public void testGetAllDepartments() throws Exception {
        List<Department> departments = List.of(department);
        when(departmentService.getAllDepartment()).thenReturn(ResponseEntity.ok(departments));

        mockMvc.perform(get("/api/admin/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Kiem toan"))
                .andExpect(jsonPath("$[0].code").value("VIP"));
    }

    @Test
    public void testCreate() throws Exception {
        when(departmentService.createDepartment(any(Department.class))).thenReturn(ResponseEntity.ok(department));

        mockMvc.perform(post("/api/admin/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kiem toan\",\"code\":\"VIP\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kiem toan"))
                .andExpect(jsonPath("$.code").value("VIP"));
    }

    @Test
    public void testUpdate() throws Exception {
        when(departmentService.updateDepartment(any(Department.class))).thenReturn(ResponseEntity.ok(department));

        mockMvc.perform(put("/api/admin/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kiem toan Updated\",\"code\":\"VIP\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kiem toan"))
                .andExpect(jsonPath("$.code").value("VIP"));
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;
        when(departmentService.deleteDepartment(id)).thenReturn(ResponseEntity.ok(department));

        mockMvc.perform(delete("/api/admin/departments/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kiem toan"))
                .andExpect(jsonPath("$.code").value("VIP"));
    }
}
