package Library.FnTree;

public class FnTreeSUtil {
//
    public static<T>
	FnTree<T> nil() {
	return new FnTree<T>();
    }
    public static<T>
	FnTree<T> sing(T x0) {
	return cons(x0, nil(), nil());
    }
    public static<T>
	FnTree<T> cons
	(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	return new FnTree<T>(x0, lxs, rxs);
    }
//
    public static<T>
	int size(FnTree<T> xs0) {
	if (xs0.nilq()) {
	    return 0;
	} else {
	    return 1 + size(xs0.lchild()) + size(xs0.rchild());
	}
    }
//
    private static int
	intmax(int x, int y) {
	return (x >= y? x : y);
    }
    public static<T>
	int height(FnTree<T> xs0) {
	if (xs0.nilq()) {
	    return 0;
	} else {
	    return 1 + intmax(size(xs0.lchild()), size(xs0.rchild()));
	}
    }
//
} // end of [public class FnTreeSUtil{...}]
