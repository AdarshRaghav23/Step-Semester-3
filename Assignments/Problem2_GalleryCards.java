// Problem 2: Gallery Description Cards

abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;

    public ArtPiece() {
        counter++;
        this.pieceId = "ART-" + counter;
    }

    public String getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class Problem2_GalleryCards {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println("[" + p.getPieceId() + "] " + p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println("[" + s.getPieceId() + "] " + s.describe());
    }
}