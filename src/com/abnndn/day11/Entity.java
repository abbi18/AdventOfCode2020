package com.abnndn.day11;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Entity {
    int row;
    int col;
    boolean startFromTop;
    boolean startFromLeft;
    List<List<Boolean>> precal;
}
