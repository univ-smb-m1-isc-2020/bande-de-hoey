package info806.GestionBD.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genre {
    Action,
    Comic
}
