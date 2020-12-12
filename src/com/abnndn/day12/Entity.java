package com.abnndn.day12;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Entity {
    private int value;
    private char instruc;
}
