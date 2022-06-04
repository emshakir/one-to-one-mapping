package com.ccube.hibernate;

import com.ccube.hibernate.model.*;
import com.ccube.hibernate.repository.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class OneToOneMappingApplication implements CommandLineRunner {

    private final InstructorRepository instructorRepository;
    private final InstructorDetailRepository instructorDetailRepository;

    public OneToOneMappingApplication(InstructorRepository instructorRepository, InstructorDetailRepository instructorDetailRepository) {
        this.instructorRepository = instructorRepository;
        this.instructorDetailRepository = instructorDetailRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(OneToOneMappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Instructor virat = new Instructor("Virat", "Kohli", "virat.kohli@gmail.com");
        Instructor raina = new Instructor("Suresh", "Raina", "suresh.raina@gmail.com");
        InstructorDetail viratDetails = new InstructorDetail("Virat-Kohli@youtube", "Cricket");
        InstructorDetail rainaDetails = new InstructorDetail("Suresh-Raina@youtube", "Cricket");
        virat.setInstructorDetail(viratDetails);
        raina.setInstructorDetail(rainaDetails);
        instructorRepository.save(virat);
        instructorRepository.save(raina);
        System.out.println("SAVED::::::");

        // We use bi-directional mapping to get the instructor associated to instructor detail as we cannot get that using uni-directional
        // create a instructor property in instructorDetail and map it to instructorDetails property from instructor class where
        // we are using foreign key column
        Optional<Instructor> rainaOpt = instructorRepository.findById(raina.getId());
        Instructor raina_Instructor = rainaOpt.get();
        Optional<InstructorDetail> raina_InstructorDetailOpt = instructorDetailRepository.findById(raina_Instructor.getInstructorDetail().getId());
        InstructorDetail raina_InstructorDetail = raina_InstructorDetailOpt.get();
        System.out.println(raina_Instructor.toString());
        System.out.println(raina_InstructorDetail.toString());
//        System.out.println("Deleting the Instructor and associated InstructorDetail using the CascadeType.ALL strategy:::::::::::::::::")
//        Deleting the Instructor and associated InstructorDetail using the CascadeType.ALL strategy
//        Thread.sleep(60000);
//        instructorDetailRepository.deleteById(raina_Instructor.getInstructorDetail().getId());
//        System.out.println("DELETED::::::::::");
//        Deleting only the instructor Details associated to instructor and putting the Instructor as it is
//        we need to make some changes in instructorDetails class instructor property's cascade to process this,
//        i.e., CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.MERGE, CascadeType.DETACH
        System.out.println("Deleting only the instructor Details associated to instructor and putting the Instructor as it is::::::::");
        Thread.sleep(10000);
        raina_InstructorDetail.getInstructor().setInstructorDetail(null);
        instructorDetailRepository.save(raina_InstructorDetail);
        instructorDetailRepository.deleteById(raina_InstructorDetail.getId());
        System.out.println("DELETED ASSOCIATED INSTRUCTOR DETAIL:::::::::::");
    }
}
