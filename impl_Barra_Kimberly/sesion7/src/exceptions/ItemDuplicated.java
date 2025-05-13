package exceptions;

@SuppressWarnings("serial")
public class ItemDuplicated extends Exception {
    public ItemDuplicated(String msg) {
        super(msg); 
    }

    public ItemDuplicated() {
        super();
    }
}
