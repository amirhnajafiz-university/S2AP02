package com.myPack;

/**
 * This class is for keeping the changes in rotation
 * to keep their score and their rotation situation.
 *
 */
public class AiRotationSheet {

    // The part we rotate
    private int part;

    // The type of the rotation
    private int rotationType;

    // The score of this situation
    private int score;

    /**
     * The main constructor of the class.
     *
     * @param part the part we rotate
     * @param rotationType the rotation type
     * @param score the score we give to this situation
     */
    public AiRotationSheet(int part, int rotationType, int score){
        this.part = part;
        this.rotationType = rotationType;
        this.score = score;
    }

    /**
     * A getter method for getting the part.
     *
     * @return the part
     */
    public int getPart() {
        return part;
    }

    /**
     * A getter method for getting the score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * A getter method for getting the type of the rotation.
     *
     * @return the rotation type
     */
    public int getRotationType() {
        return rotationType;
    }
}
