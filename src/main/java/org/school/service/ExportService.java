package org.school.service;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.school.dto.ExportCriteria;
import org.school.entity.Parent;
import org.school.entity.ParentEleve;
import org.school.entity.Student;
import org.school.entity.Teacher;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExportService {

    private final TeacherRepository teacherRepository;
    StudentService studentService;
    StudentRepository studentRepository;

    public byte[] exportStudentsToExcel(ExportCriteria criteria) throws IOException {
        List<Student> students = studentRepository.findAll().stream()
                .filter(s -> criteria.classeId() == null ||
                        (s.getClasse() != null && s.getClasse().getId().equals(criteria.classeId())))
                .toList();
        List<String> columns = criteria.columns();
        if (columns == null || columns.isEmpty()) {
            columns = List.of("id", "firstName", "lastName", "sex", "birthDate", "address", "classe");
        }

        List<String> parentColumns = List.of(
                "Nom du père", "Profession du Père", "Téléphone du père",
                "Nom de la mère", "Profession de la mère", "Téléphone de la mère"
        );

        boolean includeParents = criteria.includeParents();

        List<String> allColumns = new ArrayList<>(columns);
        if (includeParents) {
            allColumns.addAll(parentColumns);
        }


        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Students");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.BLACK.getIndex());
            headerStyle.setFont(font);

            Row header = sheet.createRow(0);
            for (int i = 0; i < allColumns.size(); i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(allColumns.get(i));
                cell.setCellStyle(headerStyle);
            }


            int rowIdx = 1;
            for (Student s : students) {
                Row row = sheet.createRow(rowIdx++);
                int col = 0;

                for (String c : columns) {
                    switch (c) {
                        case "id" -> row.createCell(col++).setCellValue(s.getId());
                        case "firstName" -> row.createCell(col++).setCellValue(s.getFirstName());
                        case "lastName" -> row.createCell(col++).setCellValue(s.getLastName());
                        case "sex" -> row.createCell(col++).setCellValue(s.getSex().name());
                        case "birthDate" ->
                                row.createCell(col++).setCellValue(s.getBirthDate() != null ? s.getBirthDate().toString() : "");
                        case "address" ->
                                row.createCell(col++).setCellValue(s.getAddress() != null ? s.getAddress() : "");
                        case "classe" ->
                                row.createCell(col++).setCellValue(s.getClasse() != null ? s.getClasse().getName() : "");
                    }
                }

                if (includeParents) {

                    String fatherName = "", fatherProf = "", fatherPhone = "";
                    String motherName = "", motherProf = "", motherPhone = "";

                    for (ParentEleve pe : s.getParentRelations()) {
                        Parent p = pe.getParent();
                        if (p == null || pe.getTypeRelation() == null) continue;

                        switch (pe.getTypeRelation().toLowerCase()) {
                            case "père":
                                fatherName = p.getLastName() + " " + p.getLastName();
                                fatherProf = p.getOccupation();
                                fatherPhone = p.getPhone();
                                break;
                            case "mère":
                                motherName = p.getLastName() + " " + p.getLastName();
                                motherProf = p.getOccupation();
                                motherPhone = p.getPhone();
                                break;
                        }
                    }


                    row.createCell(col++).setCellValue(fatherName);
                    row.createCell(col++).setCellValue(fatherProf);
                    row.createCell(col++).setCellValue(fatherPhone);

                    row.createCell(col++).setCellValue(motherName);
                    row.createCell(col++).setCellValue(motherProf);
                    row.createCell(col++).setCellValue(motherPhone);
                }
            }
            for (int i = 0; i < allColumns.size(); i++) sheet.autoSizeColumn(i);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }

    public byte[] exportTeachersToExcel(ExportCriteria criteria) throws IOException {
        List<Teacher> teachers = teacherRepository.findAll().stream()
                .toList();

        List<String> columns = criteria.columns();
        if (columns == null || columns.isEmpty()) {
            columns = List.of("id", "firstName", "lastName", "sex", "birthDate", "phone", "email");
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Teachers");

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.BLACK.getIndex());
            headerStyle.setFont(font);

            Row header = sheet.createRow(0);
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns.get(i));
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (Teacher t : teachers) {
                Row row = sheet.createRow(rowIdx++);
                int col = 0;

                for (String c : columns) {
                    switch (c) {
                        case "id" -> row.createCell(col++).setCellValue(t.getId());
                        case "firstName" -> row.createCell(col++).setCellValue(t.getFirstName());
                        case "lastName" -> row.createCell(col++).setCellValue(t.getLastName());
                        case "sex" -> row.createCell(col++).setCellValue(t.getSex());
                        case "birthDate" ->
                                row.createCell(col++).setCellValue(t.getBirthDate() != null ? t.getBirthDate().toString() : "");
                        case "phone" ->
                                row.createCell(col++).setCellValue(t.getPhone() != null ? t.getPhone() : "");
                        case "email" ->
                                row.createCell(col++).setCellValue(t.getEmail() != null ? t.getEmail() : "");
                    }
                }
            }

            for (int i = 0; i < columns.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }

}
