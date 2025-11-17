package Library.FnTree;

import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

public class FnTree<T> {
//
    private Node root;
//    
    private class Node {
	T item; // stored data
	FnTree<T> lchild; // left subtree
	FnTree<T> rchild; // right subtree
	Node(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	    item = x0;
	    lchild = lxs;
	    rchild = rxs;
	}
    }
//
    public FnTree() {
	root = null;
    }
    public FnTree
	(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	root = new Node(x0, lxs, rxs);
    }
//
    public boolean nilq() {
	return (root == null);
    }
    public boolean consq() {
	return (root != null);
    }
    public FnTree<T> lchild() { return root.lchild; }
    public FnTree<T> rchild() { return root.lchild; }
//
    public FnGtree<T> toFnGtree() {
	return new FnGtree<T>() {
	    public T value() {
		return root.item;
	    }
	    public FnList<FnGtree<T>> children() {
		return FnListSUtil.cons(
		  root.lchild.toFnGtree(),
		  FnListSUtil.sing(root.rchild.toFnGtree()));
	    }
	};
    }
//
    public LnStrm<T> inorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root == null) {
		  return new LnStcn<T>();
	      } else {
		  return LnStrmSUtil.eval0(
		    LnStrmSUtil.append0(
		      root.lchild.inorder$enumerate(),
		      LnStrmSUtil.cons0(root.item, root.rchild.inorder$enumerate())));
	      }
	  }
       );
    }
//
    public LnStrm<T> preorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root==null) {
		  return new LnStcn<T>();
	      } else {
		  return new LnStcn<T>(
                    root.item,
                    LnStrmSUtil.append0(
		      root.lchild.preorder$enumerate(), root.rchild.preorder$enumerate()));
	      }
	  }
       );
    }
//
    public LnStrm<T> postorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root==null) {
		  return new LnStcn<T>();
	      } else {
		  return LnStrmSUtil.eval0(
		    LnStrmSUtil.append0(
		      root.lchild.postorder$enumerate(),
                      LnStrmSUtil.append0(root.rchild.postorder$enumerate(), new LnStrm<T>(root.item))));
	      }
	  }
       );
    }
//
} // end of [public class FnTree<T>{...}]
