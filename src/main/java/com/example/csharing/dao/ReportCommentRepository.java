package com.example.csharing.dao;

import com.example.csharing.domain.report.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCommentRepository extends JpaRepository<ReportComment,Long> {
}
