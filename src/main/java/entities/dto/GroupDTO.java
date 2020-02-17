/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.GroupMember;

/**
 *
 * @author root
 */
public class GroupDTO {
    private String name;
    private String studentId;
    private String color;

    public GroupDTO(GroupMember group) {
        this.name = group.getName();
        this.studentId = group.getStudentId();
        this.color = group.getColor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    
    
    
}
