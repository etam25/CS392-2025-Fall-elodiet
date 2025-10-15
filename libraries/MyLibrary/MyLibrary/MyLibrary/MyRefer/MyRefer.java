package MyLibrary.MyRefer;

public class MyRefer<T> {
    T theValue;

    public MyRefer() {
        theValue = null;
    }
    public MyRefer(T value) {
        theValue = value;
    }

    public boolean nullq() {
        return (theValue == null);
    }

    public T getRaw() {
        return theValue;
    }

    public T getOpt() {
        return theValue;
    }
    
    public T getExn() {
        if (!nullq()) {
            return theValue;
        } else {
            throw new MyReferNullExn();
        }
    }
    public void setRaw(T value) {
        theValue = value;
        return;
    }

    public T takeoutRaw() {
        T value;
        value = theValue;
        theValue = null;
        return value;
    }

    public void discardRaw() {
        theValue = null;
        return;
    }
    public T exchRaw(T newValue) {
        T value = theValue;
        theValue = newValue;
        return value;
    }
}
