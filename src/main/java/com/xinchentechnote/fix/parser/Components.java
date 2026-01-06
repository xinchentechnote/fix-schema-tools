package com.xinchentechnote.fix.parser;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Components {
  @XmlElement(name = "component")
  List<Component> components = new ArrayList<>();

    public Component queryComponent(String name) {
        for (Component component : components) {
            if (component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }
}
