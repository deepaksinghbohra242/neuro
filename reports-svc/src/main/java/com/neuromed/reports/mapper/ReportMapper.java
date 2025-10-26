package com.neuromed.reports.mapper;
import com.neuromed.reports.dto.ReportDTO;
import com.neuromed.reports.entity.Report;

public class ReportMapper {

    public static ReportDTO mapToReportDTO(Report report) {
        if (report == null) return null;
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setReport_type(report.getReportType());
        dto.setDuration(report.getDuration());
        return dto;
    }

    public static Report mapToReport(ReportDTO dto) {
        if (dto == null) return null;

        Report report = new Report();
        report.setId(dto.getId());
        report.setReportType(dto.getReport_type());
        report.setDuration(dto.getDuration());
        return report;
    }
}
