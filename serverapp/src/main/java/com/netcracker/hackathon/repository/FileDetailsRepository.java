package com.netcracker.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netcracker.hackathon.domain.FileDetails;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Long>  {

}
