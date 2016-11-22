/**
 * Created by JuIngong on 22/11/2016.
 */
public enum Direction {
    NORTH("NORTH"),
    SOUTH("SOUTH"),
    EAST("EAST"),
    WEST("WEST"),
    NONE("NONE");

    private String id;

    Direction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * Renvoie la direction associé à l'id.
     *
     * @param id
     * @return
     */
    public static Direction getDirection(String id) {
        for (Direction dir : values()) {
            if (dir.getId().equals(id)) {
                return dir;
            }
        }

        return null;
    }

}
