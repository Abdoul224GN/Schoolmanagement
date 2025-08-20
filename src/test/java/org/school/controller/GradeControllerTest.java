package org.school.controller;

import org.junit.jupiter.api.Test;
import org.school.dto.GradeRequestDTO;
import org.school.dto.GradeResponseDTO;
import org.school.entity.Grade;
import org.school.mapper.GradeMapper;
import org.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GradeController.class)
class GradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GradeService gradeService;

    /*
    @Test
    void shouldReturnAllGrades() throws Exception {
        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setName("test");
        Grade grade2 = new Grade();
        grade2.setId(1L);
        grade2.setName("test2");
        List<GradeResponseDTO> list = List.of(GradeMapper.toDTO(grade1), GradeMapper.toDTO(grade2));
        when(gradeService.getAllGrade()).thenReturn(list);

        mockMvc.perform(get("/api/grade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[1].name").value("test2"));
    }
*/
    @Test
    void shouldReturnGradeById() throws Exception {
        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setName("test");
        when(gradeService.getGradeById(1L)).thenReturn(GradeMapper.toDTO(grade1));

        mockMvc.perform(get("/api/grade/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void shouldReturnCreatedGrade() throws Exception {
        String json = """
                {
                    "name": "CM2"
                }
                """;
        GradeRequestDTO gradeRequestDTO = new GradeRequestDTO("CM2");
        Grade returnedGrade = new Grade();
        returnedGrade.setId(1L);
        returnedGrade.setName("test");
        when(gradeService.createGrade(gradeRequestDTO)).thenReturn(GradeMapper.toDTO(returnedGrade));
        mockMvc.perform(post("/api/grade").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void shouldReturnUpdatedGrade() throws Exception {
        String json = """
                {
                    "name": "CM2"
                }
                """;
        GradeRequestDTO gradeRequestDTO = new GradeRequestDTO("CM2");
        Grade returnedGrade = new Grade();
        returnedGrade.setId(1L);
        returnedGrade.setName("test");
        when(gradeService.updateGrade(1L, gradeRequestDTO)).thenReturn(GradeMapper.toDTO(returnedGrade));
        mockMvc.perform(put("/api/grade/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    void shouldDeleteGrade() throws Exception {
        doNothing().when(gradeService).deleteGrade(1L);

        mockMvc.perform(delete("/api/grade/1"))
                .andExpect(status().isOk());

        verify(gradeService, times(1)).deleteGrade(1L);
    }
}