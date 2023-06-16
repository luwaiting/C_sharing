package com.example.csharing.dao;

import com.example.csharing.domain.report.ReportPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportPostingRepository extends JpaRepository<ReportPosting,Long> {
}
