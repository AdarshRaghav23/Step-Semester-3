class TrainCoaches {
    static class Node {
        String name;
        Node prev, next;

        Node(String name) {
            this.name = name;
        }
    }

    Node head = null, tail = null;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    void remove(String name) {
        Node current = head;

        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            return;
        }

        if (current == head) {
            head = current.next;
        } else {
            current.prev.next = current.next;
        }

        if (current == tail) {
            tail = current.prev;
        } else {
            current.next.prev = current.prev;
        }
    }

    void printForward() {
        Node current = head;
        System.out.print("Forward: ");

        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    void printBackward() {
        Node current = tail;
        System.out.print("Backward: ");

        while (current != null) {
            System.out.print(current.name);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TrainCoaches train = new TrainCoaches();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
