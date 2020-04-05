package eg.edu.alexu.csd.filestructure.redblacktree;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackTree r= new RedBlackTree();
        r.insert(4, 5);
        r.insert(2, 10);
        r.insert(20, 6);
        r.insert(32, 32);
        r.insert(1, 7);
        r.insert(3, 9);
        INode  y = r.getRoot();
        //System.out.println(y.getRightChild().getLeftChild().getKey());
        //r.delete(5);
        y = r.getRoot();
       System.out.println(r.delete(4));
       
       //System.out.println(y.getParent().getValue());
	}
}
