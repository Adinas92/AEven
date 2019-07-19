package com.happening.aeven.model;

import com.happening.aeven.type.DirectionToNextPoint;
import com.happening.aeven.type.IconsType;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointLocation {

    private String name;
    private Long longitude;
    private Long latitude;
    private Integer zoomLvl;
    private String description;
    private IconsType iconsType;
    private DirectionToNextPoint directionToNextPoint;
    private List<PointLocation> nearestPoints;
}
