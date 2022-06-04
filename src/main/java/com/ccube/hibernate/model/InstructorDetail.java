package com.ccube.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTOR_DETAIL")
public class InstructorDetail extends BaseEntity {

    @Column(name = "YOUTUBE_CHANNEL")
    private String youtubeChannel;

    @Column(name = "HOBBIE")
    private String hobbie;

//    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
//    private Instructor instructor;

    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {
    }

    public InstructorDetail(String youtubeChannel, String hobbie) {
        this.youtubeChannel = youtubeChannel;
        this.hobbie = hobbie;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id='" + super.getId() + '\'' +
                "youtubeChannel='" + youtubeChannel + '\'' +
                ", hobbie='" + hobbie + '\'' +
                '}';
    }
}
