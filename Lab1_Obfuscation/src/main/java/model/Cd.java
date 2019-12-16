package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Cd {
    String title;
    String artist;
    String country;
    String company;
    String price;
    String year;
}
