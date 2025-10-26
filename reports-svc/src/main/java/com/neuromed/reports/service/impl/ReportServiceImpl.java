package com.neuromed.reports.service.impl;
import com.neuromed.reports.dto.ReportDTO;
import com.neuromed.reports.entity.Report;
import com.neuromed.reports.exception.ResourceNotFoundException;
import com.neuromed.reports.mapper.ReportMapper;
import com.neuromed.reports.repository.ReportRepository;
import com.neuromed.reports.service.IReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements IReportService {

    private final ReportRepository reportRepository;

    @Override
    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = ReportMapper.mapToReport(reportDTO);
        Report saved = reportRepository.save(report);
        return ReportMapper.mapToReportDTO(saved);
    }

    @Override
    public List<ReportDTO> getReports() {
        return reportRepository.findAll()
                .stream()
                .map(ReportMapper::mapToReportDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportDTO updateReport(Long id, ReportDTO reportDTO) {
        Report existing = reportRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setReportType(reportDTO.getReport_type());
            existing.setDuration(reportDTO.getDuration());

            Report updated = reportRepository.save(existing);
            return ReportMapper.mapToReportDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deleteReport(Long id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ReportDTO fetchReport(String id, String correlationId) {
        Report report = reportRepository.findById(Long.valueOf(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Report", "id", id));
        return ReportMapper.mapToReportDTO(report);
    }
}
