class LibraryBook {
    String title, isbn;

    LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn.equals("") ? "PENDING" : isbn;
    }

    LibraryBook(String title) {
        this(title, "");
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook b = isbns[i].equals("")
                    ? new LibraryBook(titles[i])
                    : new LibraryBook(titles[i], isbns[i]);

            System.out.println(b.title + " | " + b.isbn + " | Catalogued: true");
        }
    }
}