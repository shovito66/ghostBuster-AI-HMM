package basic;

public interface CONSTANTS {
    final double MIDDLE_probabilityLeft_Right = 0.2;
    final double MIDDLE_probabilityTop_Bottom = 0.2;
    final double MIDDLE_probabilityDiagonal = 0.04;
    final double MIDDLE_probabilityNoMove= 0.04;

    final double SIDE_probabilityLeft_Right = 0.175; //double .5
    final double SIDE_probabilityTop_Bottom = 0.175; //
    final double SIDE_probabilityDiagonal = 0.15;
    final double SIDE_probabilityNoMove= 0.175;

    final double CORNER_probabilityLeft_Right = 0.3; //double .5
    final double CORNER_probabilityTop_Bottom = 0.3; //
    final double CORNER_probabilityDiagonal = 0.1;
    final double CORNER_probabilityNoMove= 0.3;

    //-------------- for emission probability--------------
    final int RED=0;
    final int ORANGE=1;
    final int GREEN=2;

    final int WHITE=-1;

    final double ERROR=0.0;
    final double RATIO_FOR_RED=0.25;//.33 DISI AGE
    final double RATIO_FOR_ORANGE=0.5;//.66 DISI AGE
}
