package com.example.complaintsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.complaintsystem.model.Complaint;
import com.example.complaintsystem.repository.ComplaintRepository;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "http://localhost:3000") // allows frontend access later
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    // ✅ Get all complaints
    @GetMapping
public List<Complaint> getAllComplaints() {
    List<Complaint> list = complaintRepository.findAll();
    for (Complaint c : list) {
        if (c.getStatus() == null || c.getStatus().trim().isEmpty()) {
            c.setStatus("Pending");
        }
    }
    return list;
}

    // ✅ Add new complaint
    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    // ✅ Update complaint status
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint updatedComplaint) {
        Complaint existing = complaintRepository.findById(id).orElseThrow();
        existing.setStatus(updatedComplaint.getStatus());
        return complaintRepository.save(existing);
    }

    // ✅ Delete complaint
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintRepository.deleteById(id);
    }
}
