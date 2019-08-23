package com.qa.data;

// POJO - plain old java object

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Users.class)
public class Users
{
    String name;
    String job;
    String id;
    String createdAt;


    @JsonCreator  // it is impotent annotation
    public Users(@JsonProperty(value = "name", required = true) String name,
                 @JsonProperty(value = "job", required = true) String job)
    {
        this.name = name;
        this.job = job;

    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setcreatedAt(String createAt) {
        this.createdAt = createAt;
    }

    public String getId() {
        return id;
    }

    public String getcreatedAt() {
        return createdAt;
    }
}
