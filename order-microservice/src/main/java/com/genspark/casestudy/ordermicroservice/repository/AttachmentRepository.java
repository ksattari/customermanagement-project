package com.genspark.casestudy.ordermicroservice.repository;

import com.genspark.casestudy.ordermicroservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttachmentRepository extends JpaRepository<Attachment,String> {

}
