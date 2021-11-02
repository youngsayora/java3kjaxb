package com.example;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlType(name = "group")
@XmlRootElement
public class Group {

    @XmlElementWrapper(name="students", nillable = true)
    @XmlElement(name = "student")
    public ArrayList<Student> students = new ArrayList<>();
    
}
