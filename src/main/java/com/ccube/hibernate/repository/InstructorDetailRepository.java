package com.ccube.hibernate.repository;

import com.ccube.hibernate.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailRepository extends CrudRepository<InstructorDetail, Long> {
}
