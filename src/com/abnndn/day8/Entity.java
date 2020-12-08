package com.abnndn.day8;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class Entity {
    String command;
    Integer value;
    Boolean visited;
}
