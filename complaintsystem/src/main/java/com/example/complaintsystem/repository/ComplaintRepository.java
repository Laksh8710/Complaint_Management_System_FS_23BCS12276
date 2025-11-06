package com.example.complaintsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complaintsystem.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
